package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
	


    public static void threadWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); // restore interrupt status
        }
    }
    
    
    public static void waitForElement(String elementName, String condition, String pageClassName, int seconds) {
    	
		try {
			String ClassName = "PageObjects." + pageClassName;
			
			Class<?> cl = Class.forName(ClassName);
			
			Constructor<?> constructor = cl.getConstructor(WebDriver.class);
			WebDriver driver=DriverManager.getDriver();
			Object pageObject = constructor.newInstance(driver);
			
			Field field = cl.getDeclaredField(elementName);
			field.setAccessible(true);
			
			By locator = (By) field.get(pageObject);
							
		    WebDriverWait UserWaits = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		    
		    if(condition.toUpperCase().equals("VISIBLE")) {
		    
                UserWaits.until(ExpectedConditions.visibilityOfElementLocated(locator));
                
		    }else if(condition.toUpperCase().equals("CLICKABLE")){
		    	
		    	UserWaits.until(ExpectedConditions.elementToBeClickable(locator));
		    	
		    }else if(condition.toUpperCase().equals("PRESENT")){
		    	
		    	UserWaits.until(ExpectedConditions.presenceOfElementLocated(locator));
		    	
		    }else if(condition.toUpperCase().equals("INVISIBLE")){
		    	
		    	UserWaits.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		    	
		    }else{
		    	 throw new IllegalArgumentException("Unsupported condition: " + condition);

		    }	

		 }catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage(), e);
		 }
    	
   
    }
    
    public static void testManually() throws InterruptedException {
    	
    try {
        System.out.println("Then the user completes the rest of the test manually...");

        Thread.sleep(600000);        
        System.exit(0);
        
    } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
    }

    }

    
}
