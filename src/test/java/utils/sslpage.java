package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class sslpage {
	
	public static void clickAdvance() {
		
		
		 WebDriver driver = DriverManager.getDriver();

	        try {
	            //  Advanced button
	            WebElement advancedBtn = driver.findElement(By.id("details-button"));
	            advancedBtn.click();

	            //  Proceed link
	            WebElement proceedLink = driver.findElement(By.id("proceed-link"));
	            proceedLink.click();

	            System.out.println(" Clicked Advanced and Proceed successfully");

	        } catch (Exception e) {
	            System.out.println(" Advanced/Proceed not found. Maybe SSL page auto-bypassed.");
	        }
	}

}
