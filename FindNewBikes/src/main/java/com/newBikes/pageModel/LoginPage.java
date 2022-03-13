package com.newBikes.pageModel;

import java.io.FileNotFoundException;
import java.util.Set;

import com.newBikes.base.baseUI;

public class LoginPage extends baseUI {

	public String parentHandle = "";

	public void setupWebsite(String browser) throws InterruptedException, FileNotFoundException {
//To open parent window of zigwheels
		invokeBrowser(browser);
		openUrl(getPropertyValue("website"));
		parentHandle = driver.getWindowHandle();

	}

	public void openLoginWindow(String loginSite) throws InterruptedException {
//To click on the login button
		clickXpath("//*[@id=\"forum_login_wrap_lg\"]");
		Thread.sleep(3000);
		
		
		//To click on the login with google button
		
		clickXpath("//*[@id=\"googleSignIn\"]");
		waitImplicit();

//To switch to child window
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			if (!(windowHandle.equals(parentHandle))) {
				driver.switchTo().window(windowHandle);
			}
		}
		Thread.sleep(3000);
	}

	public void enterCredentials(String email) throws InterruptedException {
//To enter login credentials in google window
		enterText("//*[@id=\"identifierId\"]", email);
		clickXpath("//*[@id=\"identifierNext\"]/div/button/span");
	}

}