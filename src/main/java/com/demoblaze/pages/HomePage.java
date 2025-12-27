package com.demoblaze.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import java.util.List;
import java.util.ArrayList;

public class HomePage extends BasePage {

    // Navigation
    @FindBy(id = "nava")
    private WebElement brandLogo;

    @FindBy(xpath = "//a[contains(text(),'Home')]")
    private WebElement homeLink;

    @FindBy(xpath = "//a[contains(text(),'Contact')]")
    private WebElement contactLink;

    @FindBy(xpath = "//a[contains(text(),'About us')]")
    private WebElement aboutUsLink;

    @FindBy(id = "cartur")
    private WebElement cartLink;

    @FindBy(id = "login2")
    private WebElement loginLink;

    @FindBy(id = "signin2")
    private WebElement signUpLink;

    // Categories
    @FindBy(linkText = "Phones")
    private WebElement phonesCategory;

    @FindBy(linkText = "Laptops")
    private WebElement laptopsCategory;

    @FindBy(linkText = "Monitors")
    private WebElement monitorsCategory;

    // Product Listings (based on demoblaze.com content)
    @FindBy(linkText = "Samsung galaxy s6")
    private WebElement samsungS6;

    @FindBy(linkText = "Nokia lumia 1520")
    private WebElement nokiaLumia1520;

    @FindBy(linkText = "Nexus 6")
    private WebElement nexus6;

    @FindBy(linkText = "Samsung galaxy s7")
    private WebElement samsungS7;

    @FindBy(linkText = "Iphone 6 32gb")
    private WebElement iphone6;

    @FindBy(linkText = "Sony xperia z5")
    private WebElement sonyXperiaZ5;

    @FindBy(linkText = "HTC One M9")
    private WebElement htcOneM9;

    @FindBy(linkText = "Sony vaio i5")
    private WebElement sonyVaioI5;

    @FindBy(linkText = "Sony vaio i7")
    private WebElement sonyVaioI7;

    @FindBy(className = "card-block")
    private List<WebElement> productCards;

    @FindBy(className = "hrefch")
    private List<WebElement> productLinks;

    // Locator untuk pagination
    @FindBy(id = "next2")
    private WebElement nextButton;

