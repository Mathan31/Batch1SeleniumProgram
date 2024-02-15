package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "./src/test/resources/features/",
		glue = {"steps","hooks"},
		dryRun = false,
		tags = "@Smoke", 
		//tags = "@Smoke and @Regression" 
		//tags = "@Smoke or @Regression" 
		plugin = {"pretty","json:target/reports/search.json","html:target/reports/search.html"},
		publish = true
		)

public class JunitRunner {

}
