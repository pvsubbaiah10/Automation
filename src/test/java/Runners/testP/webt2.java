package Runners.testP;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class webt2 {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		// driver.manage().window().maximize();

		driver.get("https://www.tutorialspoint.com/selenium/practice/webtables.php");

		List<WebElement> salary = driver
				.findElements(By.xpath("//*[@class=\"table table-striped mt-3\"]/tbody/tr/td[5]"));

		for (WebElement n : salary) {

			String s = n.getText();

			int num = Integer.parseInt(s);

			//System.out.println(num);

			if (num > 10000 || num ==2000) {

				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

				WebElement d = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//*[normalize-space(text())='" + num + "']/parent::tr//a[2]")));
				d.click();

			}

		}

		driver.quit();

	}

}
