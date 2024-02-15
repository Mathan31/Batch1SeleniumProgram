package day11;

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

public class EbayScrollJSExecutor {
	
	public static WebDriver driver;
	public static int browser = 8; // 1 - Chrome, 2 - Firefox,3 - Edge,4 - IE,5 - Opera
	public static String sURL = "https://www.ebay.com/";

	public static void main(String[] args) {
		invokeBrowser();
		browserSettings();
		navigateURL();
		getPageInfo();
		searchProduct("iPhone", "Cell Phones & Accessories");
		getSearchResult();
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
	
	public static void searchProduct(String searchTxt,String searchCat) {
		WebElement oText,oDrop,oBtn;
		oText = driver.findElement(By.id("gh-ac"));
		oText.sendKeys(searchTxt);
		
		oDrop = driver.findElement(By.id("gh-cat"));
		Select select = new Select(oDrop);
		select.selectByVisibleText(searchCat);
		
		oBtn = driver.findElement(By.id("gh-btn"));
		oBtn.click();
	}
	
	public static void getSearchResult() {
		WebElement oResult;
		oResult = driver.findElement(By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]"));
		String sResult = oResult.getText();
		sResult = sResult.replaceAll("[^0-9]", "");
		System.out.println("Search Result is : "+sResult);
		int iResult = Integer.parseInt(sResult);
		if(iResult > 0) {
			System.out.println("Search results are available....");
			getSearchResultList();
		}else {
			System.out.println("Search results are not available....");
		}
	}
	
	public static void getSearchResultList() {
		List<WebElement> resultLists = driver.findElements(By.
				xpath("(//ul[@class='srp-results srp-list clearfix']/li[contains(@class,'s-item s-item')])"));
		for (WebElement resultList : resultLists) {
			WebElement product = resultList.findElement(By.xpath(".//a[@class='s-item__link']"));
			int x = product.getRect().getX();
			int y = product.getRect().getY();
			scrollElementToUsingCoordinate(driver, x, y);
			String productName = product.getText();
			System.out.println(productName);
		}
		scrollElementToUsingCoordinate(driver, 0, 0);
	}
	
	
	
	public static void scrollElementToUsingCoordinate(WebDriver driver,int x,int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String cmd = String.format("window.scrollTo(%d,%d)", x,y);
		js.executeScript(cmd);
	}
		
	public static void closeBrowser() {
		driver.quit();
	}
}
