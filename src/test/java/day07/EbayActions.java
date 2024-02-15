package day07;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EbayActions {
	
	public static WebDriver driver;
	public static String sURL = "https://www.ebay.com/";
	public static String browserName = "chrome"; //1 - Chrome,2 - FF,3 - Edge,4 - IE

	public static void main(String[] args) throws Exception {
		invokeBrowser();
		browserSetting();
		navigateURL();
		getPageInfo();
		moveToElement();
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
	
	public static void moveToElement() throws Exception {
		WebElement oSports;
		oSports = driver.findElement(By.xpath("(//a[text()='Sports'])[1]"));
		Actions actions = new Actions(driver);
		//actions.moveToElement(oSports).perform();
		//Thread.sleep(2000);
		//actions.click(driver.findElement(By.xpath("//a[text()='Winter sports']"))).perform();
		//driver.findElement(By.xpath("//a[text()='Winter sports']")).click();
		actions.moveToElement(oSports).pause(Duration.ofSeconds(2))
		.click(driver.findElement(By.xpath("//a[text()='Winter sports']")))
		.perform();
	}
	
		
	public static void closeBrowser() {
		driver.quit();
	}
	
}
