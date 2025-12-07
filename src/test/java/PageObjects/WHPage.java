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
	
	private By inputbox = By.xpath("//*[@id=\"name\"]");
	
	private By frame1 = By.id("frm1");
	
	private By Choosefile = By.xpath("//*[@id=\"fileinput\"]");
	
	private By invisibletext = By.xpath("//*[@id=\"1\"]/div[1]/div[2]");
	
	private By Choose_button = By.xpath("//*[@id=\"fileinput\"]");
	
	
	

	
	
	
	
	

}
