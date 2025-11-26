package utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.Duration;


public class WindowAndFrames {
	
	public static void Window(String windowName) {
		
		WebDriver driver = DriverManager.getDriver();
		
	    List<String> windows = new ArrayList<>(driver.getWindowHandles());

	    switch (windowName.toLowerCase()) {

	        case "parentwindow":
	            if (windows.size() > 0) {
	                driver.switchTo().window(windows.get(0));
	            } else {
	                throw new RuntimeException("parentwindow not found");
	            }
	            break;


	        case "firstwindow":
	            if (windows.size() > 1) {
	                driver.switchTo().window(windows.get(1));
	                driver.manage().window().maximize();
	            } else {
	                throw new RuntimeException("First child window not found");
	            }
	            break;

	        case "secondwindow":
	            if (windows.size() > 2) {
	                driver.switchTo().window(windows.get(2));
	                driver.manage().window().maximize();
	            } else {
	                throw new RuntimeException("Second child window not found");
	            }
	            break;

	        default:
	            throw new IllegalArgumentException("Invalid window name: " + windowName);
	    }

	    System.out.println("Switched to: " + windowName);

		
		
	}
       
	public static void closeTab() {
	    WebDriver driver = DriverManager.getDriver();
	    
	    List<String> windows = new ArrayList<>(driver.getWindowHandles());
	    for (String handle : windows) {
	        if (!handle.equals(windows.get(0))) {
	            driver.switchTo().window(handle);
	            driver.close();  // close child tab
	        }
	    }

	    driver.switchTo().window(windows.get(0));  // return to parent
	}
	

	
	public static void alerts_ok() {
	    try {
	        WebDriver driver = DriverManager.getDriver();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	        wait.until(ExpectedConditions.alertIsPresent());
	        
	        driver.switchTo().alert().accept();  
	        
	        System.out.println("Alert OK clicked");
	    } catch (Exception e) {
	        System.out.println("Alert NOT present, OK click failed");
	    }
	}

	
	
	
	public static void alerts_cancel() {
		try {
		WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        
        driver.switchTo().alert().dismiss();
      
        System.out.println("Alert CANCEL clicked");
    } catch (Exception e) {
        System.out.println("Alert NOT present, CANCEL click failed");
    }
		
		
	}
	
	public static void alerts_getText() {
		
		WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        
		
		String text = driver.switchTo().alert().getText();
		
		System.out.println(" Alert Box text : "+text);
	}
	
	public static void alerts_enterText(String text) {
		
		WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());

	    driver.switchTo().alert().sendKeys(text);
		
		
	}
	
	public static void frame(String frame,String classname) {
		
		try {
			String ClassName = "PageObjects." + classname;
			
			Class<?> cl = Class.forName(ClassName);
			
			Constructor<?> constructor = cl.getConstructor(WebDriver.class);
	        WebDriver driver = DriverManager.getDriver();

			Object pageObject = constructor.newInstance(driver);
			
			Field field = cl.getDeclaredField(frame);
			field.setAccessible(true);
			
			By locator = (By) field.get(pageObject);
			
			WebElement element = driver.findElement(locator);
			
			driver.switchTo().frame(element);
			

		}catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage(), e);
		}
		

	    
		
		
	}
	
	public static void defaultframe() {
		
		WebDriver driver = DriverManager.getDriver();

	    driver.switchTo().defaultContent();
		
		
	}
	
}
