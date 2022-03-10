package coms.selenium.hackathon;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.hackathon.util.ExtentReportManager;
import org.testng.Assert;

import com.hackathon.util.DateUtil;

public class BaseUI {

	public static WebDriver driver;
	public Properties prop;

	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;

	public void getDriver(String Browser) {

		if (Browser.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver(options);
		} else if (Browser.equalsIgnoreCase("Mozila")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (Browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);

		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
				prop.load(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void hoverMouse(String Xpath) {
		try {
			WebElement ElementLink = driver.findElement(By.xpath(prop.getProperty(Xpath)));
			Actions action = new Actions(driver);
			action.moveToElement(ElementLink).build().perform();
		} catch (Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
			Assert.fail("Failing the TestCase : " + e.getMessage());
		}
	}

	public void selectFromDropDown(String Xpath, String option) {
		try {
			Select select = new Select(driver.findElement(By.xpath(Xpath)));
			select.selectByVisibleText(option);
		} catch (Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
			Assert.fail("Failing the TestCase : " + e.getMessage());
		}
	}

	public void explicitWait(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath))));
	}
	public void newBike(String newBike) {

	}
	
	//mouseover
	public void mouseover(String newBike_Xpath,String UpcommingBike_Xpath) throws InterruptedException {
	WebElement mainMenu= getElement(newBike_Xpath);
	//WebElement mainMenu = driver.findElement(By.xpath("//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[3]/a"));
	Actions actions = new Actions(driver);
	actions.moveToElement(mainMenu).perform();
	Thread.sleep(3000);
	WebElement subMenu = getElement(UpcommingBike_Xpath);
	actions.moveToElement(subMenu);
	actions.click().build().perform();
	Thread.sleep(5000);
	}
	//dropdown
	public void DropDown(String dropdown_Xpath) {
		WebElement drpdown=getElement(dropdown_Xpath);
		Select name = new Select(drpdown);
		name.selectByVisibleText("Honda");
	}
	
	public void UsedCar(String usedCar_Xpath,String Chennai_Xpath) throws InterruptedException {
	WebElement usdCar=getElement(usedCar_Xpath);
	Actions actions = new Actions(driver);
	actions.moveToElement(usdCar).perform();
	Thread.sleep(3000);
	WebElement chnCar = getElement(Chennai_Xpath);
	actions.moveToElement(chnCar);
	actions.click().build().perform();
	Thread.sleep(3000);
	}
	
	//display list of bike
	public void listelement() throws InterruptedException {
		
		for(int i =1;i<14;i++) {
		String price = driver.findElement(By.xpath("/html/body/main/div/div/div[1]/div[1]/div[2]/ul/li["+i+"]/div/div[3]/div[1]")).getText();
		String []arrofstr= price.split(" ");
		if(Double.parseDouble(arrofstr[1])<4.00) {
			List<WebElement> elements= driver.findElements(By.xpath("/html/body/main/div/div/div[1]/div[1]/div[2]/ul/li["+i+"]/div/div[3]"));

			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			WebElement q;
			System.out.println(" List of bike");
		
			if(elements.size()!=0) {
			for(int j=0;j<elements.size();j++) {
				q= elements.get(j);
				
				System.out.println((j+1)+"."+q.getText()+"\n");
				Thread.sleep(3000);
			}
			}
			
		}
		else {
			System.out.println("out of 400000");
			Thread.sleep(1000);
		}
		
		
		}
		
	}
	

	public void getURL(String URL) {
		driver.get(prop.getProperty(URL));
	}

	// Close the tab
	public void tearDown() {
		driver.close();
	}

	// Quit the browser
	public void quitBrowser() {
		driver.quit();
	}

	// Click method
	public void click(String XpathClick) {
		driver.findElement(By.xpath(XpathClick)).click();

	}
	
	public void list() throws InterruptedException {
		List<WebElement> elements= driver.findElements(By.xpath("//*[@type=\"checkbox\" and @class='carmmCheck']"));
		System.out.println(elements.size());
		
		for(int i=0;i<elements.size();i++) {
			elements.get(i).click();
			Thread.sleep(3000);
		}
		
	}

	// Get Element Method
	public WebElement getElement(String locatorkey) {
		WebElement element = null;

		try {
			if (locatorkey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(locatorkey)));
			} else if (locatorkey.endsWith("_Id")) {
				element = driver.findElement(By.id(prop.getProperty(locatorkey)));
			} else if (locatorkey.endsWith("_CSS")) {
				element = driver.findElement(By.cssSelector(prop.getProperty(locatorkey)));
			} else if (locatorkey.endsWith("_Name")) {
				element = driver.findElement(By.name(prop.getProperty(locatorkey)));
			} else if (locatorkey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText(prop.getProperty(locatorkey)));
			} else if (locatorkey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(prop.getProperty(locatorkey)));
			} else {
				reportFail("Failing the TestCase, Invalid Locator " + locatorkey);
				Assert.fail();
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
			Assert.fail("Failing the TestCase : " + e.getMessage());
		}

		return element;
	}

	// Reporting Functions
	public void reportFail(String reportString) {

	}

	public void reportPass(String reportString) {

	}

	// Take ScreenShot
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File SourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(
				System.getProperty("user.dir") + "\\target\\Screenshots\\" + DateUtil.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(SourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "\\target\\Screenshots\\" + DateUtil.getTimeStamp() + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/********* Scroll Down page ***************/
	public void pageScrollDown() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement el = driver.findElement(By.xpath("//*[@id=\"shortlist_2418\"]"));
		js.executeScript("arguments[0].scrollIntoView();", el);
		driver.findElement(By.xpath("//*[@id=\"modelList\"]/li[15]/span")).click();

	}
	public void pageScrollUp() {



		JavascriptExecutor js = (JavascriptExecutor) driver;



		js.executeScript("window.scrollBy(0,-800)");
		}
		}


