package day08;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class HDFCSwitchToWindowBasedonHandler {
	
	public static WebDriver driver;
	public static String sURL = "https://www.hdfcbank.com/";
	public static String browserName = "chrome"; //1 - Chrome,2 - FF,3 - Edge,4 - IE

	public static void main(String[] args) throws Exception {
		invokeBrowser();
		browserSetting();
		navigateURL();
		getPageInfo();
		swithToWindowBasedOnIndex();
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
	
	public static void swithToWindowBasedOnIndex() throws Exception {
		WebElement oPension,oMutual;
		WebDriverWait oWait = new WebDriverWait(driver, Duration.ofSeconds(20));
		oWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='HDFC Pension']")));
		oPension = driver.findElement(By.xpath("//span[text()='HDFC Pension']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(oPension).perform();
		System.out.println("Window count before clicking on Pension link : "+driver.getWindowHandles().size());
		oPension.click();
		oWait.until(ExpectedConditions.numberOfWindowsToBe(2));
		// Switch the window from zero index to first index
		//driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		swithToWindowUsingHandler();
		getPageInfo();
		System.out.println("Window count after clicking on Pension link : "+driver.getWindowHandles().size());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//p[contains(text(),'Copyright 2019 All rights reserved')]")));
		Thread.sleep(2000);
		oWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='HDFC Mutual Fund']")));
		System.out.println("Window count before clicking on mutual link : "+driver.getWindowHandles().size());
		oMutual = driver.findElement(By.xpath("//a[text()='HDFC Mutual Fund']"));
		oMutual.click();
		oWait.until(ExpectedConditions.numberOfWindowsToBe(3));
		// Switch the window from zero index to first index
		//driver.switchTo().window(driver.getWindowHandles().toArray()[2].toString());
		swithToWindowUsingHandler();
		getPageInfo();
		System.out.println("Window count after clicking on Mutual link : "+driver.getWindowHandles().size());
		// Switch the window from zero index to first index
		driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
		driver.close();
		System.out.println("Window count after closing Home page : "+driver.getWindowHandles().size());
		driver.quit();
	}
	
	public static void swithToWindowUsingHandler() {
		String currentWindow = driver.getWindowHandle(); // abc
		String newWindow = null;
		Set<String> windows = driver.getWindowHandles(); // abc,xyz
		for (String window : windows) {
			if(!currentWindow.equals(window)) {
				newWindow = window;
			}
		}
		driver.switchTo().window(newWindow);
	}
	
		
	public static void closeBrowser() {
		driver.quit();
	}
	
}
