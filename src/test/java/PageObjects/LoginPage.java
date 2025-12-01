package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	public WebDriver driver;
	
	
	  public LoginPage(WebDriver driver) {
	        this.driver = driver;  
	    }
	  
     private By Username = By.xpath("//*[@id=\"loginForm\"]/div[1]/div[1]/div/label/input");
     private By Password  = By.xpath("//*[@id=\"loginForm\"]/div[1]/div[2]/div/label/input");
	
	 private By login_button = By.id("submit");
	 
     private By Username1 = By.name("username");
     private By Password2  = By.name("password");
	
	 private By login_button1 = By.xpath("//*[@type=\"submit\"]");
	 
	 
	 private By r_username = By.id("username");
	 private By r_password = By.id("password");
	 private By r_sigin = By.id("signInBtn");
 	
	
  
}
