package day02;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InvokeChromeAdditionalMethods {
	
/**
 * 	Maven ---> It is a Project Management Tool.
	- Project Structure
	- It is providing the way to handle the dependencies in a centralized way using pom.xml.
	- It will compile the main java folder
	- It will compile the mail test folder
	- It will perform packaging
	- It will deploy the package .
 */

	public static void main(String[] args) {
		// To open a chrome browser
		WebDriver driver = new ChromeDriver();
		// To maximize the browser
				driver.manage().window().maximize();
				// To clear browser cookies
				driver.manage().deleteAllCookies();
				// To navigate to any URL
				//driver.get("https://www.google.com/");
				driver.navigate().to("https://www.google.com/");
				// Wait until page gets load
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
				// To get the title of the page
				String title = driver.getTitle();
				System.out.println("Title is : "+title);
				// Too get the current url
				String currentUrl = driver.getCurrentUrl();
				System.out.println("Current url is : "+currentUrl);
				// Close the browser
				//driver.close(); //--> Will close the current focused window
				driver.quit(); //-- Will close all the window invoked in the current instance, which mean it will close the driver.exe
	}

}
