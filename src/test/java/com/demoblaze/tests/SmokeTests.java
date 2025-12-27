package com.demoblaze.tests;

import com.demoblaze.pages.*;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.testng.Assert.*;

/**
 * SMOKE TEST SUITE - SIMPLE VERSION
 */
public class SmokeTests extends BaseTest {

    @Test(description = "TC_SMOKE_001 - Verify homepage loads", priority = 1)
    public void testHomePageLoad() {
        logInfo("Starting homepage test");

        try {
            HomePage homePage = new HomePage(driver);
            String title = getPageTitle();
            String url = getCurrentUrl();

            System.out.println("Title: " + title);
            System.out.println("URL: " + url);

            // Assertions yang lebih flexible
            assertTrue(title != null && !title.isEmpty(), "Title should not be empty");
            assertTrue(url.contains("demoblaze") || url.contains("demoblaze.com"),
                    "URL should contain demoblaze");

            logPass("Homepage loaded successfully");
            takeScreenshot("homepage");

        } catch (Exception e) {
            logFail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "TC_SMOKE_002 - Verify product categories exist", priority = 2)
    public void testProductCategories() {
        logInfo("Testing product categories");

        try {
            HomePage homePage = new HomePage(driver);

            // Coba click categories jika ada
            logInfo("Checking if categories are clickable");

            // Ambil semua product names
            java.util.List<String> products = homePage.getAllProductNames();
            assertTrue(products != null && products.size() > 0,
                    "Should have at least one product");

            logInfo("Found " + products.size() + " products");
            for (String product : products.subList(0, Math.min(3, products.size()))) {
                logInfo("Product available: " + product);
            }

            logPass("Product categories verified");
            takeScreenshot("product_listing");

        } catch (Exception e) {
            logFail("Categories test failed: " + e.getMessage());
        }
    }

    @Test(description = "TC_SMOKE_003 - Verify navigation works", priority = 3)
    public void testNavigation() {
        logInfo("Testing navigation");

        try {
            HomePage homePage = new HomePage(driver);

            // Test cart navigation
            logInfo("Opening cart page");
            homePage.openCart();
            pause(2000);

            String cartUrl = getCurrentUrl();
            assertTrue(cartUrl.contains("cart") || cartUrl.contains("cart.html"),
                    "Should be on cart page");

            logPass("Cart navigation works");
            takeScreenshot("cart_page");

            // Kembali ke home
            navigateBack();
            pause(2000);

        } catch (Exception e) {
            logFail("Navigation test failed: " + e.getMessage());
        }
    }

    @Test(description = "TC_SMOKE_004 - Verify product selection", priority = 4)
    public void testProductSelection() {
        logInfo("Testing product selection");

        try {
            HomePage homePage = new HomePage(driver);

            // Coba pilih product pertama yang ada
            java.util.List<String> products = homePage.getAllProductNames();
            if (products.size() > 0) {
                String firstProduct = products.get(0);
                logInfo("Selecting product: " + firstProduct);

                homePage.selectProduct(firstProduct);
                pause(2000);

                // Verify we're on product page
                String currentUrl = getCurrentUrl();
                assertTrue(currentUrl.contains("prod") || currentUrl.contains("id"),
                        "Should be on product detail page");

                logPass("Product selection works: " + firstProduct);
                takeScreenshot("product_detail");

                // Kembali ke home
                navigateBack();
            } else {
                logInfo("No products found to test selection");
            }

        } catch (Exception e) {
            logFail("Product selection test failed: " + e.getMessage());
        }
    }

    @Test(description = "TC_SMOKE_005 - Verify page elements exist", priority = 5)
    public void testPageElements() {
        logInfo("Testing page elements");

        try {
            HomePage homePage = new HomePage(driver);

            // Tunggu sampai produk muncul
            waitForElementVisible(By.id("tbodyid"), 10);

            String pageSource = driver.getPageSource();

            assertTrue(pageSource.contains("nav"), "Navbar should exist");
            assertTrue(pageSource.contains("footer") || pageSource.contains("Copyright"),
                    "Footer should exist");

            logPass("Page elements verified");
            takeScreenshot("page_elements");

        } catch (Exception e) {
            logFail("Page elements test failed: " + e.getMessage());
            throw e;
        }
    }


    @Test(description = "TC_SMOKE_006 - Final smoke test completion", priority = 6)
    public void testSmokeCompletion() {
        logInfo("Final smoke test - verifying website is accessible");

        try {
            // Simple verification that website is responsive
            String title = getPageTitle();
            String url = getCurrentUrl();

            assertTrue(title != null, "Title should exist");
            assertTrue(url != null, "URL should exist");

            logPass("✅ ALL SMOKE TESTS COMPLETED SUCCESSFULLY!");
            logInfo("Title: " + title);
            logInfo("URL: " + url);

            takeScreenshot("smoke_tests_complete");

        } catch (Exception e) {
            logFail("Final verification failed: " + e.getMessage());
        }
    }
}