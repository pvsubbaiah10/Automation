package stepDefinitions;

import io.cucumber.java.*;
import utils.DriverManager;
import utils.LogHelper;
import utils.ScreenshotUtil;
import utils.StepTracker;
import utils.WordReportGenerator;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.Logger;

public class Hooks {

    private Logger logger;
    private String featureName;
    private static final ConcurrentHashMap<String, AtomicInteger> scenarioCounter = new ConcurrentHashMap<>();

    @Before
    public void beforeScenario(Scenario scenario) {
        String uri = scenario.getUri().toString();
        String[] parts = uri.split("/");
        featureName = parts[parts.length - 1].replace(".feature", "");

        LogHelper.setLogger(featureName);
        logger = LogHelper.getLogger(featureName);
        logger.info("<=== Starting Scenario: " + scenario.getName() + " ===>");

        WordReportGenerator.init(featureName);
        WordReportGenerator.setScenario(featureName, scenario.getName());

        scenarioCounter.putIfAbsent(featureName, new AtomicInteger(0));
        scenarioCounter.get(featureName).incrementAndGet();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        String step = StepTracker.getStep();
        if (step == null) return;

        String status = scenario.isFailed() ? "FAIL" : "PASS";
        String screenshotPath = null;

        try {
            if (DriverManager.getDriver() != null) {

                //  Skip screenshot if alert is present
                try {
                    DriverManager.getDriver().switchTo().alert();
                    logger.info(" Alert detected — skipping screenshot for this step: " + step);
                } catch (Exception noAlert) {
                    // Alert not present → it will take screenshot
                    screenshotPath = ScreenshotUtil.captureScreenshot(featureName, scenario.getName());
                }

            } else {
                logger.warn("Driver not available, skipping screenshot for step: " + step);
            }
        } catch (Exception e) {
            logger.error("Screenshot capture skipped: " + e.getMessage());
        }

        WordReportGenerator.setScenario(featureName, scenario.getName());
        WordReportGenerator.addStep(featureName, step, status, screenshotPath);
    }

    @After
    public void afterScenario(Scenario scenario) {
        int remaining = scenarioCounter.get(featureName).decrementAndGet();

        if (remaining == 0) {
            WordReportGenerator.addSummary(featureName);
            try {
                WordReportGenerator.saveReport();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (DriverManager.getDriver() != null) {
            DriverManager.quitDriver();
        }
    }
}
