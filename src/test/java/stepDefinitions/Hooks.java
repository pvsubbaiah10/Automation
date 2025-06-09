package stepDefinitions;

import io.cucumber.java.*;
import utils.DriverManager;
import utils.LogHelper;
import utils.ScreenshotUtil;
import utils.StepTracker;
import utils.WordReportGenerator;

import java.io.IOException;

import org.apache.logging.log4j.Logger;

public class Hooks {

    private Logger logger;
    private String featureName;

  
    @Before
    public void beforeScenario(Scenario scenario) {
        // Get feature name from scenario URI
        String uri = scenario.getUri().toString();
        String[] parts = uri.split("/");
        featureName = parts[parts.length - 1].replace(".feature", "");

        // Initialize logger
        LogHelper.setLogger(featureName);
        logger = LogHelper.getLogger(featureName);
        logger.info("<=== Starting Scenario: " + scenario.getName() + " ===>");

        // Initialize Word report
        WordReportGenerator.init(featureName);
        WordReportGenerator.setScenario(scenario.getName());  // set first scenario name
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        String step = StepTracker.getStep();
        if (step == null) return;

        String status = scenario.isFailed() ? "FAIL" : "PASS";
        String screenshotPath = null;

        try {
            screenshotPath = ScreenshotUtil.captureScreenshot(featureName, scenario.getName());
        } catch (IOException e) {
            logger.error("Screenshot capture failed: " + e.getMessage());
        }

        // Re-set scenario name in case of new scenario
        WordReportGenerator.setScenario(scenario.getName());
        WordReportGenerator.addStep(step, status, screenshotPath);
    }

    @After
    public void afterScenario() {
        DriverManager.quitDriver();

        try {
            WordReportGenerator.saveReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
