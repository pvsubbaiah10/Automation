package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Please call initializeDriver() first");
        }
        return driver;
    }


    public static void initializeDriver(String browser, String url) {
        if (driver == null) {
            if ("chrome".equalsIgnoreCase(browser)) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\\\drivers\\chromedriver.exe");
                driver = new ChromeDriver();
            } else {
                throw new RuntimeException("Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
            driver.get(url);
        }
    }
    
    
    
   public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    } 

}
