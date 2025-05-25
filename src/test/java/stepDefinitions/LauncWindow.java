package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import utils.DriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class LauncWindow {

   
	 /*  ************** Launch the browser ************** */
    
    @Given("the web application {string} is launched in a NewWindow")
    public void Launch_NewWindow(String weburl) throws IOException {
          	    	
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        
        
        Set<String> li =prop.stringPropertyNames();
        
        for(String s :li) { 	
        	 if(s.equals(weburl)) {
        		
        		System.out.println("Launch "+ s +" Wrb_Page");
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
    
        
    
    /*  ************** Close the browser ************** */
    
    @And("the user close the browser")
    public void close_the_browser() {
    	DriverManager.quitDriver();
    }   
    
  



}
