package testRunners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "resources/features", 
		glue = "stepDefinitions",
		plugin = {"pretty", "html:target"})

public class TestRunner_All {

}
