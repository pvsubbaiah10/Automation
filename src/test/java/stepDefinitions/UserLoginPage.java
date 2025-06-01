package stepDefinitions;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.And;
import utils.LoadProperties;

public class UserLoginPage {
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserLoginPage.class);

	@And("^the user enters UserName \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
	public void Enter_UserName(String username, String textboxFieldName, String pageClassName) throws IOException {

		 logger.info("the user enters UserName '{}' into the '{}' textbox at the '{}' page", username, textboxFieldName, pageClassName);
		LoadProperties.pageClass_LoadPage(username, textboxFieldName, pageClassName);

	}

	@And("^the user enters Password \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
	public void Enter_Password(String password, String textboxFieldName, String pageClassName) {

		 logger.info("the user enters UserName '{}' into the '{}' textbox at the '{}' page", password, textboxFieldName, pageClassName);
		try {
			LoadProperties.pageClass_LoadPage(password, textboxFieldName, pageClassName);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@And("^the user clicks the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
	public void Click_button(String button, String pageClassName) {

		 logger.info("the user clicks the '{}' element at the '{}' page", button, pageClassName);
		LoadProperties.pageClass_button(button, pageClassName);

	}

}
