package utils;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoadProperties {
	
	static String E_data ;
	static String D_data ;
	
	
	
	
	public static void Loading_Properties(String weburl) throws IOException {
		
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config\\env.properties");
        Properties prop = new Properties();
        prop.load(fis);
        
    /*    ----> another way to find matching key
         
        Set<String> li =prop.stringPropertyNames();  
        for(String s :li) { 	
        	 if(s.equals(weburl)) {
        		
        		System.out.println("Launch "+ s +" WebPage");
        		String url = prop.getProperty(s);
        		 
        	    String browser = prop.getProperty("browser");
                DriverManager.initializeDriver(browser, url);
                break;
        	 }else {
        		 System.out.println("URL not found in env.properties file: " + weburl);
        		 break;
        	 }
        }
     */
        
        if(prop.containsKey(weburl)) {
        	String url = prop.getProperty(weburl);
        	
        	//System.out.println("Launch \""+  weburl +"\" WebPage");
        	
        	String browser = prop.getProperty("browser");
        	DriverManager.initializeDriver(browser, url);
        	
        }else {
   		 System.out.println(weburl + " --> URL not found in env.properties file." );
        }
		
	}
	
	
	public static void pageClass_LoadPage(String value, String textboxFieldName, String pageClassName) throws IOException   {
		
		 
		File fl = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config\\credentials.properties");
		FileInputStream   fis = new FileInputStream(fl);
		Properties prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	/*    ----> another way to find matching key
	 
	 	Set<String> sc = prop.stringPropertyNames();
		boolean found = false;
		
		  for(String s:sc) {
			  if(s.equals(value)) {
				   System.out.println("value "+ s ); 
				   data = prop.getProperty(s);
				   found = true;
				   break;	  
			  }
		  }

		  if (!found) {
			    throw new RuntimeException("Test data not found in credentials.properties file for key: " + value);
			}
	 */
		  
		String text = value.trim().toLowerCase();
		
		if (prop.containsKey(text)) {
			E_data  = prop.getProperty(text);
		} else {
		    throw new RuntimeException(text + " --->  not found in credentials.properties file.");
		}

		// --> Loading pageObject class
		
	       try {	          	    	  
	           String ClassName = "PageObjects." + pageClassName;

	           Class<?> cl = Class.forName(ClassName);
	           Constructor<?> constructor = cl.getConstructor(WebDriver.class); 
	           WebDriver driver = DriverManager.getDriver();
	           Object pageObject = constructor.newInstance(driver);

	           Field field = cl.getDeclaredField(textboxFieldName); 
	           field.setAccessible(true); 
	           By locator = (By) field.get(pageObject);

	           //WebElement element = driver.findElement(locator); // you can use this with out UserWaits.
	           
	           // Explicit Wait until element find.
	           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	           WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	           element.clear();
	           D_data=EncryptUtils.Decode(E_data);
	           
				/* Base64 decoding with out using encrypted key encryption data.
				  
				 * byte[] D_data =Base64.getDecoder().decode(E_data); 
				 * String ss= new String(D_data);
				 */
	           element.sendKeys(D_data);

	       } catch (Exception e) {
	           throw new RuntimeException("Error: " + e.getMessage(), e);
	       }
	 }
	
	
	public static void pageClass_button(String button, String pageClassName) {
		
		try {
			String ClassName = "PageObjects." + pageClassName;
			
			Class<?> cl = Class.forName(ClassName);
			
			Constructor<?> constructor = cl.getConstructor(WebDriver.class);
	        WebDriver driver = DriverManager.getDriver();

			Object pageObject = constructor.newInstance(driver);
			
			Field field = cl.getDeclaredField(button);
			field.setAccessible(true);
			
			By locator = (By) field.get(pageObject);
			
			WebElement element = driver.findElement(locator);
						
//			WebDriverWait UserWaits = new WebDriverWait(driver, Duration.ofSeconds(1));
//	        WebElement element = UserWaits.until(ExpectedConditions.elementToBeClickable(locator));
	        
			element.click();

		}catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage(), e);
		}
		
		
	}
	
	public static void enterText(String text, String textboxFieldName, String pageClassName) throws IOException   {
		
	
	       try {	          	    	  
	           String ClassName = "PageObjects." + pageClassName;

	           Class<?> cl = Class.forName(ClassName);
	           Constructor<?> constructor = cl.getConstructor(WebDriver.class); 
	           WebDriver driver = DriverManager.getDriver();
	           Object pageObject = constructor.newInstance(driver);

	           Field field = cl.getDeclaredField(textboxFieldName); 
	           field.setAccessible(true); 
	           By locator = (By) field.get(pageObject);

	           WebElement element = driver.findElement(locator); // you can use this with out UserWaits.
	          
	           
	           element.sendKeys(Keys.CONTROL + "a");
	           element.sendKeys(Keys.DELETE);

	           element.sendKeys(text); 
	       } catch (Exception e) {
	           throw new RuntimeException("Error: " + e.getMessage(), e);
	       }
	 }
	
	
	public static void hover(String hover, String pageClassName) {
		
		try {
			String ClassName = "PageObjects." + pageClassName;
			
			Class<?> cl = Class.forName(ClassName);
			
			Constructor<?> constructor = cl.getConstructor(WebDriver.class);
	        WebDriver driver = DriverManager.getDriver();

			Object pageObject = constructor.newInstance(driver);
			
			Field field = cl.getDeclaredField(hover);
			field.setAccessible(true);
			
			By locator = (By) field.get(pageObject);
			
			WebElement element = driver.findElement(locator);
						
           Actions ac = new Actions(driver);
           
           ac.moveToElement(element).pause(java.time.Duration.ofMillis(800)).build().perform();
	        
			
		}catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage(), e);
		}
			
	}

	
	

	public static void hoverAndClick(String hoverFieldName,String optionFieldName,String pageClassName) {

		try {
			String ClassName = "PageObjects." + pageClassName;
			Class<?> cl = Class.forName(ClassName);
			
			WebDriver driver = DriverManager.getDriver();
			Constructor<?> constructor = cl.getConstructor(WebDriver.class);
			Object pageObject = constructor.newInstance(driver);
			
			Field hoverField = cl.getDeclaredField(hoverFieldName);
			hoverField.setAccessible(true);
			By hoverLocator = (By) hoverField.get(pageObject);
			
			Field optionField = cl.getDeclaredField(optionFieldName);
			optionField.setAccessible(true);
			By optionLocator = (By) optionField.get(pageObject);
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
			
			WebElement hoverElement = wait.until(ExpectedConditions.visibilityOfElementLocated(hoverLocator));
			WebElement optionElement = wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator));
			
			Actions actions = new Actions(driver);
			
			
			actions.moveToElement(hoverElement)
			.pause(Duration.ofMillis(800))       
			.moveByOffset(3, 3)                   
			.pause(Duration.ofMillis(400))
			.perform();
			
			
			actions.moveToElement(optionElement)
			.pause(Duration.ofMillis(200))
			.click()
			.build()
			.perform();
			
		} catch (Exception e) {
			throw new RuntimeException("Hover and click failed: " + e.getMessage(), e);
			}
		}


	public static void dropdownByValueIndex(int valueindex,String FieldName,String pageClassName) {

		try {
			String ClassName = "PageObjects." + pageClassName;
			Class<?> cl = Class.forName(ClassName);
			
			WebDriver driver = DriverManager.getDriver();
			Constructor<?> constructor = cl.getConstructor(WebDriver.class);
			Object pageObject = constructor.newInstance(driver);
		
			
			Field Field = cl.getDeclaredField(FieldName);
			Field.setAccessible(true);
			By FieldNameLocator = (By) Field.get(pageObject);
			
			
		
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(FieldNameLocator));

			
            Select dropdown = new Select(dropdownElement);
            
            dropdown.selectByIndex(valueindex);
            
			
		} catch (Exception e) {
			throw new RuntimeException("Dropdown select failed: " + e.getMessage(), e);
			}
		}
	
	public static void dropdownByValue(String value,String FieldName,String pageClassName) {

		try {
			String ClassName = "PageObjects." + pageClassName;
			Class<?> cl = Class.forName(ClassName);
			
			WebDriver driver = DriverManager.getDriver();
			Constructor<?> constructor = cl.getConstructor(WebDriver.class);
			Object pageObject = constructor.newInstance(driver);
		
			
			Field Field = cl.getDeclaredField(FieldName);
			Field.setAccessible(true);
			By FieldNameLocator = (By) Field.get(pageObject);
			
			
		
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(FieldNameLocator));

			
            Select dropdown = new Select(dropdownElement);
            
            dropdown.selectByValue(value);
       
			
		} catch (Exception e) {
			throw new RuntimeException("Dropdown select failed: " + e.getMessage(), e);
			}
		}
	
	
	
	public static void multipleClicks(String fieldName, int value,String pageClassName) {

		try {
			String ClassName = "PageObjects." + pageClassName;
			Class<?> cl = Class.forName(ClassName);
			
			WebDriver driver = DriverManager.getDriver();
			Constructor<?> constructor = cl.getConstructor(WebDriver.class);
			Object pageObject = constructor.newInstance(driver);
		
			
			Field Field = cl.getDeclaredField(fieldName);
			Field.setAccessible(true);
			By FieldNameLocator = (By) Field.get(pageObject);
			
			
		
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(FieldNameLocator));

			int i=1;
            while(i<value) {
            	element.click();
            	i++;
            }
            
			
		} catch (Exception e) {
			throw new RuntimeException("element "+value+" clickable failed: " + e.getMessage(), e);
			}
		}
	
	
	
	
	public static void uploadfiles(String filepath, String elementname, String pageClassName) {

		try {
			String ClassName = "PageObjects." + pageClassName;
			Class<?> cl = Class.forName(ClassName);
			
			WebDriver driver = DriverManager.getDriver();
			Constructor<?> constructor = cl.getConstructor(WebDriver.class);
			Object pageObject = constructor.newInstance(driver);
		
			
			Field Field = cl.getDeclaredField(elementname);
			Field.setAccessible(true);
			By FieldNameLocator = (By) Field.get(pageObject);
		

	        WebElement upload = driver.findElement(FieldNameLocator);
	        upload.sendKeys(System.getProperty("user.home") + filepath);
            
			
		} catch (Exception e) {
			throw new RuntimeException("File uploading failed: " + e.getMessage(), e);
			}
		}
	
		
	
	
}
