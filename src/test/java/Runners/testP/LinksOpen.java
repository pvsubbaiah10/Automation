package Runners.testP;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LinksOpen {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://rahulshettyacademy.com/AutomationPractice/#");

		List<WebElement> list = driver.findElements(By.xpath("//*[@class='gf-t']/tbody/tr/td[1]//a"));

		List<String> urls = new ArrayList<>();

		for (int i=1;i<list.size();i++) {

			 WebElement links = list.get(i);
			
			String s = links.getText();

			System.out.println(s);

			String url = links.getAttribute("href");

			if (url != null && !url.isEmpty()) {
				urls.add(url);
			}

		}

		for (String url : urls) {

			driver.get(url);

			System.out.println("Opened URL: " + driver.getCurrentUrl());

			driver.navigate().back();
		}

	}

}
