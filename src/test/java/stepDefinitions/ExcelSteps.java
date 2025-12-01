package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import utils.ExcelDataStore;
import utils.ExcelUtil;

public class ExcelSteps {

    @Given("^the user loads test data file \"([^\"]*)\"$")
    public void the_user_loads_test_data_file(String fileName) {
        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/testdata/" + fileName;
        ExcelDataStore.setTestDataFilePath(filePath);
        System.out.println("Excel file set: " + filePath);
    }

    @And("^the user fetches login details for UserName \"([^\"]*)\" from sheet \"([^\"]*)\"$")
    public void the_user_fetches_login_details_for_UserName_from_sheet(String userName, String sheetName) throws Exception {

        String filePath = ExcelDataStore.getTestDataFilePath();
        if (filePath == null) {
            throw new RuntimeException("Excel file not loaded.");

        }

    
        String userId   = ExcelUtil.getUserId(filePath, userName);      
        String password = ExcelUtil.getPassword(filePath, userName);   

       
        ExcelDataStore.setExcelUsername(userId);
        ExcelDataStore.setExcelPassword(password);

    }

    @And("^the user fetches the URL for WebPage \"([^\"]*)\" from sheet \"([^\"]*)\"$")
    public void the_user_fetches_url_for__PageName_from_sheet(String WebPage, String sheetName) throws Exception {

        String filePath = ExcelDataStore.getTestDataFilePath();
        if (filePath == null) {
            throw new RuntimeException("Excel file not loaded.");
        }

      
        String url = ExcelUtil.getUrl(filePath, WebPage);
        ExcelDataStore.setExcelUrl(url);

        System.out.println("Excel URL: " + url);
    }
}

