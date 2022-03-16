package com.newBikes.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import com.aventstack.extentreports.ExtentReports;

public class baseUI {
public static WebDriver driver;
public static String path;

protected static ExtentReports report=ExtentReportManager.getReportInstance("Smoke and Regression Suite Report");
public void invokeBrowser(String browserName) {
//hello
if(browserName.equalsIgnoreCase("chrome")) {
//Creating ChromeDriver
System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\chromedriver.exe");
Map<String, Object> prefs = new HashMap<String, Object>();
prefs.put("profile.default_content_setting_values.notifications", 2);
ChromeOptions options = new ChromeOptions();

//Setting up chrome options to disable automation message
options.setExperimentalOption("prefs", prefs);
options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
options.setExperimentalOption("useAutomationExtension", false);
driver = new ChromeDriver(options);

} else if(browserName.equalsIgnoreCase("Edge")) {
//Setting up edge driver
System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\msedgedriver.exe");
driver = new EdgeDriver();

} 
else if(browserName.equalsIgnoreCase("mozilla")) {
	//Creating FirefoxDriver
	System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\geckodriver.exe");
	FirefoxProfile profile = new FirefoxProfile();
	//Disabling default proxy in firefox
	profile.setPreference("network.proxy.type", 0);
	driver = new FirefoxDriver();

	}else if(browserName.equalsIgnoreCase("safari")) {
}
}



//Method to open a URL
public void openUrl(String url) {
driver.manage().window().maximize();
driver.get(url);

}

//To maximize the browser
public void maximizeBrowser() {
driver.manage().window().maximize();
}

//To close the current window
public void closeBrowser() {
driver.close();
}

//To quit the driver
public void quitBrowser() {
driver.quit();
}

//To enter Text in a element
public void enterText(String xpath, String text) {
driver.findElement(By.xpath(xpath)).sendKeys(text);
}

//TO click on a given xpath
public void clickXpath(String xpath) {
driver.findElement(By.xpath(xpath)).click();
}

//To set explicit wait based on visibility of the element
public void setWaitByVisibility(String path) {
WebDriverWait wait=new WebDriverWait(driver, 20);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
    }

//To set explicit wait based on presence of the element
public void setWaitByPresence(String upath) {
WebDriverWait wait=new WebDriverWait(driver, 20);
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(upath)));

    }

//To set explicit wait based on clickability of the element
public void setWaitByClick(String path) {
WebDriverWait wait=new WebDriverWait(driver, 20);
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));

    }

//To click an element using JS executor
public static void clickByVisibleElement(String xpath) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Find element by link text and store in variable "Element"        
        WebElement Element = driver.findElement(By.xpath(xpath));

        //This will scroll the page till the element is found
        js.executeScript("arguments[0].click()", Element);
    }

//To set implicit wait
public static void waitImplicit() {
//Implicit wait of 15 seconds
driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

}


//To take the screenshots of the browser pages
public static void takeScreenshot(WebDriver driver) throws IOException
{
File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
try
{
SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       String timeOfExecution = sdf.format(timestamp);
FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"\\screenshot\\"+timeOfExecution+".jpg"));
}catch(IOException e){
System.out.println(e.getMessage());
}
}


public String getPropertyValue(String keyword) throws FileNotFoundException {
InputStream input = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
Properties prop = new Properties();

try {
   
   // load a properties file
   prop.load(input);

   // get the property value and print it out
   return prop.getProperty(keyword);
 
} catch (IOException ex) {
    ex.printStackTrace();
} finally {
   if (input != null) {
       try {
           input.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
return null;

}

@AfterSuite
public void endReport() {
//To close the report once the test suite execution is complete
report.flush();
}
}

