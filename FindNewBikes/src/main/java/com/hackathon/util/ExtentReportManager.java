package com.hackathon.util;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager {

	//public static ExtentHtmlReporter htmlReporter;

	public static ExtentReports report;

	public static ExtentReports getReportInstance() {
		
		if( report == null) {
		String reportName = DateUtil.getTimeStamp() + ".html";	
		ExtentHtmlReporter	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\target\\"+reportName);
			
		report =new  ExtentReports();
		report.attachReporter(htmlReporter);
		
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Environment", "UAT");
		report.setSystemInfo("Browser", "Chrome");
		
		
		htmlReporter.config().setDocumentTitle("UAT UI Hackathon Automation Results");
		htmlReporter.config().setReportName("All Test Cases Report");
		htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		}
		
		
		
		return report;

	}
}
