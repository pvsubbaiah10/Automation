package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoadProperties {
	
	static WebDriver driver;
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
	           driver = DriverManager.getDriver();
	           Object pageObject = constructor.newInstance(driver);

	           Field field = cl.getDeclaredField(textboxFieldName); 
	           field.setAccessible(true); 
	           By locator = (By) field.get(pageObject);

	           //WebElement element = driver.findElement(locator); // you can use this with out wait.
	           
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
			Object pageObject = constructor.newInstance(driver);
			
			Field field = cl.getDeclaredField(button);
			field.setAccessible(true);
			
			By locator = (By) field.get(pageObject);
			
			WebElement element = driver.findElement(locator);
						
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
//	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	        
			element.click();

		}catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage(), e);
		}
		
		
	}
	


}
