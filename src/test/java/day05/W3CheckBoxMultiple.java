package day05;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class W3CheckBoxMultiple {
	
	public static WebDriver driver;
	public static int iBrowserType = 1; //1- chrome,2 - edge,3 - FF,4 - IE
	public static String sURL = "https://www.w3.org/WAI/ARIA/apg/patterns/checkbox/examples/checkbox/";

	public static void main(String[] args) {
		invokeBrower();
		browserSetting();
		navigateURL();
		getPageInfo();
		clickOnMultipleCheckbox();
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
	
	public static void clickOnMultipleCheckbox() {
		List<WebElement> checkBoxes = driver.findElements(By.xpath("//ul[@class='checkboxes']/li"));
		for (WebElement checkbox : checkBoxes) {
			WebElement checkClick = checkbox.findElement(By.xpath("./div"));
			if(checkClick.getAttribute("aria-checked").equalsIgnoreCase("true")){
				System.out.println("Checkbox is already checked and its name is : "+checkClick.getText());
			}else {
				checkClick.click();
			}
			
		}
	}
	
	
	public static void closeBrowser() {
		driver.quit();
	}
	
}
