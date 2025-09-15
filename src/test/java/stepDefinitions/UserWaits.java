package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utils.StepLogger;
import utils.StepTracker;
import utils.Waits;

public class UserWaits {
	
	/* ************** Thread wait ************** */
	
	@And("^the user waits for \"([^\"]*)\" seconds$")
	public void the_user_waits_for_thread_of_seconds(int seconds) {
		
		String stepName = "And the user waits for \""+ seconds +"\" seconds ";
		StepTracker.setStep(stepName);
		try {
            Waits.threadWait(seconds);
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
	}
	
	/* ************** Explicit Wait ************** */
	
	@And("^the user waits for the \"([^\"]*)\" element to be \"([^\"]*)\" on the \"([^\"]*)\" page$")
	public void the_user_waits_for_element_to_be_visible_on_page(String elementName, String condition, String pageClassName) {

		String stepName = "And the user waits for the \""+elementName+"\" element to be \""+condition+"\" on the\""+ pageClassName+ "\"page";
		StepTracker.setStep(stepName);
		try {
            Waits.waitForElement(elementName,condition,pageClassName,20);
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
		
	}
	
	
	@Then("the user completes the rest of the test manually")
	public void the_user_completes_the_rest_of_the_test_manually() throws Exception  {
	
		
		String stepName = "Then the user completes the rest of the test manually";
		StepTracker.setStep(stepName);
		try {
            Waits.testManually();
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		} 
		
	}

	

}
