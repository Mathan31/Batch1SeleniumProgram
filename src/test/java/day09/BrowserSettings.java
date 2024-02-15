package day09;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserSettings {

	public static void main(String[] args) {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("start-maximized");
		option.addArguments("incognito");
		option.setAcceptInsecureCerts(true);
		option.addArguments("--headless=new");
		
//		Proxy proxy = new Proxy();
//		proxy.setHttpProxy("100.200.300.400");
//		option.setProxy(proxy);
		
//		DesiredCapabilities dc = new DesiredCapabilities();
//		dc.setPlatform(Platform.WINDOWS);
		
		WebDriver driver = new ChromeDriver(option);
		//Override methods from ChromeDriver,methods from Webdriver and Object class methods
		driver.manage().deleteAllCookies(); // To delete all the cookies in the browser
		
		driver.get("https://www.credosystemz.com/"); // To launch the page
		
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL is : "+currentUrl);
		
		driver.quit();
	}

}
