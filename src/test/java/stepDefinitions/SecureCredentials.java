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
	           
	    	   // taking class name from feature file and store into string.
	           String ClassName = "PageObjects." + pageClassName;

	           
	           // “Class<?> A reference to a Class object of any type.” The <?>  it is a place-holder ,called wildcard generic technic, and it represents an unknown type.
	           //  you can store any class type related Class objects like :- Class<String>, Class<LoginPage>, Class<Integer>.
	           Class<?> cl = Class.forName(ClassName); // Class.forName :- it loads LoginPage in runtime 
	           
	           // WebDriver.class :- is the argument type of the constructor and To say that WebDriver is a constructor argument type & To create an object with that constructor
	           // .class :- it represents the type like 
                       //String.class     -  Class<String> object related to String class
	                   //Integer.class    -  Integer class object
	                   //WebDriver.class  -  Class object related to WebDriver interface

	           Constructor<?> constructor = cl.getConstructor(WebDriver.class); 
	           driver = DriverManager.getDriver();
	           Object pageObject = constructor.newInstance(driver);

	           //  Field (textbox locator) ను తీసుకుంటుంది
	           //getDeclaredField :-This is a method of retrieving a single field (instance variable) in a class by its name.
	           Field field = cl.getDeclaredField(textboxFieldName); 
	           field.setAccessible(true); // private field access

	           By locator = (By) field.get(pageObject); // Field నుండి By లొకేటర్ తీసుకోవడం

	           // Use WebDriver to interact with the element
	           
	           WebElement element = driver.findElement(locator);
	           element.clear();
	           element.sendKeys(value);

	       } catch (Exception e) {
	           throw new RuntimeException("Error: " + e.getMessage(), e);
	       }
	   }  
    

}
