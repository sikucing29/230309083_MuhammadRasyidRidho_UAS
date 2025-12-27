package com.demoblaze.listeners;

import com.aventstack.extentreports.Status;
import com.demoblaze.report.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.lang.reflect.Field;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("🚀 Test Suite: " + context.getName() + " started");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("✅ Test Suite: " + context.getName() + " finished");
        ExtentManager.flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("▶️ Test Started: " + result.getName());
        ExtentManager.createTest(result.getName(), result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getName());
        ExtentManager.getTest().log(Status.PASS, "Test passed successfully");
        addScreenshotToReport(result, "Success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());
        ExtentManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable().getMessage());
        ExtentManager.getTest().fail(result.getThrowable());
        addScreenshotToReport(result, "Failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⏸️ Test Skipped: " + result.getName());
        ExtentManager.getTest().log(Status.SKIP, "Test skipped");
    }

    private void addScreenshotToReport(ITestResult result, String status) {
        try {
            Object testClass = result.getInstance();
            Field driverField = testClass.getClass().getSuperclass().getDeclaredField("driver");
            driverField.setAccessible(true);
            org.openqa.selenium.WebDriver driver = (org.openqa.selenium.WebDriver) driverField.get(testClass);

            if (driver != null) {
                ExtentManager.addScreenshot(driver, result.getName() + "_" + status);
            }
        } catch (Exception e) {
            System.err.println("⚠️ Could not add screenshot: " + e.getMessage());
        }
    }
}