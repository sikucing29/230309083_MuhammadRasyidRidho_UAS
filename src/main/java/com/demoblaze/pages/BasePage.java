package com.demoblaze.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait shortWait;
    protected WebDriverWait longWait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ====== WAIT FOR METHODS ======

    /**
     * Generic wait method with custom condition
     */
    protected <T> T waitFor(Function<WebDriver, T> condition) {
        return wait.until(condition);
    }

    /**
     * Generic wait method with custom condition and timeout
     */
    protected <T> T waitFor(Function<WebDriver, T> condition, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(condition);
    }

    /**
     * Wait for element to be present in DOM
     */
    protected WebElement waitForElementPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement waitForElementPresent(By locator, int timeoutInSeconds) {
        return waitFor(ExpectedConditions.presenceOfElementLocated(locator), timeoutInSeconds);
    }

    /**
     * Wait for element to be visible
     */
    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
        return waitFor(ExpectedConditions.visibilityOfElementLocated(locator), timeoutInSeconds);
    }

    /**
     * Wait for element to be clickable
     */
    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForElementClickable(By locator, int timeoutInSeconds) {
        return waitFor(ExpectedConditions.elementToBeClickable(locator), timeoutInSeconds);
    }

    /**
     * Wait for element to disappear or be invisible
     */
    protected boolean waitForElementInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected boolean waitForElementInvisible(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected boolean waitForElementInvisible(By locator, int timeoutInSeconds) {
        return waitFor(ExpectedConditions.invisibilityOfElementLocated(locator), timeoutInSeconds);
    }

    /**
     * Wait for text to be present in element
     */
    protected boolean waitForTextPresent(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    protected boolean waitForTextPresent(WebElement element, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * Wait for element to have specific attribute value
     */
    protected boolean waitForAttributeValue(By locator, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
    }

    protected boolean waitForAttributeValue(WebElement element, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    /**
     * Wait for element count to be specific number
     */
    /**
     * Wait for element count to be specific number
     */
    protected boolean waitForElementCount(By locator, int count) {
        return wait.until(driver -> driver.findElements(locator).size() == count);
    }

    protected boolean waitForElementCountMoreThan(By locator, int count) {
        return wait.until(driver -> driver.findElements(locator).size() > count);
    }

    protected boolean waitForElementCountLessThan(By locator, int count) {
        return wait.until(driver -> driver.findElements(locator).size() < count);
    }



    /**
     * Wait for alert to be present
     */
    protected Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    protected Alert waitForAlert(int timeoutInSeconds) {
        return waitFor(ExpectedConditions.alertIsPresent(), timeoutInSeconds);
    }

    /**
     * Wait for URL to contain specific text
     */
    protected boolean waitForUrlContains(String text) {
        return wait.until(ExpectedConditions.urlContains(text));
    }

    /**
     * Wait for URL to be specific value
     */
    protected boolean waitForUrlToBe(String url) {
        return wait.until(ExpectedConditions.urlToBe(url));
    }

    /**
     * Wait for frame to be available and switch to it
     */
    protected WebDriver waitForFrameAndSwitch(By frameLocator) {
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    /**
     * Wait for page title to contain text
     */
    protected boolean waitForTitleContains(String text) {
        return wait.until(ExpectedConditions.titleContains(text));
    }

    /**
     * Wait for page title to be exact match
     */
    protected boolean waitForTitleIs(String title) {
        return wait.until(ExpectedConditions.titleIs(title));
    }

    /**
     * Wait for JavaScript condition to be true
     */
    protected Boolean waitForJsCondition(String jsCondition) {
        return wait.until(driver -> {
            Object result = js.executeScript("return " + jsCondition);
            return result instanceof Boolean ? (Boolean) result : false;
        });
    }

    /**
     * Wait with polling for custom condition
     */
    protected boolean waitForCondition(Function<WebDriver, Boolean> condition, int timeoutInSeconds, int pollingIntervalMillis) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        customWait.pollingEvery(Duration.ofMillis(pollingIntervalMillis));
        return customWait.until(condition);
    }

    /**
     * Wait for element to be stale (removed from DOM)
     */
    protected boolean waitForElementStale(WebElement element) {
        return wait.until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * Wait for all elements to be visible
     */
    protected List<WebElement> waitForAllElementsVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Wait for element to be selected
     */
    protected boolean waitForElementSelected(By locator) {
        return wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    protected boolean waitForElementSelected(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    // ====== UTILITY METHODS ======

    /**
     * Simple sleep method (use with caution)
     */
    protected void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Wait with custom message
     */
    protected <T> T waitFor(Function<WebDriver, T> condition, String message) {
        try {
            return wait.until(condition);
        } catch (TimeoutException e) {
            throw new TimeoutException(message + ": " + e.getMessage());
        }
    }

    /**
     * Wait for element with retry logic
     */
    protected WebElement waitForElementWithRetry(By locator, int maxRetries, int retryDelayMillis) {
        for (int i = 0; i < maxRetries; i++) {
            try {
                return waitForElementVisible(locator, 3);
            } catch (TimeoutException e) {
                if (i == maxRetries - 1) {
                    throw e;
                }
                waitFor(retryDelayMillis);
            }
        }
        throw new NoSuchElementException("Element not found after " + maxRetries + " retries: " + locator);
    }

    // Existing methods (with updated implementations using waitFor)
    protected void click(By locator) {
        WebElement element = waitForElementClickable(locator);
        highlightElement(element);
        element.click();
    }

    protected void click(WebElement element) {
        WebElement clickableElement = waitForElementClickable(element);
        highlightElement(clickableElement);
        clickableElement.click();
    }

    protected void type(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        highlightElement(element);
        element.clear();
        element.sendKeys(text);
    }

    protected void type(WebElement element, String text) {
        WebElement visibleElement = waitForElementVisible(element);
        highlightElement(visibleElement);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForElementVisible(locator).getText();
    }

    protected String getText(WebElement element) {
        WebElement visibleElement = waitForElementVisible(element);
        highlightElement(visibleElement);
        return visibleElement.getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitForElementVisible(locator, 3).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return shortWait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Existing methods remain the same...
    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    protected void waitForPageLoad() {
        waitFor(driver ->
                        js.executeScript("return document.readyState").equals("complete"),
                "Page did not load completely"
        );
    }

    protected void scrollToElement(By locator) {
        WebElement element = waitForElementPresent(locator);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    protected void highlightElement(By locator) {
        WebElement element = waitForElementPresent(locator);
        highlightElement(element);
    }

    protected void highlightElement(WebElement element) {
        js.executeScript("arguments[0].style.border='3px solid red'", element);
        waitFor(100);
        js.executeScript("arguments[0].style.border=''", element);
    }

    protected void takeScreenshot(String name) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // Screenshot will be attached via Allure
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}