package stepDefinitions;

import io.cucumber.java.*;
import utils.DriverManager;
import utils.LogHelper;
import utils.StepTracker;

import org.apache.logging.log4j.Logger;

public class Hooks {

    private Logger logger;

    // Close browser after every scenario
    @After
    public void afterScenario() {
        DriverManager.quitDriver();
    }
    

    @Before
    public void beforeScenario(Scenario scenario) {
        String uri = scenario.getUri().toString();
        String[] parts = uri.split("/");
        String featureName = parts[parts.length - 1].replace(".feature", "");

        LogHelper.setLogger(featureName);   // This will create unique log file per run
        logger = LogHelper.getLogger();

        logger.info("<=== Starting Scenario: " + scenario.getName() + " ===>");
    }

    
//    @AfterStep
//    public void afterStep(Scenario scenario) {
//        String stepName = StepTracker.getStep();
//
//        String status = scenario.getStatus().name();
//
//        if (status.equalsIgnoreCase("PASSED")) {
//            logger.info("✅ Step Passed: " + stepName);
//        } else if (status.equalsIgnoreCase("FAILED")) {
//            logger.error("❌ Step Failed: " + stepName);
//            logger.error("Reason: " + scenario.getStatus());
//        }
//        else {
//            logger.warn("⚠ Step Status: " + status + " | Step: " + stepName);
//        }
//    }
}

