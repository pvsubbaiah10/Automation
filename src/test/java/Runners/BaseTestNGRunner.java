package Runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features = "src/test/resources/features",glue = "stepDefinitions",monochrome = true)

public class BaseTestNGRunner extends AbstractTestNGCucumberTests{
	
	
//  -----> If you want parallel execution you can uncomment this.
	
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios(); // Enables parallel execution
//    }
   

}