    @FindBy(id = "prev2")
    private WebElement prevButton;

    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public void open() {
        driver.get("https://www.demoblaze.com");
        waitForPageLoad();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickCategory(String category) {
        switch(category.toLowerCase()) {
            case "phones":
                click(phonesCategory);
                break;
            case "laptops":
                click(laptopsCategory);
                break;
            case "monitors":
                click(monitorsCategory);
                break;
        }
        waitForPageLoad();
    }

    public void selectProduct(String productName) {
        switch(productName.toLowerCase()) {
            case "samsung galaxy s6":
                click(samsungS6);
                break;
            case "nokia lumia 1520":
                click(nokiaLumia1520);
                break;
            case "nexus 6":
                click(nexus6);
                break;
            case "samsung galaxy s7":
                click(samsungS7);
                break;
            case "iphone 6 32gb":
                click(iphone6);
                break;
            case "sony xperia z5":
                click(sonyXperiaZ5);
                break;
            case "htc one m9":
                click(htcOneM9);
                break;
            case "sony vaio i5":
                click(sonyVaioI5);
                break;
            case "sony vaio i7":
                click(sonyVaioI7);
                break;
            default:
                // Fallback to generic click
                driver.findElement(By.linkText(productName)).click();
        }
        waitForPageLoad();
    }

    public void openLoginModal() {
        click(loginLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModal")));
    }

    public void openSignUpModal() {
        click(signUpLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInModal")));
    }

    public void openCart() {
        click(cartLink);
        waitForPageLoad();
    }

    // Di HomePage.java
    public int getProductCount() {
        try {
            int totalProducts = 0;
            boolean hasNextPage = true;
            int page = 1;

            System.out.println("Mencari produk di halaman " + page);

            // Loop untuk semua halaman
            while (hasNextPage) {
                // Tunggu elemen produk muncul
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[@class='card-block']/h4/a")));

                List<WebElement> products = driver.findElements(
                        By.xpath("//div[@class='card-block']/h4/a"));

                totalProducts += products.size();
                System.out.println("Halaman " + page + ": " + products.size() + " produk ditemukan");

                // Coba cari dan klik next button jika ada
                try {
                    WebElement nextButton = driver.findElement(
                            By.id("next2")  // ID tombol next di DemoBlaze
                            // atau bisa juga By.xpath("//button[text()='Next']")
                            // atau By.xpath("//button[@id='next2']")
                    );

                    if (nextButton.isDisplayed() && nextButton.isEnabled()) {
                        System.out.println("Menekan tombol Next...");
                        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
                        nextButton.click();

                        // Tunggu halaman berikutnya loading
                        Thread.sleep(2000);  // Tunggu sederhana
                        page++;

                        // Tunggu sampai produk di halaman baru muncul
                        wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//div[@class='card-block']/h4/a")));
                    } else {
                        hasNextPage = false;
                        System.out.println("Tombol Next tidak aktif, berhenti.");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Tombol Next tidak ditemukan, berhenti.");
                    hasNextPage = false;
                } catch (ElementNotInteractableException e) {
                    System.out.println("Tombol Next tidak bisa diklik, berhenti.");
                    hasNextPage = false;
                }
            }

            System.out.println("Total semua produk: " + totalProducts);
            return totalProducts;

        } catch (Exception e) {
            System.err.println("Error saat menghitung produk: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public List<String> getAllProductNames() {
        return productLinks.stream()
                .map(WebElement::getText)
                .toList();
    }

    public boolean isProductDisplayed(String productName) {
        return productLinks.stream()
                .anyMatch(element -> element.getText().equalsIgnoreCase(productName));
    }

    // ====== PAGINATION METHODS ======

    /**
     * Check if next button is available (exists and enabled)
     */
    public boolean isNextButtonAvailable() {
        try {
            WebElement nextBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("next2")));
            return nextBtn.isDisplayed() && nextBtn.isEnabled();
        } catch (Exception e) {
            // Jika tidak ditemukan, return false
            return false;
        }
    }

    /**
     * Click next button to go to next page
     */
    public void clickNextButton() {
        try {
            if (isNextButtonAvailable()) {
                click(nextButton);
                waitForPageLoad();
                waitFor(1000); // Extra wait for products to load
            }
        } catch (Exception e) {
            // Ganti logError dengan System.out.println
            System.out.println("ERROR: Could not click next button: " + e.getMessage());
        }
    }

    /**
     * Get product count from ALL pages (including pagination)
     */
    public int getTotalProductCountFromAllPages() {
        int totalCount = 0;
        int currentPage = 1;
        int maxPages = 10; // Safety limit

        try {
            System.out.println("INFO: Counting products from all pages...");

            do {
                // Count products on current page
                int productsOnPage = getProductCount();
                totalCount += productsOnPage;
                System.out.println("INFO: Page " + currentPage + ": " + productsOnPage + " products");

                // Simpan produk yang ada di halaman ini untuk debug
                List<String> productNames = getAllProductNames();
                System.out.println("INFO: Products on page " + currentPage + ": " + productNames);

                // Check if next button is available
                boolean hasNextPage = isNextButtonAvailable();

                if (hasNextPage) {
                    clickNextButton();
                    currentPage++;

                    // Safety: jangan infinite loop
                    if (currentPage > maxPages) {
                        System.out.println("WARN: Reached max pages limit: " + maxPages);
                        break;
                    }
                } else {
                    System.out.println("INFO: No more pages. Total pages: " + currentPage);
                    break;
                }

            } while (true);

        } catch (Exception e) {
            System.out.println("ERROR: Error counting products from all pages: " + e.getMessage());
        }

        System.out.println("INFO: Total products across all pages: " + totalCount);
        return totalCount;
    }

    /**
     * Get ALL product names from ALL pages
     */
    public List<String> getAllProductNamesFromAllPages() {
        List<String> allProductNames = new ArrayList<>();
        int currentPage = 1;
        int maxPages = 10;

        try {
            System.out.println("INFO: Collecting product names from all pages...");

            do {
                // Get product names from current page
                List<String> pageProductNames = getAllProductNames();
                allProductNames.addAll(pageProductNames);
                System.out.println("INFO: Page " + currentPage + " has " + pageProductNames.size() + " products");

                // Check if next button is available
                boolean hasNextPage = isNextButtonAvailable();

                if (hasNextPage) {
                    clickNextButton();
                    currentPage++;

                    // Safety limit
                    if (currentPage > maxPages) {
                        System.out.println("WARN: Reached max pages limit: " + maxPages);
                        break;
                    }
                } else {
                    System.out.println("INFO: No more pages. Total pages: " + currentPage);
                    break;
                }

            } while (true);

        } catch (Exception e) {
            System.out.println("ERROR: Error collecting product names: " + e.getMessage());
        }

        System.out.println("INFO: Total unique product names collected: " + allProductNames.size());
        return allProductNames;
    }

    /**
     * Navigate to specific page number
     */
    public void goToPage(int pageNumber) {
        try {
            // For simplicity, we'll click next/prev or use page number links
            // Demoblaze doesn't have direct page links, so we need to navigate from first page
            driver.get("https://www.demoblaze.com");
            waitForPageLoad();

            for (int i = 1; i < pageNumber; i++) {
                if (isNextButtonAvailable()) {
                    clickNextButton();
                } else {
                    System.out.println("WARN: Cannot navigate to page " + pageNumber + ", only " + i + " pages available");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: Error navigating to page " + pageNumber + ": " + e.getMessage());
        }
    }

    /**
     * Get total number of pages available
     */
    public int getTotalPages() {
        int totalPages = 1;
        int currentPage = 1;
        int maxPages = 10;

        try {
            // Start from first page
            driver.get("https://www.demoblaze.com");
            waitForPageLoad();

            while (isNextButtonAvailable() && currentPage <= maxPages) {
                clickNextButton();
                currentPage++;
                totalPages = currentPage;
            }

            // Return to first page
            driver.get("https://www.demoblaze.com");
            waitForPageLoad();

        } catch (Exception e) {
            System.out.println("ERROR: Error counting total pages: " + e.getMessage());
        }

        System.out.println("INFO: Total pages detected: " + totalPages);
        return totalPages;
    }
}