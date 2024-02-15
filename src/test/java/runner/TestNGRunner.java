package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "./src/test/resources/features/",
		glue = {"steps","hooks"},
		dryRun = false,
		tags = "@Smoke", 
		//tags = "@Smoke and @Regression" 
		//tags = "@Smoke or @Regression" 
		plugin = {"pretty","json:target/reports/search.json","html:target/reports/search.html"},
		publish = true)
public class TestNGRunner extends AbstractTestNGCucumberTests{

}
