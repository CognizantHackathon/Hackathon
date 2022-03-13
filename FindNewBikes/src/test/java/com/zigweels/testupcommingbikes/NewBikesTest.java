package com.zigweels.testupcommingbikes;



import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.newBikes.pageModel.NewBikesPage;
public class NewBikesTest extends NewBikesPage{


@Test(priority=1)
public void Test1() throws InterruptedException{
ExtentTest logger=report.createTest("New Bikes Page - Test 1");
logger.log(Status.INFO,"Initializing the Browser");
Load_Home_Page("Chrome");
logger.log(Status.INFO,"Entering Upcoming Honda Bikes in the search box");
searchBox1("upcoming honda bikes");
assertEquals(driver.getTitle(),"Upcoming Honda Bikes in India 2022/23, See Price, Launch Date, Specs @ ZigWheels");
try {
takeScreenshot(driver);
} catch (IOException e) {
e.printStackTrace();
}
logger.log(Status.PASS,"Test Executed Successfully");
quitBrowser();
}

@Test(priority=2)
public void Test2() throws InterruptedException{
ExtentTest logger=report.createTest("New Bikes Page - Test 2");
logger.log(Status.INFO,"Initializing the Browser");
Load_Home_Page("Mozilla");
logger.log(Status.INFO,"Entering Upcoming Honda Bikes in the search box");
searchBox2("Upcoming Honda Bikes Under 4 Lakhs");
try {
takeScreenshot(driver);
} catch (IOException e) {
e.printStackTrace();
}
logger.log(Status.PASS,"Test Executed Successfully");
quitBrowser();
}

@Test(priority=3)
public void Test3() throws InterruptedException{
ExtentTest logger=report.createTest("New Bikes Page - Test 3");
logger.log(Status.INFO,"Initializing the Browser");
Load_Home_Page("Chrome");
mouseHover();
assertEquals(driver.getTitle(),"Upcoming Honda Bikes in India 2022/23, See Price, Launch Date, Specs @ ZigWheels");
printUpcommingBikes();
try {
takeScreenshot(driver);
} catch (IOException e) {
e.printStackTrace();
}
logger.log(Status.PASS,"Test Executed Successfully");
quitBrowser();
}
}
