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

public class webt3 {

	private static WebDriver webDriver;

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://practice.expandtesting.com/dynamic-table");

		List<WebElement> names = driver.findElements(By.xpath("//*[@class=\"table table-striped\"]//tr/td[1]"));

		for (WebElement n : names) {

			String s = n.getText();
			// System.out.println(s);

			if (s.equals("Firefox")) {

				WebElement Disk = driver.findElement(
						By.xpath("//*[normalize-space(text())='" + s + "']/ancestor::tr/td[contains(text(),'MB/s')]"));
				String d = Disk.getText();
				System.out.println(d);
			}

		}

		// driver.quit();

		// Thread.sleep(50000);

	}

}
