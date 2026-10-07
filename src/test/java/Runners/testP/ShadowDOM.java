package Runners.testP;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShadowDOM {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://practice.softwaretestingmentor.com/shadow-dom?utm_source=");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// shadowRoot 1

		WebElement shadowHost = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#shadow-host-1")));

		// WebElement shadowHost =
		// driver.findElement(By.xpath("//div[@id='shadow-host-1']"));
		SearchContext shadowRoot = shadowHost.getShadowRoot();

		shadowRoot.findElement(By.cssSelector("#shadow-input")).sendKeys("Venkat");

		shadowRoot.findElement(By.cssSelector("input[name='shadowPassword']")).sendKeys("@venek");

		// shadowRoot 2

		WebElement shadowHost2 = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#shadow-host-2")));

		SearchContext shadowRoot2 = shadowHost2.getShadowRoot();

		WebElement increment = shadowRoot2.findElement(By.cssSelector("#shadow-increment"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", increment);
		for (int i = 1; i <=5; i++) {

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", increment);
		}
		
		
		
		
		//Nested Shadow DOM

	}

}
