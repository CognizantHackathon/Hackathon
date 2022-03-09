package coms.selenium.hackathon;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.hackathon.util.ExtentReportManager;


public class TestCases extends BaseUI{
	

	@Test
	public void testOne() throws InterruptedException {

		logger = report.createTest("test One");
		report=ExtentReportManager.getReportInstance();
		ExtentTest logger = report.createTest("test One");

		logger.log(Status.INFO, "Open The Browser");
		getDriver("chrome");
		logger.log(Status.INFO, "Open The Site URL");
		getURL("URL");
//		Alert alt = driver.switchTo().alert();
//		alt.accept();
//		driver.switchTo().activeElement().submit();
//		ChromeOptions o = new ChromeOptions();
//		o.addArguments("--disable-notifications");
//		driver.getWindowHandles();
		
		//Create prefs map to store all preferences 
		Map<String, Object> prefs = new HashMap<String, Object>();

		//Put this into prefs map to switch off browser notification
		prefs.put("profile.default_content_setting_values.notifications", 2);

		//Create chrome options to set this prefs
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		//Thread.sleep(5000);
		//click("PopUp_Xpath");
		//Thread.sleep(5000);
		logger.log(Status.INFO,"Login");
		click("Login_Xpath");
		Thread.sleep(5000);
		//explicitWait("LoginGoogle_Xpath");
		logger.log(Status.INFO,"Login with Google");
		click("LoginGoogle_Xpath");
//		//logger.log(Status.INFO, "Click on element");
//		Thread.sleep(20000);
////		explicitWait("PopUp_Xpath");
//		click("PopUp_Xpath");
//		hoverMouse("newBike_Xpath");
//	//	explicitWait("upcomingBike_Xpath");
//		hoverMouse("upcomingBike_Xpath");
//		click("upcomingBike_Xpath");
//		Thread.sleep(5000);
//		logger.log(Status.INFO, "Quit the Browser Tab");
		//tearDown();
		logger.log(Status.PASS, "All test cases pass successfully");

	}
//	@Test(dependsOnMethods="testOne")
//	public void testTwo() throws InterruptedException {
//		logger = report.createTest("test One");
//		logger.log(Status.INFO, "Open The Browser");
//		getDriver("chrome");
//		logger.log(Status.INFO, "Open The Site URL");
//		getURL("URL");
//		logger.log(Status.INFO, "Click on WebElement(Upcoming Bike)");
//		hoverMouse("newBike_Xpath");
//		Thread.sleep(3000);
//		logger.log(Status.INFO, "taking screenshot");
//		takeScreenShotOnFailure();
//		logger.log(Status.INFO, "Quit the Browser Tab");
//		quitBrowser();
//		logger.log(Status.PASS, "All test cases pass successfully");
//	}
		

	@AfterMethod
	public void endBrowser() {
		report.flush();
	}
}
