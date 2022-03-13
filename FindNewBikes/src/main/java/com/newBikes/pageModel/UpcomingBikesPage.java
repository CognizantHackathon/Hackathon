package com.newBikes.pageModel;


import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UpcomingBikesPage extends NewBikesPage {

//To select "Honda" from the manufacturer dropdown list
public void selectManufacturer() throws InterruptedException{
mouseHover();
WebElement sManufacturer=driver.findElement(By.id("makeId"));
Select dropdown=new Select(sManufacturer);
dropdown.selectByVisibleText("Honda");
        Thread.sleep(3000);
}

//To select upcoming honda bikes under 4 lakhs
public static ArrayList<String> getHondaBikesUnderBudget(Double budget) throws InterruptedException {

clickByVisibleElement("//span[@class='zw-cmn-loadMore','View More Bikes')]");

//Storing the information of all the Upcoming Honda Bikes
waitImplicit();
String bikeModels =driver.findElement(By.xpath("//*[@id='carModels']/ul")).getText();

//Storing the info in an ArrayList
waitImplicit();
ArrayList<String> bikeModelsElements = new ArrayList<String>();
Collections.addAll(bikeModelsElements,bikeModels.split("\n"));

//Sorting the information according to names,dates and prices
ArrayList<String> NameList = new ArrayList<String>();
ArrayList<String> DateList = new ArrayList<String>();
ArrayList<String> PriceList =new ArrayList<String>();
String[] arr = null;
for(int i = 0 ; i < bikeModelsElements.size(); i++){
String s = bikeModelsElements.get(i);
if(s.contains("Honda")){
NameList.add(s);
}
if(s.contains("Rs. ")){
arr = s.split(" ");
PriceList.add(arr[1]);
}
if(s.contains("Exp. Launch")){
DateList.add(s);
}
}

waitImplicit();
ArrayList<String> upcomingBikes = new ArrayList<String>();
for(int i=0; i<NameList.size();i++){
String temp = NameList.get(i);
double price = Double.parseDouble(PriceList.get(i));
String info=temp+"  "+PriceList.get(i)+" Lakh  "+DateList.get(i);
if(info.contains(temp)){
if(Double.compare(price, budget)<0){
upcomingBikes.add(info);
}
}
}

//Printing the results
System.out.println("Upcoming Honda Bikes under 4 Lakhs: ");
for(int i = 0 ; i < upcomingBikes.size(); i++){
System.out.println(upcomingBikes.get(i));
}
return upcomingBikes;
}

//To select bikes by budget
public void selectBudget(String xpath) throws InterruptedException{
mouseHover();
clickByVisibleElement(xpath);
waitImplicit();
}
}