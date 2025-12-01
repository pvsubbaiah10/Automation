package utils;

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

public class ExcelDataManager {
	
	static String E_data ;
	static String D_data ;
	
	
	public static void url(String weburl) throws IOException {
		
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config\\env.properties");
        Properties prop = new Properties();
        prop.load(fis);
        
        	String browser = prop.getProperty("browser");
        	DriverManager.initializeDriver(browser, weburl);
		
	}

	
	
	
	public static void enterText(String value, String textboxFieldName, String pageClassName) throws IOException   {
				

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
	           E_data=value;
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
	
	
	
	
}
