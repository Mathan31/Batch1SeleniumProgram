package day04;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebElementMethods {
	
	public static WebDriver driver;
	public static int iBrowserType = 1; //1- chrome,2 - edge,3 - FF,4 - IE
	public static String sURL = "https://www.google.com/";

	public static void main(String[] args) {
		invokeBrower();
		browserSetting();
		navigateURL();
		getPageInfo();
		searchProduct("Selenium 4");
		closeBrowser();
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
	
	/**
	 * ImplicitlyWait will come in to picture whenever we use driveer.findelement() .
	 * It will wait for provided seconds and it will throw "NoSuchElementException" when it excided the time.
	 * It will check the DOM for every .5Sec(500 ms)to check for the element to be loaded.
	 */
	
	public static void navigateURL() {
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	
	public static void getPageInfo() {
		// To get the title of the page
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		// Too get the current url
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current url is : "+currentUrl);
		System.out.println("Window ID : "+driver.getWindowHandle());
	}
	
	public static void searchProduct(String searchTxt) {
		WebElement oSearch,oBtn,oLink,oGmailLink,oLogo,oHidden;
		oHidden = driver.findElement(By.xpath("//input[@name='sca_esv']"));
		System.out.println("Is Displayed : "+oHidden.isDisplayed());
		oLogo = driver.findElement(By.xpath("//img[@alt='Google']"));
		System.out.println("Logo displayed : "+oLogo.isDisplayed());
		int height = oLogo.getSize().getHeight();
		int width = oLogo.getSize().getWidth();
		System.out.println("Height is : "+height);
		System.out.println("Width is : "+width);
		oGmailLink = driver.findElement(By.xpath("//a[text()='Gmail']"));
		System.out.println("Text of Gmail Link is : "+oGmailLink.getText());
		String fontColor = oGmailLink.getCssValue("color");
		System.out.println("Font Color is : "+fontColor);
		String fontFamily = oGmailLink.getCssValue("font-family");
		System.out.println("Font Family is : "+fontFamily);
		String fontSize = oGmailLink.getCssValue("font-size");
		System.out.println("Font Size is : "+fontSize);
		int x = oGmailLink.getLocation().getX();
		int y = oGmailLink.getLocation().getY();
		System.out.println("X-Coordinate is : "+x+" Y-Coordinate is : "+y);
		oSearch = driver.findElement(By.xpath("//textarea[@title='Search']"));
		System.out.println("Acccessible for Text box : "+oSearch.getAccessibleName());
		System.out.println("Get the Role Attribute : "+oSearch.getAttribute("role"));
		oSearch.sendKeys(searchTxt);
		oBtn = driver.findElement(By.name("btnK"));
		System.out.println("Acccessible for Button : "+oBtn.getAccessibleName());
		System.out.println("Type attribute value is : "+oBtn.getAttribute("type"));
		//oBtn.click();
		oBtn.submit();// When the button is presence inside the form tag then we can use submit.
		oLink = driver.findElement(By.xpath("//h3[text()='Upgrade to Selenium 4']"));
		System.out.println("Acccessible for Link : "+oLink.getAccessibleName());
		oLink.click();
	}
	
	
	
	public static void closeBrowser() {
		driver.quit();
	}
	
}
