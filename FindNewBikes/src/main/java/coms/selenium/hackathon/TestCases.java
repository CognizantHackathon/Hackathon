package coms.selenium.hackathon;

import org.testng.annotations.Test;

public class TestCases extends BaseUI{
	@Test
	public void testOne() {
		getDriver("mozila");
		getURL("URL");
		tearDown();
		
	}
}
