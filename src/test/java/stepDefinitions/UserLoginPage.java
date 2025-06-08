package stepDefinitions;

import java.io.IOException;
import io.cucumber.java.en.And;
import utils.LoadProperties;
import utils.StepLogger;
import utils.StepTracker;

public class UserLoginPage {

	@And("^the user enters UserName \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
	public void Enter_UserName(String username, String textboxFieldName, String pageClassName) throws IOException {
		String stepName = "Enter UserName \"" + username + "\" into \"" + textboxFieldName + "\" textbox at \"" + pageClassName + "\" page";
		StepTracker.setStep(stepName);
		try {
			LoadProperties.pageClass_LoadPage(username, textboxFieldName, pageClassName);
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
	}

	@And("^the user enters Password \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
	public void Enter_Password(String password, String textboxFieldName, String pageClassName) throws IOException {
		String stepName = "Enter Password \"" + password + "\" into \"" + textboxFieldName + "\" textbox at \"" + pageClassName + "\" page";
		StepTracker.setStep(stepName);
		try {
			LoadProperties.pageClass_LoadPage(password, textboxFieldName, pageClassName);
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
	}

	@And("^the user clicks the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
	public void Click_button(String button, String pageClassName) {
		String stepName = "Click \"" + button + "\" element at \"" + pageClassName + "\" page";
		StepTracker.setStep(stepName);
		try {
			LoadProperties.pageClass_button(button, pageClassName);
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
	}

}
