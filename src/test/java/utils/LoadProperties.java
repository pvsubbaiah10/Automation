package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoadProperties {
	
	static WebDriver driver;
	
	public static void Loading_Properties(String weburl) throws IOException {
		
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        
        
        Set<String> li =prop.stringPropertyNames();
        
        for(String s :li) { 	
        	 if(s.equals(weburl)) {
        		
        		System.out.println("Launch "+ s +" WebPage");
        		String url = prop.getProperty(s);
        		 
        	    String browser = prop.getProperty("browser");
                DriverManager.initializeDriver(browser, url);
                break;
        	 }else {
        		 System.out.println("URL not found in properties file: " + weburl);
        		 break;
        	 }
        }
		
	}
	
	
	public static void pageClass_LoadPage(String value, String textboxFieldName, String pageClassName) {
		
	       try {	          	    	  
	           String ClassName = "PageObjects." + pageClassName;

	           Class<?> cl = Class.forName(ClassName);
	           Constructor<?> constructor = cl.getConstructor(WebDriver.class); 
	           driver = DriverManager.getDriver();
	           Object pageObject = constructor.newInstance(driver);

	           Field field = cl.getDeclaredField(textboxFieldName); 
	           field.setAccessible(true); 
	           By locator = (By) field.get(pageObject);

	           WebElement element = driver.findElement(locator);
	           element.clear();
	           element.sendKeys(value);

	       } catch (Exception e) {
	           throw new RuntimeException("Error: " + e.getMessage(), e);
	       }
		
	}
	
	
	
	

}
