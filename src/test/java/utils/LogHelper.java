package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        Configuration config = context.getConfiguration();

        String logFileName = Paths.get("logs", featureName + ".log").toString();
        PatternLayout layout = PatternLayout.newBuilder()
                .withPattern("[%d{HH:mm:ss}] [%p] %m%n")
                .build();

        FileAppender appender = FileAppender.newBuilder()
                .withFileName(logFileName)
                .withName(featureName + "FileAppender")
                .withLayout(layout)
                .withAppend(false)
                .setConfiguration(config)
                .build();

        appender.start();
        config.addAppender(appender);

        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.addAppender(appender, null, null);

        context.updateLoggers();

        logger = LogManager.getLogger(featureName); // Set logger
    }

    public static Logger getLogger() {
        return logger;
    }
}
