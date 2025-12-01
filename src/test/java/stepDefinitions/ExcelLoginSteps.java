package stepDefinitions;

import io.cucumber.java.en.And;
import utils.ExcelDataManager;
import utils.ExcelDataStore;
import utils.StepLogger;
import utils.StepTracker;

public class ExcelLoginSteps {
	
	
	@And("^the user enters UserName into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
	public void the_user_enters_Excel_UserName_into_the_textbox(String textboxFieldName, String pageClassName) throws Exception {
		String stepName = "Enter UserName into the \"" + textboxFieldName + "\" textbox at \"" + pageClassName + "\" page";
		StepTracker.setStep(stepName);
	    String userId = ExcelDataStore.getExcelUsername(); 

	    if (userId == null || userId.trim().isEmpty()) {
	        throw new RuntimeException(" Excel UserId is null or empty.");
	    }

		try {
			ExcelDataManager.enterText(userId, textboxFieldName, pageClassName);
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
	    
	    
	    
	}

	
	@And("^the user enters Password into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
	public void the_user_enters_Excel_Password_into_the_textbox(String textboxFieldName, String pageClassName) throws Exception {
		String stepName = "Enter Password into the \"" + textboxFieldName + "\" textbox at \"" + pageClassName + "\" page";
		StepTracker.setStep(stepName);
	    String password = ExcelDataStore.getExcelPassword();

	    if (password == null || password.trim().isEmpty()) {
	        throw new RuntimeException(" Excel Password is null or empty.");
	    }

	   
		try {
			 ExcelDataManager.enterText(password, textboxFieldName, pageClassName);
			StepLogger.logPass(stepName);
		} catch (Exception e) {
			StepLogger.logFail(stepName, e);
			throw e;
		}
	}



}
