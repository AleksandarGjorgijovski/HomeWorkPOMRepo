package base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Base {
	//definiranje na driver-ot, definiranje na homeUrl
	public static WebDriver driver;
	public String homeUrl = "https://demo.nopcommerce.com/";
	
	public void testSetup() {
		//slicno kako @BeforeMethod - seitranje na driver-ot, otvaranje na URL i setiranje na browser-ot 
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(homeUrl);
		driver.manage().window().maximize();
	}
	public void testTeardown() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	public static void captureScreenshot(String screenshotName ) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./Screenshots/" + screenshotName + ".png"));
			System.out.println("Screenshot taken");
		} catch (Exception e) {
			System.out.println("Exception while taking screenshots" + e.getMessage());
		}
	}
	public static void captureFullScreenshot(String screenshotName ) throws IOException {
		Screenshot screenshot =new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(),"PNG",new File("./Screenshots/" + screenshotName + ".png"));
	}
	public static void captureScreenshotURL(String screenshotName ) throws IOException, AWTException {
		   Robot robot = new Robot();
		   BufferedImage screenShot = robot.createScreenCapture(new java.awt.Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		   ImageIO.write(screenShot, "JPG", new File("./Screenshots/" + screenshotName + ".png"));
	}
}



