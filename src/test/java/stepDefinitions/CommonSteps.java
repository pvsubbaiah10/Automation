package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utils.LoadProperties;
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

	    @And("^the user enters \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
	    public void user_enter_text(String text, String locatorName, String pageClassName ) throws Exception {
	        String stepName = "And the user enters \"" + text + "\" into the \"" + locatorName + "\" textbox at the \"" + pageClassName + "\" page";
	        StepTracker.setStep(stepName);

	        try {
	            LoadProperties.enterText(text, locatorName, pageClassName);
	            StepLogger.logPass(stepName);
	        } catch (Exception e) {
	            StepLogger.logFail(stepName, e);
	            throw e;
	        }
	    }  

	    
		@And("^the user hovers over the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
		public void Hover_button(String button, String pageClassName) {
			String stepName = "User hovers over the \"" + button + "\" element at \"" + pageClassName + "\" page";
			StepTracker.setStep(stepName);
			try {
				LoadProperties.hover(button, pageClassName);
				StepLogger.logPass(stepName);
			} catch (Exception e) {
				StepLogger.logFail(stepName, e);
				throw e;
			}
		}
		
	    
		@And("^the user hovers over \"([^\"]*)\" and clicks \"([^\"]*)\" at the \"([^\"]*)\" page$")
		public void hover_and_click(String hoverField, String optionField, String pageClassName) {
		   
			String stepName = "User hovers over \"" + hoverField + "\" and clicks \"" + optionField + "\" at the \"" + pageClassName + "\" page";
			StepTracker.setStep(stepName);
			try {
				LoadProperties.hoverAndClick(hoverField, optionField, pageClassName);
				StepLogger.logPass(stepName);
			} catch (Exception e) {
				StepLogger.logFail(stepName, e);
				throw e;
			}
		}
		@And("^the user selects valuelndex \"([^\"]*)\" from the \"([^\"]*)\" dropdown at the \"([^\"]*)\" page$")
		public void the_user_selects_valuelndex_from_the_dropdown_at_the_page(String  valuelndex, String webelemnt, String pageClassName) {
			String stepName = "User selects valuelndex \""+valuelndex+"\" from the \""+webelemnt+"\" dropdown at the \""+pageClassName+"\" page";
			StepTracker.setStep(stepName);
			try {
				LoadProperties.dropdownByValueIndex(Integer.parseInt(valuelndex), webelemnt, pageClassName);
				StepLogger.logPass(stepName);
			} catch (Exception e) {
				StepLogger.logFail(stepName, e);
				throw e;
			
		   }
		}
		@And("^the user selects value \"([^\"]*)\" from the \"([^\"]*)\" dropdown at the \"([^\"]*)\" page$")
		public void the_user_selects_value_from_the_dropdown_at_the_page(String value, String webelemnt, String pageClassName) {
			String stepName = "User selects value \""+value+"\" from the \""+webelemnt+"\" dropdown at the \""+pageClassName+"\" page";
			StepTracker.setStep(stepName);
			try {
				LoadProperties.dropdownByValue(value, webelemnt, pageClassName);
				StepLogger.logPass(stepName);
			} catch (Exception e) {
				StepLogger.logFail(stepName, e);
				throw e;
			
		  }
		}
		@And("^the user clicks the \"([^\"]*)\" element \"([^\"]*)\" times on the \"([^\"]*)\" page$")
		public void the_user_clicks_elemnt(String webelemnt, String value, String pageClassName) {
			String stepName = "User clicks the \""+webelemnt+"\" element \""+value+"\" element \""+pageClassName+"\" page";
			StepTracker.setStep(stepName);
			try {
				LoadProperties.multipleClicks(webelemnt, Integer.parseInt(value), pageClassName);
				StepLogger.logPass(stepName);
			} catch (Exception e) {
				StepLogger.logFail(stepName, e);
				throw e;
			
		  }
		}	
	   
		
		@And("^the user uploads file \"([^\"]*)\" into \"([^\"]*)\" in \"([^\"]*)\" page$")
		public void upload_File(String filePath, String elementName, String pageClassName) throws Exception {
			String stepName = "User uploads file \""+filePath+"\" into \""+elementName+"\" in \""+pageClassName+"\" page";
			StepTracker.setStep(stepName);
			try {
				LoadProperties.uploadfiles(filePath, elementName, pageClassName);
				StepLogger.logPass(stepName);
			} catch (Exception e) {
				StepLogger.logFail(stepName, e);
				throw e;
			
		  }
			
		}



}
