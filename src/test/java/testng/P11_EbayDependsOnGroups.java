package testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class P11_EbayDependsOnGroups {
	
	public  WebDriver driver;
	public  int iBrowserType = 1; //1- chrome,2 - edge,3 - FF,4 - IE
	public  String sURL = "https://www.ebay.com/";
	
	
	@BeforeClass(groups = {"Smoke","Sanity","Regression"})
	public  void invokeBrower() {
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
	
	@Test(priority = 1,groups = "Regression")
	public  void browserSetting() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@Test(priority = 2,groups = {"Smoke","Sanity","Regression"})
	public  void navigateURL() {
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current url is : "+currentUrl);
		if(currentUrl.equals("ebay")) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 3,groups = {"Smoke","Sanity","Regression"})
	public  void getPageInfo() {
		// To get the title of the page
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		
	}
	
	@Test(priority = 4,groups = {"Sanity","Regression"},dependsOnGroups = "Smoke")
	public  void searchProduct() {
		WebElement oSearch,oDropdown,oBtn;
		oSearch = driver.findElement(By.id("gh-ac"));
		oSearch.sendKeys("iPhone");
		
		oDropdown = driver.findElement(By.id("gh-cat"));
		Select select = new Select(oDropdown);
		select.selectByVisibleText("Cell Phones & Accessories");
		
		oBtn = driver.findElement(By.id("gh-btn"));
		oBtn.click();
	}
	
	@Test(priority = 5,groups = "Regression",dependsOnGroups = "Smoke")
	public void getSearchResult() {
		WebElement oResult;
		oResult = driver.findElement(By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]"));
		String resultText = oResult.getText();
		System.out.println("Result Text is : "+resultText);
		resultText = resultText.replaceAll("[^0-9]", ""); //450000
		int iResult = Integer.parseInt(resultText);
		System.out.println("Result Text after converting to an int is : "+iResult);
		if(iResult > 0) {
			System.out.println("Result list is available.");
		}else {
			System.out.println("Result list is not available for the searched product.");
		}
	}
	
	@AfterClass(groups = {"Smoke","Sanity","Regression"})
	public  void closeBrowser() {
		driver.quit();
	}

}
