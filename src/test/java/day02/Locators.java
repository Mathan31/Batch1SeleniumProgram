package day02;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {
	
/**
	Locator : It is a mechanism to locate any element in the web page.
	Types:
		- id
		- name
		- class name
		- link text
		- partial link text
		- CSS (Cascading style sheet)
		- X-Path
 */

	public static void main(String[] args) {
		// To open a chrome browser
		WebDriver driver = new ChromeDriver();
		// To maximize the browser
				driver.manage().window().maximize();
				// To clear browser cookies
				driver.manage().deleteAllCookies();
				// To navigate to any URL
				driver.navigate().to("https://www.facebook.com/");
				// Wait until page gets load
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
				// To get the title of the page
				String title = driver.getTitle();
				System.out.println("Title is : "+title);
				// Too get the current url
				String currentUrl = driver.getCurrentUrl();
				System.out.println("Current url is : "+currentUrl);
				// Enter user name
				//driver.findElement(By.id("email")).sendKeys("yalini@gmail.com");
				driver.findElement(By.cssSelector("input#email")).sendKeys("yalini@gmail.com");
				// Enter password
				//driver.findElement(By.name("pass")).sendKeys("Sathya123");
				driver.findElement(By.cssSelector("input.inputtext._55r1._6luy._9npi")).sendKeys("Testing123");
				// Click on Login 
				//driver.findElement(By.name("login")).click();
				driver.findElement(By.cssSelector("button[data-testid='royal_login_button']")).click();
				// Click on Forgot password? link
				//driver.findElement(By.linkText("Forgotten password?")).click();
				
				
				//driver.quit(); //-- Will close all the window invoked in the current instance, which mean it will close the driver.exe
	}

}
