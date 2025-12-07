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
	 private By checkbox = By.xpath("//*[@id=\"radio-btn-example\"]/fieldset/label[2]/input");
	 private By hover = By.xpath("//*[@id=\"HTML3\"]/div[1]/div/button");
	 
	 private By relod = By.xpath("//*[@id=\"HTML3\"]/div[1]/div/div/a[1]");
	 
	 
	 private By calender_button = By.xpath("//*[@id=\"datepicker\"]");
	 private By date = By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[6]/a");
	 
	 
	 
	 private By drop = By.id("ctl00_mainContent_DropDownListCurrency");
	 
	 private By passengers = By.id("divpaxinfo");
	 private By adults = By.id("hrefIncAdt");
	 private By done_button = By.id("btnclosepaxoption");
	 
	 private By from = By.xpath("//*[@id=\"ctl00_mainContent_ddl_originStation1_CTXT\"]");
	 private By pune = By.xpath("//a[@value=\"PNQ\"]");
	 private By hyd = By.xpath("//a[@value=\"HYD\"][1]");
	 
	 private By currentdate = By.cssSelector(".ui-state-default.ui-state-active");
	 
	 
	 
 	
	
  
}
