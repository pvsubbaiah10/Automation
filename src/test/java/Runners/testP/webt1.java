package Runners.testP;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class webt1 {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://www.tutorialspoint.com/selenium/practice/webtables.php?utm_source=chatgpt.com");

		List<WebElement> names = driver
				.findElements(By.xpath("//table[@class=\"table table-striped mt-3\"]/tbody/tr/td[1]"));

		for (WebElement n : names) {

			String na = n.getText();

			if (na.equals("Kierra")) {

				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

				WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//*[normalize-space(text())='"+na+"']/parent::tr//a[2]")));
				
	

				deleteButton.click();
			}

		}

		//driver.quit();

		// Thread.sleep(50000);

	}

}
