package wrapper;

import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenShotWrapper {
	
	public static void takeScreenshotAsFile(WebDriver driver,String fileName) throws Exception {
		File dist = new File("./snap/"+fileName+".png");
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File src =  screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, dist);
	}
	
	public static void takeScreenshotAsByteArray(WebDriver driver,String fileName) throws Exception {
		File dist = new File("./snap/"+fileName+".png");
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		byte[] bytesrc = screenshot.getScreenshotAs(OutputType.BYTES);
		File src = OutputType.FILE.convertFromPngBytes(bytesrc);
		FileUtils.copyFile(src, dist);
	} 
	
	public static void takeScreenshotAsBase64(WebDriver driver,String fileName) throws Exception {
		File dist = new File("./snap/"+fileName+".png");
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		String base64 = screenshot.getScreenshotAs(OutputType.BASE64);
		File src = OutputType.FILE.convertFromBase64Png(base64);
		FileUtils.copyFile(src, dist);
	}
	
	public static void takeScreenshotAsFileByPassingWebElement(WebElement element,String fileName) throws Exception {
		File dist = new File("./snap/"+fileName+".png");
		TakesScreenshot screenshot = (TakesScreenshot)element;
		File src =  screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, dist);
	}
	
	public static String takeScreenshotAsFileWithDynamicPath(WebDriver driver,String fileName) throws Exception {
		String fileLocation = System.getProperty("user.dir")+"/snap/"+fileName+System.currentTimeMillis()+".png";
		System.out.println("File Path : "+fileLocation);
		File dist = new File(fileLocation);
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File src =  screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, dist);
		return fileLocation;
	}
	
	public static void takeFullScreenshotAsFile(WebDriver driver,String sFileName) throws Exception {
		Screenshot oShot = new AShot()
							.shootingStrategy(ShootingStrategies.viewportPasting(1000))
							.takeScreenshot(driver);
		String sPath = System.getProperty("user.dir")+"/snap/"+sFileName+System.currentTimeMillis()+".png";
		File oDes = new File(sPath);
		ImageIO.write(oShot.getImage(), "png", oDes);
	}

}
