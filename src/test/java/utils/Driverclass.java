package utils;

import java.awt.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class Driverclass {
	 
	String weburl;
	public WebDriver driver;
	
	public WebDriver DriverManager(String weburl) throws IOException {
		
	 FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\global.properties");
	 Properties prop = new Properties();
	 prop.load(fis);
			
	 Set<String> li =prop.stringPropertyNames();
	
       for(String s :li) { 	   
    	   if(s.equals(weburl)) {
    		   //System.out.println(s);
    		   
    		   String url =prop.getProperty(s);
    		    if(driver == null) {
    				 if(prop.getProperty("browser").equalsIgnoreCase("chrome")) { 
    				 System.setProperty("window.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\chromedriver.exe");
    				 driver = new ChromeDriver(); 
    				 driver.manage().window().maximize(); 
    				 driver.get(url);
    				 driver.close(); 
    				 } 	 
    		    }   
    	   }
       }
		
	return driver;
		
	}

}
