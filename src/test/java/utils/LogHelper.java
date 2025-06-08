package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.nio.file.Paths;

public class LogHelper {

    private static Logger logger;


    
    @SuppressWarnings("deprecation")
    public static void setLogger(String featureName) {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        org.apache.logging.log4j.core.config.Configuration config = context.getConfiguration();

        String timestamp = java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HHmmss"));
        String logFileName = Paths.get("logs", featureName + "_" + timestamp + ".log").toString();

        PatternLayout layout = PatternLayout.newBuilder()
                .withPattern("[%d{HH:mm:ss}] [%p] %m%n")
                .build();

        String appenderName = featureName + "FileAppender";

        // ✅ Check and remove previous appender if exists
        Appender oldAppender = config.getAppender(appenderName);
        if (oldAppender != null) {
            oldAppender.stop();
            LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
            loggerConfig.removeAppender(appenderName);  // <-- THIS is the right remove method
        }

        FileAppender appender = FileAppender.newBuilder()
                .withFileName(logFileName)
                .withName(appenderName)
                .withLayout(layout)
                .withAppend(false)
                .setConfiguration(config)
                .build();

        appender.start();
        config.addAppender(appender);

        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.addAppender(appender, null, null);

        context.updateLoggers();

        logger = LogManager.getLogger(featureName);
    }



    public static Logger getLogger() {
        return logger;
    }
}
