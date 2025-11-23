package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utils.PageActions;
import utils.StepLogger;
import utils.StepTracker;

public class CommonSteps {

	    @And("the user validate the page title with {string}")
	    public void the_user_validate_the_page_title_with(String Title) {
			String stepName = "And the user validate the page title with\""+Title+"\"";
			StepTracker.setStep(stepName);

			try {
				PageActions.validatePageTitle(Title);
				StepLogger.logPass(stepName);
			} catch (Exception e) {
				StepLogger.logFail(stepName, e);
				throw e;
			}

	    }
	    
	    @Then("the user validate the page URL with {string}")
	    public void the_user_validate_the_page_url_with(String Url) {
			String stepName = "Then the user validate the page URL with\""+Url+"\"";
			StepTracker.setStep(stepName);

			try {
				PageActions.validateURL(Url);
				StepLogger.logPass(stepName);
			} catch (Exception e) {
				StepLogger.logFail(stepName, e);
				throw e;
			}

	    }
	
	    @And("^the user Get the text from \"([^\"]*)\" in \"([^\"]*)\" page$")
	    public void get_text_from_element(String elementtext,String pageClassName ) {
			String stepName = "And the user Get the text from\""+elementtext+"\" in \"" + pageClassName + "\" page";
			StepTracker.setStep(stepName);

			try {
				PageActions.storeText(elementtext,pageClassName );
				StepLogger.logPass(stepName);
			} catch (Exception e) {
				StepLogger.logFail(stepName, e);
				throw e;
			}
	    	
	    }
	    
	    @And("^the user Get the AttributeValue \"([^\"]*)\" from \"([^\"]*)\" in \"([^\"]*)\" page$")
	    public void get_AttributeValue_from_element(String attributeName, String locatorName, String pageClassName ) {
	        String stepName = "And the user Get the AttributeValue \"" + attributeName + "\" from \"" + locatorName + "\" in \"" + pageClassName + "\" page";
	        StepTracker.setStep(stepName);

	        try {
	            PageActions.getAttributeValue(attributeName, locatorName, pageClassName);
	            StepLogger.logPass(stepName);
	        } catch (Exception e) {
	            StepLogger.logFail(stepName, e);
	            throw e;
	        }
	    }

	    

}
