package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

public class PreAndPostCondition {
	
	@Before(value = "@Smoke")
	public void beforeScenario1() {
		System.out.println("This will be executed before all the scenario 1 from all the feature file.");
	}
	
	@Before(value = "@Regression")
	public void beforeScenario2() {
		System.out.println("This will be executed before all the scenario 2 from all the feature file.");
	}
	
	@After(value = "@Smoke")
	public void afterScenario1() {
		System.out.println("This will be executed after all the scenario 1 from all the feature file.");
	}
	
	@After(value = "@Regression")
	public void afterScenario2() {
		System.out.println("This will be executed after all the scenario 2 from all the feature file.");
	}
	
	//@BeforeStep
	public void beforeEachStep() {
		System.out.println("This will be execute before each step of all scenarios from all the feature file.");
	}
	
	//@AfterStep
	public void afterEachStep() {
		System.out.println("This will be execute after each step of all scenarios from all the feature file.");
	}
}
