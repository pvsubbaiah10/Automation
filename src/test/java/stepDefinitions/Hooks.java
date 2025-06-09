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

 
//    // Close browser after every scenario
//    @After
//    public void afterScenario() {
//        DriverManager.quitDriver();
//    }
    

    @Before
    public void beforeScenario(Scenario scenario) {
        String uri = scenario.getUri().toString();
        String[] parts = uri.split("/");
        featureName = parts[parts.length - 1].replace(".feature", "");

        LogHelper.setLogger(featureName);
        logger = LogHelper.getLogger(featureName);
        logger.info("<=== Starting Scenario: " + scenario.getName() + " ===>");

        // Word report init for this feature
        WordReportGenerator.init(featureName);
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        String stepText = StepTracker.getStep();
        if (stepText == null) return;

        String status = scenario.isFailed() ? "FAIL" : "PASS";
        String screenshotPath = null;

        try {
            // Screenshot capture
            screenshotPath = ScreenshotUtil.captureScreenshot(featureName, scenario.getName());
        } catch (IOException e) {
            logger.error("Screenshot capture failed: " + e.getMessage());
        }

        // Add step info to Word report
        WordReportGenerator.addStep(stepText, status, screenshotPath);
    }
    
    @After
    public void afterScenario() {
        try {
            System.out.println("Saving Word report for feature: " + featureName);
            WordReportGenerator.saveReport();
        } catch (IOException e) {
            logger.error("Failed to save Word report: " + e.getMessage());
        }
        DriverManager.quitDriver();
    }

    
}

