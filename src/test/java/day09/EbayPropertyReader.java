package day09;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import wrapper.PropertyReader;

public class EbayPropertyReader {
	
	public static WebDriver driver;
	public static String propFileName = "environment";
	public static int iBrowserType = Integer.parseInt(PropertyReader.readDataFromProperty(propFileName, "browser"));
	public static String sURL = PropertyReader.readDataFromProperty(propFileName, "liveURL");

	public static void main(String[] args) {
		invokeBrower();
		browserSetting();
		navigateURL();
		getPageInfo();
		searchProduct("IPhone","Cell Phones & Accessories");
		//closeBrowser();
	}
	
	public static void invokeBrower() {
		switch (iBrowserType) {
		case 1:
			System.out.println("User option is : "+iBrowserType+", So invoking chrome browser.");
			driver = new ChromeDriver();
			break;
		case 2:
			System.out.println("User option is : "+iBrowserType+", So invoking edge browser.");
			driver = new EdgeDriver();
			break;
		case 3:
			System.out.println("User option is : "+iBrowserType+", So invoking FF browser.");
			driver = new FirefoxDriver();
			break;
		case 4:
			System.out.println("User option is : "+iBrowserType+", So invoking IE browser.");
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("User option is wrong : "+iBrowserType+", So invoking the default chrome browser.");
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
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}
	
	public static void getPageInfo() {
		// To get the title of the page
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		// Too get the current url
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current url is : "+currentUrl);
	}
	
	public static void searchProduct(String prodName,String prodCat) {
		WebElement oSearch,oDropdown,oBtn;
		oSearch = driver.findElement(By.id("gh-ac"));
		oSearch.sendKeys(prodName);
		
		oDropdown = driver.findElement(By.id("gh-cat"));
		Select select = new Select(oDropdown);
		select.selectByVisibleText(prodCat);
		
		oBtn = driver.findElement(By.id("gh-btn"));
		oBtn.click();
	}
	
	
	public static void closeBrowser() {
		driver.quit();
	}
	
}
