package com.demoblaze.pages;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class OrderPage extends BasePage {
    
    @FindBy(id = "name")
    private WebElement nameInput;
    
    @FindBy(id = "country")
    private WebElement countryInput;
    
    @FindBy(id = "city")
    private WebElement cityInput;
    
    @FindBy(id = "card")
    private WebElement creditCardInput;
    
    @FindBy(id = "month")
    private WebElement monthInput;
    
    @FindBy(id = "year")
    private WebElement yearInput;
    
    @FindBy(xpath = "//button[contains(text(),'Purchase')]")
    private WebElement purchaseButton;
    
    @FindBy(xpath = "//button[contains(text(),'OK')]")
    private WebElement okButton;
    
    @FindBy(className = "sweet-alert")
    private WebElement confirmationDialog;
    
    public OrderPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterOrderDetails(String name, String country, String city, 
                                  String creditCard, String month, String year) {
        type(nameInput, name);
        type(countryInput, country);
        type(cityInput, city);
        type(creditCardInput, creditCard);
        type(monthInput, month);
        type(yearInput, year);
    }
    
    public void submitOrder() {
        click(purchaseButton);
    }
    
    public void confirmPurchase() {
        click(okButton);
    }
    
    public String getConfirmationMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(confirmationDialog))
                      .findElement(By.xpath("//h2")).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getOrderDetails() {
        try {
            return confirmationDialog.findElement(By.xpath("//p")).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
