package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.DriverSetup;
import utils.Driverclass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LauncWindow {

    public WebDriver driver;

    Driverclass dc;
    
    @Given("the web application {string} is launched in a NewWindow")
    public void Launch_NewWindow(String weburl) throws IOException {
      
    	dc = new Driverclass();
    	dc.DriverManager(weburl);
       System.out.println("eclipse");
    	
    }



}
