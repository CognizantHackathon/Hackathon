package com.zigweels.UsedCars;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.newBikes.base.WriteExcelSheet;
import com.newBikes.pageModel.UsedCars;

public class UsedCarTest extends UsedCars {

@Test
public void extractUsedCarsModel1() throws Exception {
setupWebsite("Chrome");
findUsedCars("//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[6]/a");
selectLocation("//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[6]/ul/li/div[2]/ul/li[5]/a");
String pageTitle= driver.getTitle();
ExtentTest logger=report.createTest("Used Cars Page - Test 1");
if(pageTitle.equals("Used Cars in Chennai - Certified Second Hand Cars for Sale @ ZigWheels"))
{
logger.log(Status.PASS,"Test Executed Successfully");
}
else
{
logger.log(Status.FAIL, "Test Failed");
}

ArrayList<String>models=printModels("//body/div[11]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[2]/div[2]/div[4]", "Chennai");
WriteExcelSheet.writeData(models,"Used Cars","List of Used cars in Chennai", "UsedCarsChennaiTest.xlsx");
assertEquals(pageTitle,"847 Used Cars in Chennai - Certified Second Hand Cars for Sale @ ZigWheels");
takeScreenshot(driver);
quitBrowser();

}




}
