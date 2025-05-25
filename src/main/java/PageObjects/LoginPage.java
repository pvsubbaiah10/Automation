package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	public WebDriver driver;
	
	
	  public LoginPage(WebDriver driver) {
	        this.driver = driver;  
	    }
	  
     private By Username = By.id("username");
     private By Password  = By.id("password");
	
	
	
	
  
}
