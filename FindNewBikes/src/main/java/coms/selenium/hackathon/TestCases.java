package coms.selenium.hackathon;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class TestCases extends BaseUI {

	@Test
	public void testOne() {

		logger = report.createTest("test One");
		logger.log(Status.INFO, "Open The Browser");
		getDriver("mozila");
		logger.log(Status.INFO, "Open The Site URL");
		getURL("URL");
		logger.log(Status.INFO, "Quit the Browser Tab");
		tearDown();
		logger.log(Status.PASS, "All test cases pass successfully");

	}

	@AfterMethod
	public void endBrowser() {
		report.flush();
	}
}
