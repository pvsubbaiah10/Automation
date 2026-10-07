package Runners.testP;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Window {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://speedwaytech.co.in/playwrightMultipleWindows.html");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement play = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='openPlaywright']")));
		play.click();

		WebElement Tutorials = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='openTutorialsNinja']")));
		Tutorials.click();

		driver.findElement(By.xpath("//*[@id=\"openChildWindow\"]")).click();

		String p = driver.getWindowHandle();

		Set<String> windows = driver.getWindowHandles();

		String first = "";
		String second = "";
		String third = "";
		
		
		// if you dont know how many windows/tabs ,if you want atleast 6 windows/tabs use this one.
		/*
		 * List<String> childWindows = new ArrayList<>();
		 * 
		 * for (String w : windows) {
		 * 
		 * if (!w.equals(parent)) {
		 * 
		 * if (childWindows.size() < 6) { 
		 * childWindows.add(w); 
		 * } 
		 * }
		 *  }
		 */
		
		/*
		 * driver.switchTo().window(childWindows.get(0)); // 1st
		 * driver.switchTo().window(childWindows.get(1)); // 2nd
		 * driver.switchTo().window(childWindows.get(2)); // 3rd
		 */		
		
		
		for (String w : windows) {

			if (!w.equals(p)) {

				driver.switchTo().window(w);

				String title = driver.getTitle();

				if (title.contains("Store")) {
					second = w;
					// System.out.println("Title: " + driver.getTitle());
					// System.out.println("Title: " + driver.getCurrentUrl());
					// System.out.println("Title: " + driver.getPageSource());
					// break; if you want stay in that window/tab use break.
				} else if (title.contains("Playwright")) {
					first = w;
				} else {
					third = w;
				}
			}
		}
		driver.switchTo().window(second);
		String d = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[4]/div/div[2]/p[1]")).getText();
		System.out.println(d);

		driver.switchTo().window(first);

		String f = driver.findElement(By.xpath("//*[@id=\"__docusaurus_skipToContent_fallback\"]/header/div/h1"))
				.getText();
		System.out.println(f);
		driver.switchTo().window(p);
		driver.switchTo().window(third);
		driver.manage().window().maximize();

	}

}
