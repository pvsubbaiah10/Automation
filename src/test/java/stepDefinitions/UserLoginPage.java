package stepDefinitions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import utils.DriverManager;
import utils.LoadProperties;

public class UserLoginPage {
	
	static WebDriver driver;
	   	   
	   @And("^the user enters UserName \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
	   public void Enter_UserNmae(String value, String textboxFieldName, String pageClassName) {
		   
	   LoadProperties.pageClass_LoadPage(value, textboxFieldName, pageClassName);
	   
	   }  
    

}
