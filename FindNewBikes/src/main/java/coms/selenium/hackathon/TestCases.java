package coms.selenium.hackathon;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.hackathon.util.ExtentReportManager;

public class TestCases extends BaseUI {

	@Test
	public void testOne() throws InterruptedException {

		logger = report.createTest("test One");
		report = ExtentReportManager.getReportInstance();
		ExtentTest logger = report.createTest("test One");

		logger.log(Status.INFO, "Open The Browser");
		getDriver("chrome");
		logger.log(Status.INFO, "Open The Site URL");
		getURL("URL");
		mouseover("newBike_Xpath", "UpcommingBike_Xpath");
		DropDown("dropdown_Xpath");
		pageScrollDown();
		 pageScrollUp();
		Thread.sleep(5000);
		 listelement();
		// pageScrollUp();
		UsedCar("usedCar_Xpath", "Chennai_Xpath");
		//list();
		logger.log(Status.INFO, "Quit the Browser Tab");
		tearDown();
		logger.log(Status.PASS, "All test cases pass successfully");

	}


	@AfterMethod
	public void endBrowser() {
		report.flush();
	}
}
