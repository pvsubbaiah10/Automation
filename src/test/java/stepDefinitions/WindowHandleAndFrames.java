package stepDefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utils.DriverManager;
import utils.StepLogger;
import utils.StepTracker;
import utils.WindowAndFrames;

public class WindowHandleAndFrames {
	
	
	@Then("the user switches to {string}")
	public void user_switches_to_window(String windowName) {
		
		String stepName = "Then the user switch to \""+ windowName +"\"";
		StepTracker.setStep(stepName);
		try {
            WindowAndFrames.Window(windowName);
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}		
			
	}
	
	@And("the user closes the current tab")
	public void closeCurrentTab() {
		
		String stepName = "And the user closes the current tab";
		StepTracker.setStep(stepName);
		try {
            WindowAndFrames.closeTab();
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}	
	}		
		

	
	
	
	@Then("the user switches to alert and click OK button")
	public void the_user_switches_to_alert_and_click_ok_button() {
		String stepName = "Then the user switches to alert and click OK button";
		StepTracker.setStep(stepName);
		try {
            WindowAndFrames.alerts_ok();
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
	}

	@Then("the user switches to alert and click CANCEL button")
	public void the_user_switches_to_alert_and_click_cancel_button() {
		String stepName = "Then the user switches to alert and click CANCEL button";
		StepTracker.setStep(stepName);
		try {
            WindowAndFrames.alerts_cancel();
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
	}

	@Then("the user switches to alert and get text from alertbox")
	public void the_user_switches_to_alert_and_get_text_from_alertbox() {
		String stepName = "Then the user switches to alert and get text from alertbox";
		StepTracker.setStep(stepName);
		try {
            WindowAndFrames.alerts_getText();
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
	}
	
	
	@Then("the user switches to alert and enter {string} into alert box")
	public void the_user_switches_to_alert_and_enter_text_into_alert_box(String text) {
		String stepName = "Then the user switches to alert and enter\""+ text + "\" into alert box";
		StepTracker.setStep(stepName);
		try {
            WindowAndFrames.alerts_enterText(text);
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}

	}


}
