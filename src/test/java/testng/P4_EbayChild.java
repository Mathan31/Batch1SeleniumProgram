package testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class P4_EbayChild extends P4_BaseClass{
	
	public  String sURL = "https://www.ebay.com/";
	
	@BeforeTest
	public void setUp() {
		iBrowserType = 8;
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
	
	@Test(priority = 4)
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

}
