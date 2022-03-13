package com.newBikes.pageModel;

import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.newBikes.base.baseUI;

public class UsedCars extends baseUI{

//To open zigwheels.com
public  void setupWebsite(String browser) {
invokeBrowser(browser);
openUrl("https://www.zigwheels.com/");
}

//Find Used Cars
public static void findUsedCars(String path) {

Actions actions = new Actions(driver);
WebElement usedCarsOption = driver.findElement(By.xpath(path));
   
//Mouse hover on UsedCars
    actions.moveToElement(usedCarsOption).perform();
}

//Select Location Chennai
public  void selectLocation(String path) {

setWaitByVisibility(path);
driver.findElement(By.xpath(path)).click();
}


//Printing models
public static ArrayList<String> printModels(String path,String city) throws Exception {

String models_list=driver.findElement(By.xpath(path)).getText();
ArrayList<String> models=new ArrayList<String>();
Collections.addAll(models, models_list.split("\n"));

//Printing the Popular used Cars in Chennai
System.out.println("Popular Models of Used Cars in "+city+" are:-");
for(int i =0; i< models.size(); i++){
System.out.println((i+1)+" "+models.get(i));
}
return models;
}

}
