package com.demoblaze.pages;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class ProductPage extends BasePage {
    
    @FindBy(className = "name")
    private WebElement productName;
    
    @FindBy(className = "price-container")
    private WebElement productPrice;
    
    @FindBy(xpath = "//div[@id='more-information']/p")
    private WebElement productDescription;
    
    @FindBy(linkText = "Add to cart")
    private WebElement addToCartButton;
    
    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    private WebElement cartButton;
    
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    
    public String getProductName() {
        return getText(productName);
    }
    
    public String getProductPrice() {
        return getText(productPrice);
    }
    
    public String getDescription() {
        return getText(productDescription);
    }
    
    public void addToCart() {
        click(addToCartButton);
        
        // Handle alert
        try {
            Thread.sleep(1000);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert present");
        }
    }
    
    public void navigateToCart() {
        click(cartButton);
    }
    
    public boolean verifyProductDetails(String expectedName, String expectedPrice) {
        String actualName = getProductName();
        String actualPrice = getProductPrice();
        
        return actualName.equalsIgnoreCase(expectedName) && 
               actualPrice.contains(expectedPrice);
    }
}
