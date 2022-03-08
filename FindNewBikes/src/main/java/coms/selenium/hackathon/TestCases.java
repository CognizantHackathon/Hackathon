package coms.selenium.hackathon;

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
		getDriver("mozila");
		logger.log(Status.INFO, "Open The Site URL");
		getURL("URL");
		logger.log(Status.INFO, "Click on element");
		upcomingBike("newBike");
		logger.log(Status.INFO, "Quit the Browser Tab");
		tearDown();
		logger.log(Status.PASS, "All test cases pass successfully");

	}
	@Test(dependsOnMethods="testOne")
	public void testTwo() throws InterruptedException {
		logger = report.createTest("test One");
		logger.log(Status.INFO, "Open The Browser");
		getDriver("chrome");
		logger.log(Status.INFO, "Open The Site URL");
		getURL("URL");
		logger.log(Status.INFO, "Click on WebElement(Upcoming Bike)");
		upcomingBike("newBike");
		logger.log(Status.INFO, "taking screenshot");
		takeScreenShotOnFailure();
		logger.log(Status.INFO, "Quit the Browser Tab");
		quitBrowser();
		logger.log(Status.PASS, "All test cases pass successfully");
	}
		

	@AfterMethod
	public void endBrowser() {
		report.flush();
	}
}
