package stepDefinitions;

import io.cucumber.java.en.Then;
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
	

}
