package stepDefinitions;


import io.cucumber.java.en.And;
import utils.ExcelDataManager;
import utils.ExcelDataStore;
import utils.StepLogger;
import utils.StepTracker;

public class ExcelUrlSteps {

    @And("^the web application URL is launched in a NewWindow$")
    public void the_web_application_URL_is_launched_in_a_NewWindow() throws Exception {
		String stepName = "And the web application URL is launched in a NewWindow";
		StepTracker.setStep(stepName);

        String url = ExcelDataStore.getExcelUrl();
        if (url == null) {
            throw new RuntimeException("In Excel URL is null.");
        }
       
		try {

			ExcelDataManager.url(url);
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}

       
        System.out.println(" Launched \"" +url+ "\" in new window ");
    }
}

