package Runners.testP;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://www.epfo.gov.in/site_en/");

		List<WebElement> list = driver.findElements(By.tagName("a"));

		List<String> Broken = new ArrayList<>();
		List<String> Working = new ArrayList<>();

		List<String> Invalid = new ArrayList<>();

		System.out.println("Total Links: " + list.size());

		int B = 0;
		int W = 0;
		int i = 0;
		for (WebElement links : list) {

			String Linkurl = links.getAttribute("href");

			if (Linkurl == null || Linkurl.isEmpty() || Linkurl.startsWith("#")) {
				Invalid.add(String.valueOf(Linkurl));
				i++;
				continue;
			}

//			URL url = new URL(Linkurl);
//
//			URLConnection connection = url.openConnection();
//
//			HttpsURLConnection C = (HttpsURLConnection) connection;

			HttpsURLConnection C = (HttpsURLConnection) new URL(Linkurl).openConnection();
        
			C.setConnectTimeout(3000);
			C.setRequestMethod("HEAD");
			C.connect();

			int n = C.getResponseCode();

			if (n != 200) {
				B++;
				Broken.add(Linkurl);

			} else {
				W++;
				Working.add(Linkurl);

			}

		}

		System.out.println("Total Broken Links: " + B);

		for (String b : Broken) {
			System.err.println("Broken Link: " + b);
		}

		System.out.println();

		System.out.println("Total Working Links: " + W);
		for (String w : Working) {
			System.out.println("Working Link: " + w);
		}

		System.out.println();

		System.out.println("Total Invalid Links: " + i);
		for (String I : Invalid) {
			System.err.println("Invalid Link: " + I);
		}

		driver.quit();

	}

}
