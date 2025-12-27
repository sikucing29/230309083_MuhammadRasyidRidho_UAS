package com.demoblaze.pages;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import java.util.*;

public class CartPage extends BasePage {
    
    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderButton;
    
    @FindBy(id = "totalp")
    private WebElement totalPrice;
    
    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> cartItems;
    
    @FindBy(xpath = "//a[contains(text(),'Delete')]")
    private List<WebElement> deleteLinks;
    
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    public int getItemCount() {
        return cartItems.size();
    }
    
    public String getTotalPrice() {
        return getText(totalPrice);
    }
    
    public List<Map<String, String>> getCartItems() {
        List<Map<String, String>> items = new ArrayList<>();
        
        for (WebElement item : cartItems) {
            Map<String, String> itemDetails = new HashMap<>();
            List<WebElement> columns = item.findElements(By.tagName("td"));
            
            if (columns.size() >= 3) {
                itemDetails.put("title", columns.get(1).getText());
                itemDetails.put("price", columns.get(2).getText());
                items.add(itemDetails);
            }
        }
        
        return items;
    }
    
    public boolean isProductInCart(String productName) {
        return cartItems.stream()
                .anyMatch(item -> item.getText().contains(productName));
    }
    
    public void deleteProduct(String productName) {
        for (WebElement item : cartItems) {
            if (item.getText().contains(productName)) {
                WebElement deleteLink = item.findElement(By.linkText("Delete"));
                click(deleteLink);
                break;
            }
        }
    }
    
    public void deleteAllProducts() {
        while (!deleteLinks.isEmpty()) {
            click(deleteLinks.get(0));
            try {
                Thread.sleep(1000); // Wait for deletion
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void placeOrder() {
        click(placeOrderButton);
    }
}
