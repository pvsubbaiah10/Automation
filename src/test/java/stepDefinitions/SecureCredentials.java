package stepDefinitions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import utils.DriverManager;

public class SecureCredentials {
	
	WebDriver driver;
	//LoginPage Lp;
		   	   
//	   @And("the user enters UserName {string} into the User textbox at the LoginPage page")
//	   public void Enter_UserName(String UserName) {
//		    driver = DriverManager.getDriver();
//		    Lp =new LoginPage(driver);
//		    Lp.UserName(UserName);
//   
//	   }
//	   
//	   
//	   
//	   
//	   @And("the user enters Password {string} into the User textbox at the LoginPage page")
//	   public void Enter_PassWord(String Password) {
//		   
//		   Lp.PassWord(Password);
//		   
//		 
//		   
//	   }
//	   
//	   
//	   
//	   @And("the user clicks the {string} element at the LoginPage page")
//	   public void Click_the_Login_Button(String string) {
//		   driver.findElement(By.xpath("//button[@id=\"submit\"]")).click();
//	   }

   
    
	   
	   
	   
	   
	   
	   
	   
	   @And("^the user enters UserName \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
	   public void enterUserNameByLocator(String value, String textboxFieldName, String pageClassName) {
	       try {	          	    	  
	           String ClassName = "PageObjects." + pageClassName;

	           Class<?> cl = Class.forName(ClassName);
	           Constructor<?> constructor = cl.getConstructor(WebDriver.class); 
	           driver = DriverManager.getDriver();
	           Object pageObject = constructor.newInstance(driver);

	           Field field = cl.getDeclaredField(textboxFieldName); 
	           field.setAccessible(true); 
	           By locator = (By) field.get(pageObject);

	           WebElement element = driver.findElement(locator);
	           element.clear();
	           element.sendKeys(value);

	       } catch (Exception e) {
	           throw new RuntimeException("Error: " + e.getMessage(), e);
	       }
	   }  
    

}
