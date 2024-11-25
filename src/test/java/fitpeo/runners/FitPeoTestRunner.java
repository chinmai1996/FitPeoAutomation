package fitpeo.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

    features = "Features",
        glue = "fitpeo.stepdefinitions",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,        
        dryRun = false
)
public class FitPeoTestRunner extends AbstractTestNGCucumberTests {
}
