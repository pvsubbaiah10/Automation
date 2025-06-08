package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StepLogger {
    private static final Logger log = LogManager.getLogger(StepLogger.class);

    public static void logPass(String stepName) {
        log.info("Step [Passed]: " + stepName);
    }

    public static void logFail(String stepName, Exception e) {
        log.error("Step [Failed]: " + stepName);
        log.error("{ ERROR ---> }: " + e.getMessage(), e); 
    }
}
