package com.demoblaze.pages;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class LoginPage extends BasePage {
    
    @FindBy(id = "loginusername")
    private WebElement usernameInput;
    
    @FindBy(id = "loginpassword")
    private WebElement passwordInput;
    
    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    private WebElement loginButton;
    
    @FindBy(xpath = "//div[@id='logInModal']//button[contains(text(),'Close')]")
    private WebElement closeButton;
    
    @FindBy(id = "nameofuser")
    private WebElement welcomeMessage;
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void login(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
        
        try {
            Thread.sleep(2000); // Wait for login to process
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void closeModal() {
        click(closeButton);
    }
    
    public boolean isLoginSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(welcomeMessage))
                      .getText().toLowerCase().contains("welcome");
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isLoginModalDisplayed() {
        return isDisplayed(usernameInput);
    }
}
