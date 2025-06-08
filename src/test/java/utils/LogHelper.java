package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.*;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LogHelper {

    private static final Map<String, Logger> loggerMap = new HashMap<>();

    public static void setLogger(String featureName) {
        if (loggerMap.containsKey(featureName)) {
            return; // Already created
        }

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();

        // Date folder format
        String dateFolder = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Path logDir = Paths.get("logs", dateFolder);

        try {
            if (!Files.exists(logDir)) {
                Files.createDirectories(logDir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Time format with AM/PM
        String timePart = LocalTime.now().format(DateTimeFormatter.ofPattern("hh-mm-ssa")); 
        String logFileName = featureName + "_" + timePart + ".log";
        String fullPath = logDir.resolve(logFileName).toString();

        // Layout with AM/PM in time
        PatternLayout layout = PatternLayout.newBuilder()
                .withPattern("[%d{hh:mm:ss a}] [%p] %m%n") 
                .build();

        FileAppender appender = FileAppender.newBuilder()
                .withFileName(fullPath)
                .withName(featureName + "_FileAppender")
                .withLayout(layout)
                .withAppend(false)
                .setConfiguration(config)
                .build();

        appender.start();
        config.addAppender(appender);

        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.addAppender(appender, null, null);

        context.updateLoggers();

        Logger logger = LogManager.getLogger(featureName);
        loggerMap.put(featureName, logger);
    }

    public static Logger getLogger(String featureName) {
        return loggerMap.get(featureName);
    }
}
