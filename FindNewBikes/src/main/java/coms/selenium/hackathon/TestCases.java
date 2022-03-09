package coms.selenium.hackathon;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
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
		mouseover("newBike_Xpath","UpcommingBike_Xpath");
		DropDown("dropdown_Xpath");
		Thread.sleep(5000);
		UsedCar("usedCar_Xpath","Chennai_Xpath");
		logger.log(Status.INFO, "Quit the Browser Tab");
		tearDown();
		logger.log(Status.PASS, "All test cases pass successfully");

	}
//
//@Test
//	public void testTwo() throws InterruptedException {
//		logger = report.createTest("test Two");
//		report=ExtentReportManager.getReportInstance();
//		ExtentTest logger = report.createTest("test Two");
//	
//		logger.log(Status.INFO, "Open The Browser");
//		getDriver("chrome");
//		logger.log(Status.INFO, "Open The Site URL");
//		getURL("URL");
//		logger.log(Status.INFO, "UsedCar and selectChennai");
//		UsedCar(" usedCar_Xpath", "Chennai_Xpath");
//		quitBrowser();
//		
//	}
	
	
		
		
		
		
		
		

	
		
	@AfterMethod
	public void endBrowser() {
		report.flush();
	}
}
