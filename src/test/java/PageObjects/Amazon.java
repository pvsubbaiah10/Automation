package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Amazon {
   
	public WebDriver driver;
	
	
	public Amazon(WebDriver driver){
		this.driver=driver;
		
	}
	
	
	private By search_box =By.xpath("//*[@id=\"twotabsearchtextbox\"]");
	private By search_button =By.xpath("//*[@id=\"nav-search-submit-button\"]");
	
	private By addtocart =By.xpath("//*[@id=\"a-autoid-1-announce\"]");
	private By addtocart_button =By.xpath("//*[@id=\"nav-cart-count-container\"]");
	private By airpods =By.xpath("(//span[contains(@class,'a-truncate-cut')])[1]");
	private By iphone =By.xpath("(//span[contains(@class,'a-truncate-cut')])[2]");
	
}
