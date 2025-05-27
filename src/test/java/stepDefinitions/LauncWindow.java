package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import utils.DriverManager;
import utils.LoadProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class LauncWindow {
	
	 /*  ************** Launch the browser ************** */
    
    @Given("the web application {string} is launched in a NewWindow")
    public void Launch_NewWindow(String weburl) throws IOException {
          	    	
    	LoadProperties.Loading_Properties(weburl);

     }
    
        
    
    /*  ************** Close the browser ************** */
    
    @And("the user close the browser")
    public void close_the_browser() {
    	DriverManager.quitDriver();
    }   
    
  



}
