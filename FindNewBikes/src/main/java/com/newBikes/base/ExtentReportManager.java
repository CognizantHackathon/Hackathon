package com.newBikes.base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;


public class ExtentReportManager extends baseUI {
public static ExtentReports report;

//Method to create Extent Report object
public static ExtentReports getReportInstance(String reportTitle){

if(report == null){
//Creating new HTML Report
ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter( System.getProperty("user.dir")+"\\Html_report\\"+reportTitle+".html");
report =  new ExtentReports();
report.attachReporter(htmlReporter);

//Setting up Environment variables
report.setSystemInfo("OS", "Windows 10");
report.setSystemInfo("Environment", "UAT");
report.setSystemInfo("Build Number", "10.8.1");

//ExtentHtmlReport Title and elements set-up
htmlReporter.config().setDocumentTitle(reportTitle);
htmlReporter.config().setReportName("Test Report");
htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
}

//Returning the report.
return report;
}
}