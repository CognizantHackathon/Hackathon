package com.zigweels.testupcommingbikes;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.newBikes.base.WriteExcelSheet;
import com.newBikes.pageModel.UpcomingBikesPage;

public class UpcommingBikesTest extends UpcomingBikesPage {

	@Test(priority = 1)
	public void Test1() throws InterruptedException, IOException {
		Load_Home_Page("Mozilla");
		selectManufacturer();
		String pageTitle = driver.getTitle();

		ExtentTest logger = report.createTest("Upcoming Bikes Page - Test 1");
		if (pageTitle.equals("Upcoming Bikes in India 2022/23, See Price, Launch Date, Specs @ ZigWheels")) {
			logger.log(Status.PASS, "Test Executed Successfully");
		} else {
			logger.log(Status.FAIL, "Test Failed");
		}
			takeScreenshot(driver);
		quitBrowser();
	}
	@Test(priority = 2)
	public void Test3() throws InterruptedException, IOException {
//Test to get Bikes under 4 lacs
		Double budget = 4d;
		Load_Home_Page("Chrome");
		selectManufacturer();
		ArrayList<String> bikes = getHondaBikesUnderBudget(budget);
		WriteExcelSheet.writeData(bikes, "Upcoming Bikes", "Upcoming Bikes Under 4 lacs:", "upcomingbikes4lacs.xlsx");
		ExtentTest logger = report.createTest("Upcoming Bikes Page - Test 3");
		logger.log(Status.PASS, "Test Executed Successfully");
		takeScreenshot(driver);
		quitBrowser();
	}

	@Test(priority = 3)
	public void Test4() throws InterruptedException, IOException {
//Test to get Bikes under 2 lacs
		Double budget = 2d;
		Load_Home_Page("Mozilla");
		selectManufacturer();
		ArrayList<String> bikes = getHondaBikesUnderBudget(budget);
		WriteExcelSheet.writeData(bikes, "Upcoming Bikes", "Upcoming Bikes Under 2 lacs", "upcomingbikes2lacs.xlsx");
		ExtentTest logger = report.createTest("Upcoming Bikes Page - Test 4");
		logger.log(Status.PASS, "Test Executed Successfully");
		takeScreenshot(driver);
		quitBrowser();
	}
}
