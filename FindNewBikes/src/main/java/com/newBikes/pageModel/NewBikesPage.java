package com.newBikes.pageModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.newBikes.base.baseUI;

public class NewBikesPage extends baseUI {

//To load the main site
	public void Load_Home_Page(String browser) throws InterruptedException {
		invokeBrowser(browser);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		openUrl("https://www.zigwheels.com/");
	}

//To search "Upcoming Honda Bikes" in the search box
	public void searchBox1(String searchText) throws InterruptedException {
		enterText("//*[@id=\"headerSearch\"]", searchText);
		clickXpath("//*[@id='search-Sml']/span[3]");
		Thread.sleep(3000);
		clickXpath("//*[@id=\"main_section\"]/div[2]/ul/li/a");
		Thread.sleep(20000);
	}

//To search "Upcoming Honda Bikes Under 4 Lakhs" in the search box
	public void searchBox2(String searchText) throws InterruptedException {
		enterText("//*[@id=\"headerSearch\"]", searchText);
		clickXpath("//*[@id='search-Sml']/span[3]");
		Thread.sleep(20000);
		String resultText = driver.findElement(By.xpath("//*[@id=\"content_search_result_div\"]/div[2]")).getText();
		System.out.println(resultText);
	}

//To select "Upcoming Bikes" option through mouse hover
	public void mouseHover() throws InterruptedException {
		WebElement newBikes = driver.findElement(By.partialLinkText("New Bikes"));
		Actions builder = new Actions(driver);
		builder.moveToElement(newBikes).build().perform();
		WebElement upcomingBikes =driver.findElement(By.partialLinkText("Upcoming Bikes"));
		upcomingBikes.click();
		WebElement drpdown = driver.findElement(By.xpath("//*[@id=\"makeId\"]"));
		Select name = new Select(drpdown);
		name.selectByVisibleText("Honda");
		Thread.sleep(3000);
	}
	

//To print all upcomming less than 4lakh in console..
	public void printUpcommingBikes() throws InterruptedException {

		List<WebElement> elements = driver.findElements(By.xpath("/html/body/main/div/div/div[1]/div[1]/div[2]/ul/li[*]/div/div[3]"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement q;
		System.out.println(" List of bike");
		if (elements.size() != 0) {
			for (int j = 0; j < elements.size(); j++) {
				q = elements.get(j);
				System.out.println((j + 1) + "." + q.getText() + "\n");
				Thread.sleep(3000);
			}
		}

	}

}
