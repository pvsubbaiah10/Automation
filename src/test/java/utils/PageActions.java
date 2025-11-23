package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PageActions {
	
	public static void validatePageTitle(String expectedTitle) {
		
        WebDriver driver = DriverManager.getDriver();

        String actualTitle = driver.getTitle();
        System.out.println(" *** Page title: " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle, 
                " *** Page title mismatch! Expected: " + expectedTitle + " but was: " + actualTitle);

        System.out.println(" *** Page title validated successfully: " + actualTitle);
	}
	
	public static void validateURL(String expectedUrl) {
		
        WebDriver driver = DriverManager.getDriver();

        String actualUrl = driver.getCurrentUrl();
        System.out.println(" *** Current URL: " + actualUrl);

        Assert.assertEquals(actualUrl, expectedUrl,
                " *** URL mismatch! Expected: " + expectedUrl + " but was: " + actualUrl);

        System.out.println(" *** Page URL validated successfully: " + actualUrl);
	}
	
	public static void storeText(String text ,String pageClassName) {
		
		try {
			String ClassName = "PageObjects." + pageClassName;
			
			Class<?> cl = Class.forName(ClassName);
			
			Constructor<?> constructor = cl.getConstructor(WebDriver.class);
	        WebDriver driver = DriverManager.getDriver();

			Object pageObject = constructor.newInstance(driver);
			
			Field field = cl.getDeclaredField(text);
			field.setAccessible(true);
			
			By locator = (By) field.get(pageObject);
			
			WebElement element = driver.findElement(locator);
					
	        
			String storingtext= element.getText();
			System.out.println(" ***** Retrieved Element Text: [ \"" + storingtext+"\" ] *****");

		}catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage(), e);
		}
        

	}
	
	public static void getAttributeValue(String attributeName, String locatorName, String pageClassName) {

	    try {
	        

	        String className = "PageObjects." + pageClassName;
	        Class<?> cl = Class.forName(className);

	        Constructor<?> constructor = cl.getConstructor(WebDriver.class);
	        WebDriver driver = DriverManager.getDriver();
	        Object pageObject = constructor.newInstance(driver);

	        Field locatorField = cl.getDeclaredField(locatorName);
	        locatorField.setAccessible(true);

	        By locator = (By) locatorField.get(pageObject);

	        WebElement element = driver.findElement(locator);

	        String value = element.getAttribute(attributeName);

	        System.out.println(" ***** Retrieved Attribute Value: [ \"" + value+"\" ] *****");

	    } catch (Exception e) {
	        throw new RuntimeException("Error: " + e.getMessage(), e);
	    }
	}

}
