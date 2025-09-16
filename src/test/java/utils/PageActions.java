package utils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PageActions {
	
	public static void validatePageTitle(String expectedTitle) {
		
        WebDriver driver = DriverManager.getDriver();

        String actualTitle = driver.getTitle();
        System.out.println(" *** Page title: " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle, 
                " *** Page title mismatch! Expected: " + expectedTitle + " but was: " + actualTitle);

        System.out.println(" *** Page title validated successfully: " + actualTitle);
	}
	
	public static void validateURL(String expectedUrl) {
		
        WebDriver driver = DriverManager.getDriver();

        String actualUrl = driver.getCurrentUrl();
        System.out.println(" *** Current URL: " + actualUrl);

        Assert.assertEquals(actualUrl, expectedUrl,
                " *** URL mismatch! Expected: " + expectedUrl + " but was: " + actualUrl);

        System.out.println(" *** Page URL validated successfully: " + actualUrl);
	}
	
	
	

}
