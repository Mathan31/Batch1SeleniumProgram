package steps;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EbaySearchStep {
	
	public WebDriver driver;
	public int iBrowserType = 1; //1- chrome,2 - edge,3 - FF,4 - IE
	public String sURL = "http://www.ebay.com/";
	
	@Given("Launch Chrome Browser")
	public void launchChromeBrower() {
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
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	// Cucumber Expression

	@Given("Launch Browser based on {int}")
	public void launch_browser_based_on(Integer iBrowserType) {
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
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	

	@Given("^Launch Browser based on multiple value ([1-9]+)$")
	public void launch_browser_based_on_multiple_value(Integer iBrowserType) {
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
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	

	@Given("Navigate to Ebay URL")
	public void navigate_to_ebay_url() {
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@When("Search the product and Select the Catagory")
	public void search_the_product_and_select_the_catagory() {
		WebElement oSearch,oDropdown;
		oSearch = driver.findElement(By.id("gh-ac"));
		oSearch.sendKeys("IPhone");
		
		oDropdown = driver.findElement(By.id("gh-cat"));
		Select select = new Select(oDropdown);
		select.selectByVisibleText("Cell Phones & Accessories");
		
		
	}
	
	@When("Search the product as {string} and Select the Catagory as {string}")
	public void search_the_product_as_and_select_the_catagory_as(String prodName, String prodCatagory) {
		WebElement oSearch,oDropdown;
		oSearch = driver.findElement(By.id("gh-ac"));
		oSearch.sendKeys(prodName);
		
		oDropdown = driver.findElement(By.id("gh-cat"));
		Select select = new Select(oDropdown);
		select.selectByVisibleText(prodCatagory);
	}

	@When("^Search the multiple product as (\\w+) and Select the multiple Catagory as ([^1-9]+)$")
	public void search_the_multiple_product_as_selenium_and_select_the_multiple_catagory_as_books(String prodName,String prodCatagory) {
		WebElement oSearch,oDropdown;
		oSearch = driver.findElement(By.id("gh-ac"));
		oSearch.sendKeys(prodName);
		
		oDropdown = driver.findElement(By.id("gh-cat"));
		Select select = new Select(oDropdown);
		select.selectByVisibleText(prodCatagory);
	}
	
	@When("Search the product from the below data table as List")
	public void search_the_product_from_the_below_data_table_as_list(DataTable dataList) {
		List<List<String>> listRow = dataList.asLists();
		for (List<String> row : listRow) {
			String prodName = row.get(0);
			String prodCat = row.get(1);
			WebElement oSearch,oDropdown;
			oSearch = driver.findElement(By.id("gh-ac"));
			oSearch.clear();
			oSearch.sendKeys(prodName);
			
			oDropdown = driver.findElement(By.id("gh-cat"));
			Select select = new Select(oDropdown);
			select.selectByVisibleText(prodCat);
			
			WebElement oBtn = driver.findElement(By.id("gh-btn"));
			oBtn.click();
		}
	}
	

	@When("Search the product from the below data table as Map")
	public void search_the_product_from_the_below_data_table_as_map(DataTable dataMap) {
		List<Map<String, String>> asMaps = dataMap.asMaps();
		for (Map<String, String> map : asMaps) {
			String prodName = map.get("ProductName");
			String prodCat = map.get("ProductCatagory");
			WebElement oSearch,oDropdown;
			oSearch = driver.findElement(By.id("gh-ac"));
			oSearch.clear();
			oSearch.sendKeys(prodName);
			
			oDropdown = driver.findElement(By.id("gh-cat"));
			Select select = new Select(oDropdown);
			select.selectByVisibleText(prodCat);
			
			WebElement oBtn = driver.findElement(By.id("gh-btn"));
			oBtn.click();
		}
	}







	@When("Click on Search Button")
	public void click_on_search_button() {
	    WebElement oBtn = driver.findElement(By.id("gh-btn"));
		oBtn.click();
	}

	@Then("User should see the search result")
	public void user_should_see_the_search_result() {
		WebElement oText;
		oText = driver.findElement(By.xpath("//*[@class='listingscnt']|//*[@class='srp-controls__count-heading']"));
		String sText = oText.getText();
		System.out.println("Search Result is : " + sText);
	}

	@Then("Close Browser")
	public void close_browser() {
	    driver.quit();
	}


}
