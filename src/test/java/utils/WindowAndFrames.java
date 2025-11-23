package utils;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.WebDriver;

public class WindowAndFrames {
	
	public static void Window(String windowName) {
		
		WebDriver driver = DriverManager.getDriver();
		
	    List<String> windows = new ArrayList<>(driver.getWindowHandles());

	    switch (windowName.toLowerCase()) {

	        case "parentwindow":
	            if (windows.size() > 0) {
	                driver.switchTo().window(windows.get(0));
	            } else {
	                throw new RuntimeException("parentwindow not found");
	            }
	            break;


	        case "firstwindow":
	            if (windows.size() > 1) {
	                driver.switchTo().window(windows.get(1));
	                driver.manage().window().maximize();
	            } else {
	                throw new RuntimeException("First child window not found");
	            }
	            break;

	        case "secondwindow":
	            if (windows.size() > 2) {
	                driver.switchTo().window(windows.get(2));
	                driver.manage().window().maximize();
	            } else {
	                throw new RuntimeException("Second child window not found");
	            }
	            break;

	        default:
	            throw new IllegalArgumentException("Invalid window name: " + windowName);
	    }

	    System.out.println("Switched to: " + windowName);

		
		
	}

}
