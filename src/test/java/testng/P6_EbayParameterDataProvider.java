package testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class P6_EbayParameterDataProvider {
	
	public  WebDriver driver;
	
	public  String sURL = "https://www.ebay.com/";
	
	@Parameters("BrowserName")
	@BeforeClass
	public  void invokeBrower(int iBrowserType) {
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
	
	@Test(priority = 1)
	public  void browserSetting() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@Test(priority = 2)
	public  void navigateURL() {
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}
	
	@Test(priority = 3)
	public  void getPageInfo() {
		// To get the title of the page
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		// Too get the current url
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current url is : "+currentUrl);
	}
	
	@DataProvider(name = "SingleParameter")
	public String[] singleData() {
		String[] data = {"iPhone","Lenovo","Samsung"};
		return data;
	}
	
	@DataProvider(name = "MultipleParameter")
	public String[][] multipleData() {
		String[][] data = new String[3][2];
		data[0][0] = "Selenium";
		data[0][1] = "Books";
		
		data[1][0] = "Lenovo";
		data[1][1] = "Cell Phones & Accessories";
		
		data[2][0] = "Java";
		data[2][1] = "Books";
		return data;
	}
	
	//@Test(priority = 4,dataProvider = "SingleParameter")
	public  void searchProduct(String prodName) {
		WebElement oSearch,oDropdown,oBtn;
		oSearch = driver.findElement(By.id("gh-ac"));
		oSearch.clear();
		oSearch.sendKeys(prodName);
		
		oDropdown = driver.findElement(By.id("gh-cat"));
		Select select = new Select(oDropdown);
		select.selectByVisibleText("Cell Phones & Accessories");
		
		oBtn = driver.findElement(By.id("gh-btn"));
		oBtn.click();
	}
	
	@Test(priority = 4,dataProvider = "MultipleParameter")
	public  void searchMultipleProduct(String prodName,String prodCatagory) {
		WebElement oSearch,oDropdown,oBtn;
		oSearch = driver.findElement(By.id("gh-ac"));
		oSearch.clear();
		oSearch.sendKeys(prodName);
		
		oDropdown = driver.findElement(By.id("gh-cat"));
		Select select = new Select(oDropdown);
		select.selectByVisibleText(prodCatagory);
		
		oBtn = driver.findElement(By.id("gh-btn"));
		oBtn.click();
	}
	
	@AfterClass
	public  void closeBrowser() {
		driver.quit();
	}

}
