package utils;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {

	
	
	 private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>(); // it Creates thread  for WebDriver


	public static void initializeDriver(String browser, String url) {
		if (driver.get() == null) {
			if ("chrome".equalsIgnoreCase(browser)) {
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\\\drivers\\chromedriver.exe");
				driver.set(new ChromeDriver()); //Assigns a new Chrome browser to the current thread
			} else if("msedge".equalsIgnoreCase(browser)) {
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\\\drivers\\msedgedriver.exe");
				driver.set(new EdgeDriver()); //Assigns a new Edge browser to the current thread
			} else {
	            throw new RuntimeException("Unsupported browser: " + browser);
	        }
			
			 driver.get().manage().window().maximize();
			 driver.get().get(url); // driver.get() :- Fetches the WebDriver for the current thread only
		}
	}

	
	public static WebDriver getDriver() {
		if (driver.get() == null) {
			throw new IllegalStateException("Driver not initialized. Please call initializeDriver() first");
		}
		return driver.get();
	}

	
	
	
    public static void quitDriver() {
        if (driver.get() != null) {
        	driver.get().quit();
            driver.remove();  //  remove driver reference from ThreadLocal
        }
    }

}
