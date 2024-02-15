package steps;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AmazonSearchStep {
	
	public WebDriver driver;
	public int iBrowserType = 1; //1- chrome,2 - edge,3 - FF,4 - IE
	public String sURL = "https://www.amazon.in/";
	
	@Given("Launch Chrome Browser for Amazon")
	public void launch_chrome_browser_for_amazon() {
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

	@Given("Navigate to Amazon URL")
	public void navigate_to_amazon_url() {
		driver.navigate().to(sURL);
		System.out.println("Title is : "+driver.getTitle());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); // Wait until page loads
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@When("Search the amazon product and Select the Catagory")
	public void search_the_amazon_product_and_select_the_catagory() {
		WebElement oProduct,oDropDown;
		oProduct = driver.findElement(By.id("twotabsearchtextbox"));
		oProduct.clear();
		oProduct.sendKeys("Selenium");
		
		oDropDown = driver.findElement(By.id("searchDropdownBox"));
		Select oSelect = new Select(oDropDown);
		oSelect.selectByVisibleText("Books");
	}

	@When("Click on amaon Search Button")
	public void click_on_amaon_search_button() {
	   WebElement oButton = driver.findElement(By.id("nav-search-submit-button"));
		oButton.click();
	}

	@Then("User should see the amazon search result")
	public void user_should_see_the_amazon_search_result() {
		WebElement oResult;
		oResult = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small']/span)[1]"));
		String sResult = oResult.getText(); //530,000
	    System.out.println("Result is : "+sResult);
	}

	@Then("Close amazon Browser")
	public void close_amazon_browser() {
	   driver.quit();
	}




}
