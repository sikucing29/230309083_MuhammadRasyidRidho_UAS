package com.demoblaze.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    public static ExtentReports getInstance() {
        if (extent == null) {
            setup();
        }
        return extent;
    }
    
    private static void setup() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html";
        
        // Create test-output directory
        new File(System.getProperty("user.dir") + "/test-output").mkdirs();
        new File(System.getProperty("user.dir") + "/test-output/screenshots").mkdirs();
        
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Demoblaze Automation Report");
        spark.config().setReportName("Demoblaze Test Results");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setEncoding("UTF-8");
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        
        // Add custom CSS for better look
        spark.config().setCss(".badge-primary { background-color: #007bff !important; }");
        spark.config().setCss(".badge-success { background-color: #28a745 !important; }");
        spark.config().setCss(".badge-danger { background-color: #dc3545 !important; }");
        
        extent = new ExtentReports();
        extent.attachReporter(spark);
        
        // System info
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("OS Version", System.getProperty("os.version"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("URL", "https://www.demoblaze.com");
    }
    
    public static void createTest(String testName, String description) {
        ExtentTest extentTest = getInstance().createTest(testName, description);
        test.set(extentTest);
    }
    
    public static ExtentTest getTest() {
        return test.get();
    }
    
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
    
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = screenshotName.replaceAll(" ", "_") + "_" + timestamp + ".png";
            String filePath = System.getProperty("user.dir") + "/test-output/screenshots/" + fileName;
            
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            org.apache.commons.io.FileUtils.copyFile(srcFile, new File(filePath));
            
            return filePath;
        } catch (Exception e) {
            System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
    
    public static void addScreenshot(WebDriver driver, String title) {
        try {
            String screenshotPath = captureScreenshot(driver, title);
            if (screenshotPath != null) {
                getTest().addScreenCaptureFromPath(screenshotPath, title);
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to add screenshot: " + e.getMessage());
        }
    }
    
    public static void logPass(String message) {
        getTest().pass(message);
    }
    
    public static void logFail(String message) {
        getTest().fail(message);
    }
    
    public static void logInfo(String message) {
        getTest().info(message);
    }
    
    public static void logWarning(String message) {
        getTest().warning(message);
    }
    
    public static void logSkip(String message) {
        getTest().skip(message);
    }
}
