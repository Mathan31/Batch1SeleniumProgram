package day12;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class HerokuShadowDom {
	
	public static WebDriver driver;
	public static int browser = 8; // 1 - Chrome, 2 - Firefox,3 - Edge,4 - IE,5 - Opera
	public static String sURL = "https://the-internet.herokuapp.com/shadowdom";

	public static void main(String[] args) {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		//handlingShadowDomSelenium3();
		handlingShadowDomSelenium4();
		closeBrowser();
	}
	
	public static void invokeBrowser() {
		switch (browser) {
		case 1:
			System.out.println("User option is : "+browser+", So invoking chrome browser");
			driver = new ChromeDriver();
			break;
		case 2:
			System.out.println("User option is : "+browser+", So invoking firefox browser");
			driver = new FirefoxDriver();
			break;
		case 3:
			System.out.println("User option is : "+browser+", So invoking edge browser");
			driver = new EdgeDriver();
			break;
		case 4:
			System.out.println("User option is : "+browser+", So invoking ie browser");
			driver = new InternetExplorerDriver();
			break;

		default:
			System.out.println("User option is wrong : "+browser+", So invoking default chrome browser");
			driver = new ChromeDriver();
			break;
		}
	}
	
	public static void browserSettings() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public static void navigateURL() {
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	public static void getPageInfo() {
		System.out.println("Page title is : "+driver.getTitle());
		System.out.println("Page URL is : "+driver.getCurrentUrl());
	}
	
	
	public static void handlingShadowDomSelenium3() {
		WebElement oText;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = "return document.querySelector('my-paragraph').shadowRoot.querySelector('p > slot')";
		oText = (WebElement) js.executeScript(script);
		System.out.println(oText.getText());
	}
	
	public static void handlingShadowDomSelenium4() {
		WebElement oText;
		oText = driver.findElement(By.xpath("(//my-paragraph)[1]")).getShadowRoot()
				.findElement(By.cssSelector("p > slot"));
		System.out.println(oText.getText());
	}
	
			
	public static void closeBrowser() {
		driver.quit();
	}
}
