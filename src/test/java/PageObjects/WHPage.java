package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WHPage {
	
	WebDriver driver;
	public WHPage(WebDriver driver) {
		this.driver =driver;
		
	}
	
	
	private By Window_Button = By.id("newWindowBtn");
	
	private By Tab_Button = By.id("newTabBtn");
	
	private By text = By.xpath("//*[@id=\"newTabBtn\"]");
	
	private By inputField = By.xpath("//*[@id=\"newTabBtn\"]");
	
	private By click = By.id("promptBox");
	private By contact = By.xpath("//*[@id=\"nav\"]/li[3]/a");
	
	
	

}
