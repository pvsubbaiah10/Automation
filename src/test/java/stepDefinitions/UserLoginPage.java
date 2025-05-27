package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import utils.DriverManager;
import utils.LoadProperties;

public class UserLoginPage {
	
	static WebDriver driver;
	   	   
	   @And("^the user enters UserName \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
	   public void Enter_UserName(String username, String textboxFieldName, String pageClassName) throws IOException {
		   
	  
		   
	   LoadProperties.pageClass_LoadPage(username, textboxFieldName, pageClassName);
	   
	   }  
    
	   
	   
	   
	   @And("^the user enters Password \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
	   public void Enter_Password(String password, String textboxFieldName, String pageClassName){
	   
		   
			try {
				LoadProperties.pageClass_LoadPage(password, textboxFieldName, pageClassName);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		 
	   }
	      

	   @And("^the user clicks the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
	   public void Click_button(String button, String pageClassName) {
	   
		    LoadProperties.pageClass_button(button, pageClassName);
		   
		   
		   
	   }
	     
	   

}
