package com.demoblaze.utils;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    
    public static String takeScreenshot(WebDriver driver, String testName) {
        String screenshotPath = null;
        try {
            // Create directory if not exists
            File directory = new File("target/screenshots");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            // Generate filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            String fileName = testName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
            screenshotPath = "target/screenshots/" + fileName;
            
            // Take screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            
            System.out.println("📸 Screenshot saved to: " + screenshotPath);
            
        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
        }
        return screenshotPath;
    }
    
    public static void takeScreenshotWithHighlight(WebDriver driver, WebElement element, String testName) {
        try {
            // Highlight element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String originalStyle = element.getAttribute("style");
            js.executeScript("arguments[0].style.border='3px solid red'", element);
            
            // Take screenshot
            takeScreenshot(driver, testName + "_highlighted");
            
            // Restore original style
            js.executeScript("arguments[0].style.border='" + originalStyle + "'", element);
            
        } catch (Exception e) {
            System.err.println("Failed to take screenshot with highlight: " + e.getMessage());
            takeScreenshot(driver, testName);
        }
    }
}
