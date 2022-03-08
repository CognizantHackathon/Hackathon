package coms.selenium.hackathon;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.hackathon.util.ExtentReportManager;

import org.testng.Assert;

import com.hackathon.util.DateUtil;
public class BaseUI {

	public static WebDriver driver ;
	public Properties prop;

	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;

	public void getDriver(String Browser) {

		if (Browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("Mozila")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (Browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);

		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
				prop.load(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void upcomingBike(String newBike) throws InterruptedException {
		
		WebElement newBikeLink=driver.findElement(By.xpath(prop.getProperty(newBike)));
		Actions action = new Actions(driver);
		action.moveToElement(newBikeLink).build().perform();
		// use explicit wait in future
		WebElement upcomingBikes = driver.findElement(By.xpath(prop.getProperty(newBike)));
		upcomingBikes.click();
		//Thread.sleep(40000);
		
	
		
	}

	public void getURL(String URL) {
		driver.get(prop.getProperty(URL));
	}

	// Close the tab
	public void tearDown() {
		driver.close();
	}

	// Quit the browser
	public void quitBrowser() {
		driver.quit();
	}

	// Click method
	public void click(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	// Get Element Method
	public WebElement getElement(String locatorkey) {
		WebElement element = null;
		
		try {
			if (locatorkey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(locatorkey)));
			} else if (locatorkey.endsWith("_Id")) {
				element = driver.findElement(By.id(prop.getProperty(locatorkey)));
			} else if (locatorkey.endsWith("_CSS")) {
				element = driver.findElement(By.cssSelector(prop.getProperty(locatorkey)));
			} else if (locatorkey.endsWith("_Name")) {
				element = driver.findElement(By.name(prop.getProperty(locatorkey)));
			} else if (locatorkey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText(prop.getProperty(locatorkey)));
			} else if (locatorkey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(prop.getProperty(locatorkey)));
			} else {
				reportFail("Failing the TestCase, Invalid Locator " + locatorkey);
				Assert.fail();
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
			Assert.fail("Failing the TestCase : " + e.getMessage());
		}

		return element;
	}

	// Reporting Functions
	public void reportFail(String reportString) {

	}

	public void reportPass(String reportString) {

	}
	
	//Take ScreenShot
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot = (TakesScreenshot)driver;
		File SourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "\\target\\Screenshots\\" + DateUtil.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(SourceFile,destFile);
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "\\target\\Screenshots\\" + DateUtil.getTimeStamp() + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
