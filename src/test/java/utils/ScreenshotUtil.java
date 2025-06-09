package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static String captureScreenshot(String featureName, String scenarioName) throws IOException {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) return null;

        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());

        String dateFolder = WordReportGenerator.getDateFolder(featureName);
        Path screenshotsDir = Paths.get("word report", dateFolder, "screenshots");

        if (!Files.exists(screenshotsDir)) {
            Files.createDirectories(screenshotsDir);
        }

        String filename = featureName + "_" + scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
        Path destFile = screenshotsDir.resolve(filename);

        FileUtils.copyFile(srcFile, destFile.toFile());

        return destFile.toString();
    }
}
