package com.demoblaze.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.demoblaze.report.ExtentManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.ITestResult;
import org.testng.ITestContext;
import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import java.util.List;
import java.util.Arrays;

/**
 * BASE TEST CLASS - SUPERCLASS UNTUK SEMUA TEST CASES
 * ===================================================
 * Class ini berisi setup, teardown, dan utility methods
 * yang digunakan oleh semua test classes
 */
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Random random = new Random();
    private String currentTestName;

    // ========== SETUP & TEARDOWN METHODS ==========

    @BeforeSuite
    public void setupSuite() {
        System.out.println("🚀 [TC_SETUP_001] SETTING UP TEST SUITE...");
        System.out.println("=========================================");
        WebDriverManager.chromedriver().setup();

        try {
            ExtentManager.getInstance();
            System.out.println("✅ [TC_SETUP_001] ExtentReports initialized");
        } catch (Exception e) {
            System.out.println("⚠️ [TC_SETUP_001] Could not initialize ExtentReports: " + e.getMessage());
        }

        System.out.println("✅ [TC_SETUP_001] ChromeDriver setup completed");
        System.out.println("=========================================");
    }

    @BeforeMethod
    public void setupTest(ITestContext context) {
        System.out.println("🔧 [TC_SETUP_002] SETTING UP DRIVER FOR TEST...");
        System.out.println("=========================================");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        loadWebsiteWithRetry();

        System.out.println("✅ [TC_SETUP_002] Driver setup completed successfully");
        System.out.println("=========================================");
    }

    @AfterMethod
    public void teardownTest(ITestResult result) {
        System.out.println("🧹 [TC_SETUP_003] CLEANING UP TEST...");
        System.out.println("=========================================");

        if (result.getStatus() == ITestResult.FAILURE) {
            String testName = result.getName();
            takeScreenshot(testName + "_FAILED", "Test Failed");
        }

        if (driver != null) {
            try {
                driver.quit();
                System.out.println("✅ [TC_SETUP_003] Driver closed successfully");
            } catch (Exception e) {
                System.out.println("⚠️ [TC_SETUP_003] Error closing driver: " + e.getMessage());
            }
        }
        System.out.println("=========================================");
    }

    @AfterSuite
    public void teardownSuite() {
        try {
            ExtentManager.flushReport();
            System.out.println("✅ [TC_SETUP_004] ExtentReports saved successfully");
        } catch (Exception e) {
            System.out.println("⚠️ [TC_SETUP_004] Could not flush ExtentReports: " + e.getMessage());
        }
        System.out.println("=========================================");
    }

    // ========== WEBSITE LOADING ==========

    private void loadWebsiteWithRetry() {
        for (int i = 1; i <= 3; i++) {
            try {
                System.out.println("🌐 [TC_UTIL_001] Attempt " + i + "/3 to load demoblaze.com");
                driver.get("https://www.demoblaze.com");
                pause(3000);

                String title = driver.getTitle();
                if (title.contains("STORE") || title.contains("Store") || title.contains("Demo")) {
                    System.out.println("✅ [TC_UTIL_001] Website loaded successfully: " + title);
                    return;
                }
            } catch (Exception e) {
                System.out.println("❌ [TC_UTIL_001] Attempt " + i + " failed: " + e.getMessage());
                if (i == 3) throw new RuntimeException("Failed to load website");
                pause(5000);
            }
        }
    }

    // ========== TEST INITIALIZATION ==========

    protected void initTest(String testName, String description) {
        this.currentTestName = testName;
        try {
            ExtentManager.createTest(testName, description);
        } catch (Exception e) {
            System.out.println("⚠️ Could not create ExtentReports test");
        }
    }

    protected void endTest() {
        // Nothing to do here for now
    }

    // ========== LOGGING METHODS ==========

    protected void logInfo(String testCaseCode, String message) {
        System.out.println("📝 [" + testCaseCode + "] " + message);
        try {
            ExtentManager.logInfo("[" + testCaseCode + "] " + message);
        } catch (Exception e) {
            // Silent fail
        }
    }

    protected void logPass(String testCaseCode, String message) {
        System.out.println("✅ [" + testCaseCode + "] " + message);
        try {
            ExtentManager.logPass("[" + testCaseCode + "] " + message);
        } catch (Exception e) {
            // Silent fail
        }
    }

    protected void logFail(String testCaseCode, String message) {
        System.out.println("❌ [" + testCaseCode + "] " + message);
        try {
            ExtentManager.logFail("[" + testCaseCode + "] " + message);
        } catch (Exception e) {
            // Silent fail
        }
    }

    protected void logWarning(String testCaseCode, String message) {
        System.out.println("⚠️ [" + testCaseCode + "] " + message);
        try {
            ExtentManager.logWarning("[" + testCaseCode + "] " + message);
        } catch (Exception e) {
            // Silent fail
        }
    }


    // ========== SCREENSHOT METHODS ==========

    protected void takeScreenshot(String testCaseCode, String name) {
        System.out.println("📸 [" + testCaseCode + "] Taking screenshot: " + name);
        try {
            ExtentManager.addScreenshot(driver, "[" + testCaseCode + "] " + name);
        } catch (Exception e) {
            System.out.println("⚠️ [" + testCaseCode + "] Screenshot failed: " + e.getMessage());
        }
    }

    protected void takeScreenshot(String testCaseCode) {
        takeScreenshot(testCaseCode, "Screenshot");
    }

    // ========== WAIT METHODS ==========

    protected void waitForElementVisible(By locator, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println("👀 Element visible: " + locator);
        } catch (Exception e) {
            System.out.println("❌ Element not visible: " + locator);
            throw e;
        }
    }

    protected void waitForElementClickable(By locator, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            customWait.until(ExpectedConditions.elementToBeClickable(locator));
            System.out.println("🖱️ Element clickable: " + locator);
        } catch (Exception e) {
            System.out.println("❌ Element not clickable: " + locator);
            throw e;
        }
    }

    protected void waitForElementPresent(By locator, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            System.out.println("📍 Element present: " + locator);
        } catch (Exception e) {
            System.out.println("❌ Element not present: " + locator);
            throw e;
        }
    }

    // ========== PAUSE METHOD ==========

    protected void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ========== BROWSER NAVIGATION METHODS ==========

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void navigateBack() {
        driver.navigate().back();
        pause(2000);
        System.out.println("🔙 Navigated back");
    }

    public void refreshPage() {
        driver.navigate().refresh();
        pause(3000);
        System.out.println("🔄 Page refreshed");
    }

    public void navigateForward() {
        driver.navigate().forward();
        pause(2000);
        System.out.println("🔜 Navigated forward");
    }

    // ========== DATA GENERATION METHODS ==========

    protected String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    protected String generateRandomEmail() {
        return "testuser_" + System.currentTimeMillis() + "@test.com";
    }

    protected int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    // ========== ASSERTION METHODS ==========

    protected void assertContains(String testCaseCode, String actual, String expected, String message) {
        if (actual == null || !actual.contains(expected)) {
            logFail(testCaseCode, message + " | Expected: " + expected + " | Actual: " + actual);
            throw new AssertionError("[" + testCaseCode + "] " + message);
        }
        logPass(testCaseCode, message);
    }

    protected void assertTrue(String testCaseCode, boolean condition, String message) {
        if (!condition) {
            logFail(testCaseCode, message);
            throw new AssertionError("[" + testCaseCode + "] " + message);
        }
        logPass(testCaseCode, message);
    }

    protected void assertEquals(String testCaseCode, String actual, String expected, String message) {
        if (!actual.equals(expected)) {
            logFail(testCaseCode, message + " | Expected: " + expected + " | Actual: " + actual);
            throw new AssertionError("[" + testCaseCode + "] " + message);
        }
        logPass(testCaseCode, message);
    }

    protected void assertEqualsIgnoreCase(String testCaseCode, String actual, String expected, String message) {
        if (!actual.equalsIgnoreCase(expected)) {
            logFail(testCaseCode, message + " | Expected: " + expected + " | Actual: " + actual);
            throw new AssertionError("[" + testCaseCode + "] " + message);
        }
        logPass(testCaseCode, message);
    }

    protected void assertElementDisplayed(String testCaseCode, By locator, String message) {
        try {
            boolean isDisplayed = driver.findElement(locator).isDisplayed();
            if (!isDisplayed) {
                logFail(testCaseCode, message + " | Element: " + locator);
                throw new AssertionError("[" + testCaseCode + "] " + message);
            }
            logPass(testCaseCode, message);
        } catch (Exception e) {
            logFail(testCaseCode, message + " | Element not found: " + locator);
            throw new AssertionError("[" + testCaseCode + "] " + message);
        }
    }

    protected void assertElementTextEquals(String testCaseCode, By locator, String expectedText, String message) {
        try {
            String actualText = driver.findElement(locator).getText();
            if (!actualText.equals(expectedText)) {
                logFail(testCaseCode, message + " | Expected: " + expectedText + " | Actual: " + actualText);
                throw new AssertionError("[" + testCaseCode + "] " + message);
            }
            logPass(testCaseCode, message);
        } catch (Exception e) {
            logFail(testCaseCode, message + " | Element not found: " + locator);
            throw new AssertionError("[" + testCaseCode + "] " + message);
        }
    }

    // ========== ELEMENT INTERACTION METHODS ==========

    protected void safeClick(String testCaseCode, By locator, String elementName) {
        try {
            waitForElementClickable(locator, 10);
            driver.findElement(locator).click();
            System.out.println("🖱️ [" + testCaseCode + "] Clicked: " + elementName);
            pause(1000);
        } catch (Exception e) {
            System.out.println("❌ [" + testCaseCode + "] Failed to click: " + elementName);
            throw e;
        }
    }

    protected void safeSendKeys(String testCaseCode, By locator, String text, String fieldName) {
        try {
            waitForElementVisible(locator, 10);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
            System.out.println("⌨️ [" + testCaseCode + "] Entered text in " + fieldName + ": " + text);
            pause(500);
        } catch (Exception e) {
            System.out.println("❌ [" + testCaseCode + "] Failed to enter text: " + fieldName);
            throw e;
        }
    }

    protected String getElementText(String testCaseCode, By locator, String elementName) {
        try {
            waitForElementVisible(locator, 10);
            String text = driver.findElement(locator).getText();
            System.out.println("📄 [" + testCaseCode + "] Got text from " + elementName + ": " + text);
            return text;
        } catch (Exception e) {
            System.out.println("❌ [" + testCaseCode + "] Failed to get text: " + elementName);
            throw e;
        }
    }

    protected boolean isElementExists(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void acceptAlert(String testCaseCode) {
        try {
            driver.switchTo().alert().accept();
            pause(1000);
            System.out.println("✅ [" + testCaseCode + "] Alert accepted");
        } catch (Exception e) {
            System.out.println("⚠️ [" + testCaseCode + "] No alert present");
        }
    }

    protected void dismissAlert(String testCaseCode) {
        try {
            driver.switchTo().alert().dismiss();
            pause(1000);
            System.out.println("❌ [" + testCaseCode + "] Alert dismissed");
        } catch (Exception e) {
            System.out.println("⚠️ [" + testCaseCode + "] No alert present");
        }
    }

    protected String getAlertText(String testCaseCode) {
        try {
            String alertText = driver.switchTo().alert().getText();
            System.out.println("📄 [" + testCaseCode + "] Alert text: " + alertText);
            return alertText;
        } catch (Exception e) {
            System.out.println("⚠️ [" + testCaseCode + "] No alert present");
            return "";
        }
    }

    // ========== OVERLOADED METHODS ==========

    protected void assertContains(String actual, String expected, String message) {
        assertContains("TC_GENERIC", actual, expected, message);
    }

    protected void assertTrue(boolean condition, String message) {
        assertTrue("TC_GENERIC", condition, message);
    }

    protected void assertEquals(String actual, String expected, String message) {
        assertEquals("TC_GENERIC", actual, expected, message);
    }

    protected void logInfo(String message) {
        logInfo("TC_GENERIC", message);
    }

    protected void logPass(String message) {
        logPass("TC_GENERIC", message);
    }

    protected void logFail(String message) {
        logFail("TC_GENERIC", message);
    }

    protected void logWarning(String message) {
        logWarning("TC_GENERIC", message);
    }


}