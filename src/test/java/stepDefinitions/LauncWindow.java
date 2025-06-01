package stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import utils.DriverManager;
import utils.LoadProperties;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LauncWindow {

	
	
	private static final Logger logger = LoggerFactory.getLogger(LauncWindow.class);
	
	/* ************** Launch the browser ************** */

	@Given("the web application {string} is launched in a NewWindow")
	public void Launch_NewWindow(String weburl) throws IOException {

		 logger.info("the web application '{}' is launched in a NewWindow", weburl);
		LoadProperties.Loading_Properties(weburl);

	}

	/* ************** Close the browser ************** */
    @After // added Hook for this step,it will execute after Session.
	@And("the user close the browser")
	public void close_the_browser() {
    	logger.info("the user close the browser");
		DriverManager.quitDriver();
	}

}
