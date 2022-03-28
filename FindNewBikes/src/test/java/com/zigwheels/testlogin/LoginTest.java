
package com.zigwheels.testlogin;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.newBikes.base.readDataSheet;
import com.newBikes.pageModel.LoginPage;

public class LoginTest extends LoginPage {

//Method to get cell data from excel
	public static String getData(int colNum, int rowNum) throws Exception {
		readDataSheet dataprovider = new readDataSheet();
		dataprovider.openWorkbook("Email_password_Dataset.xlsx");
		return dataprovider.getCellData("Sheet1", colNum, rowNum);
	}

	@Test(priority = 1)
	public void testOne() throws Exception {

		String email = getData(0, 1);
		setupWebsite("chrome");
		openLoginWindow("Google");
		enterCredentials(email);
		String pageTitle = driver.getTitle();

		ExtentTest logger = report.createTest("Login Page - Test 1");
		if (pageTitle.equals("Sign in – Google accounts")) {
			logger.log(Status.PASS, "Test Executed Successfully");
		} else {
			logger.log(Status.FAIL, "Test Failed");
		}
		assertEquals(pageTitle, "Sign in – Google accounts");
		takeScreenshot(driver);
		closeBrowser();
		quitBrowser();

	}

	@Test(priority = 2)
	public void testTwo() throws Exception {
		String email = getData(0, 2);
		setupWebsite("chrome");
		openLoginWindow("Google");
		enterCredentials(email);
		String pageTitle = driver.getTitle();
		ExtentTest logger = report.createTest("Login Page - Test 2");
		if (pageTitle.equals("Sign in – Google accounts")) {
			logger.log(Status.PASS, "Test Executed Successfully");
		} else {
			logger.log(Status.FAIL, "Test Failed");
		}
		assertEquals(pageTitle, "Sign in – Google accounts");
		takeScreenshot(driver);
		closeBrowser();
		quitBrowser();
	}

	@Test(priority = 3)
	public void testThree() throws Exception {
		String email = getData(0, 3);
		setupWebsite("edge");
		openLoginWindow("Google");
		enterCredentials(email);
		String pageTitle = driver.getTitle();
		ExtentTest logger = report.createTest("Login Page - Test 3");
		if (pageTitle.equals("Sign in – Google accounts")) {
			logger.log(Status.PASS, "Test Executed Successfully");
		} else {
			logger.log(Status.FAIL, "Test Failed");
		}
		assertEquals(pageTitle, "Sign in – Google accounts");
		takeScreenshot(driver);
		closeBrowser();
		quitBrowser();

	}

}
