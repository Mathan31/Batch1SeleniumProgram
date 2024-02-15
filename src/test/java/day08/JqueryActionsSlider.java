package day08;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class JqueryActionsSlider {
	
	public static WebDriver driver;
	public static String sURL = "https://jqueryui.com/slider/";
	public static String browserName = "chrome"; //1 - Chrome,2 - FF,3 - Edge,4 - IE

	public static void main(String[] args) {
		invokeBrowser();
		browserSetting();
		navigateURL();
		getPageInfo();
		slideElement();
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
	
	public static void slideElement() {
		WebElement oLine,oSlider;
		driver.switchTo().frame(0);
		oLine = driver.findElement(By.id("slider"));
		oSlider = driver.findElement(By.xpath("//div[@id='slider']/span"));
		int width = oLine.getRect().getWidth();
		Actions actions = new Actions(driver);
		actions.clickAndHold(oSlider).moveByOffset(width/2, 0).perform();
	}
	

	public static void closeBrowser() {
		driver.quit();
	}
	
}
