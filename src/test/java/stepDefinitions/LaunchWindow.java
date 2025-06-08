package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import utils.DriverManager;
import utils.LoadProperties;
import utils.StepLogger;
import utils.StepTracker;

import java.io.IOException;

public class LaunchWindow {

	/* ************** Launch the browser ************** */
	@Given("the web application {string} is launched in a NewWindow")
	public void launchNewWindow(String webUrl) throws IOException {
		String stepName = "Launch \"" + webUrl + "\" WebPage";
		StepTracker.setStep(stepName);

		try {
			LoadProperties.Loading_Properties(webUrl);
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
	}

	/* ************** Close the browser ************** */
	@And("the user close the browser")
	public void closeTheBrowser() {
		String stepName = "Close browser session";
		StepTracker.setStep(stepName);

		try {
			DriverManager.quitDriver();
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
	}

}
