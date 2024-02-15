package testng;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class P12_IsuiteListenerImplementation implements ISuiteListener{
	
	long currentTime;
	long executionTime;
	
	  	  public void onStart(ISuite suite) {
	  		System.out.println("Before suite started and its name is : "+suite.getName());
	  		currentTime = System.currentTimeMillis();
	  		System.out.println("Current time is : "+currentTime);
		  }

		
		  public void onFinish(ISuite suite) {
		    executionTime = (System.currentTimeMillis() - currentTime);
		    System.out.println("Execution time is : "+executionTime);
		  }

}
