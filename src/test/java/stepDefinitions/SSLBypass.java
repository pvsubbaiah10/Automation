package stepDefinitions;
import io.cucumber.java.en.When;
import utils.StepLogger;
import utils.StepTracker;
import utils.sslpage;

public class SSLBypass {

    @When("the user clicks the Advanced option and clicks Proceed")
    public void user_clicks_advanced_and_proceed() {
    	
		String stepName = "When the user clicks the Advanced option and clicks Proceed";
		StepTracker.setStep(stepName);
		try {
			sslpage.clickAdvance();
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
    	
    	
    }
}
