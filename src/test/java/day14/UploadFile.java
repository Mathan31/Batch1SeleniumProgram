package day14;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class UploadFile {
	
	public static WebDriver driver;
	public static String sURL = "https://the-internet.herokuapp.com/upload";
	public static String browserName = "chrome"; //1 - Chrome,2 - FF,3 - Edge,4 - IE

	public static void main(String[] args) throws Exception {
		invokeBrowser();
		browserSetting();
		navigateURL();
		getPageInfo();
		//uploadfileUsingSendKeys();
		uploadUsingRobot();
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
	
	public static void uploadfileUsingSendKeys() {
		WebElement upload;
		upload = driver.findElement(By.id("file-upload"));
		upload.sendKeys("C:\\Users\\user\\Desktop\\AppliationDetails.txt");
	}
	
	public static void uploadUsingRobot() throws Exception {
		// copying File path to Clipboard
		// creating object of Robot class
		WebElement button;
		button = driver.findElement(By.id("file-upload"));
		 Actions builder = new Actions(driver);
		 builder.moveToElement(button).click().perform();
		 Thread.sleep(2000);
	    Robot rb = new Robot();
	    StringSelection str = new StringSelection("C:\\Users\\user\\Desktop\\AppliationDetails.txt");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
	 // press Contol+V for pasting
	     rb.keyPress(KeyEvent.VK_CONTROL);
	     rb.keyPress(KeyEvent.VK_V);
	    // release Contol+V for pasting
	    rb.keyRelease(KeyEvent.VK_CONTROL);
	    rb.keyRelease(KeyEvent.VK_V);
	    Thread.sleep(2000);
	    // for pressing and releasing Enter
	    rb.keyPress(KeyEvent.VK_ENTER);
	    rb.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	public static void closeBrowser() {
		driver.quit();
	}
	
}
