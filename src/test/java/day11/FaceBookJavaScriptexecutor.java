package day11;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FaceBookJavaScriptexecutor {
	
	public static WebDriver driver;
	public static String sURL = "https://www.facebook.com/";
	public static String browserName = "chrome"; //1 - Chrome,2 - FF,3 - Edge,4 - IE

	public static void main(String[] args) throws Exception {
		invokeBrowser();
		browserSetting();
		navigateURL();
		getPageInfo();
		faceBookLogin();
		//obj.closeBrowser();
	}
	
	public static void invokeBrowser() {
		switch (browserName) {
		case "chrome":
			System.out.println("User option is : "+browserName+",So invoking Chrome browser");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.out.println("User option is : "+browserName+",So invoking Firefox browser");
			driver = new FirefoxDriver();
			break;
		case "edge":
			System.out.println("User option is : "+browserName+",So invoking Edge browser");
			driver = new EdgeDriver();
			break;
		case "ie":
			System.out.println("User option is : "+browserName+",So invoking IE browser");
			driver = new InternetExplorerDriver();
			break;

		default:
			System.out.println("User option is wrong: "+browserName+",So invoking the default Chrome browser");
			driver = new ChromeDriver();
			break;
		}
	}
	
	public static void browserSetting() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public static void navigateURL() {
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public static void getPageInfo() {
		//To Get Title of the page
		System.out.println("Page title is : "+driver.getTitle());
		// To get the current url
		System.out.println("Current URL is : "+driver.getCurrentUrl());
	}
	
	public static void faceBookLogin() throws Exception {
		WebElement email,button;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		email = (WebElement) js.executeScript("return document.querySelector('#email')");
		highLightElement(driver, email);
		email.sendKeys("Mathan");
		
		js.executeScript("document.getElementById('pass').value='Testing123'");
		
		button = driver.findElement(By.name("login"));
		highLightElement(driver, button);
		js.executeScript("arguments[0].click()", button);
		
	}
	
	public static void highLightElement(WebDriver driver,WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border='3px solid red';",element);
	}
	
		
	public static void closeBrowser() {
		driver.quit();
	}
	
}
