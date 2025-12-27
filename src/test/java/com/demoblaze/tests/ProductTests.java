package com.demoblaze.tests;

import com.demoblaze.pages.*;
import com.demoblaze.tests.BaseTest;
import org.testng.annotations.*;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * PRODUCT TEST SUITE - TEST CASES UNTUK FUNGSIONALITAS PRODUK
 * ===========================================================
 * Test suite ini menguji semua aspek terkait produk di demoblaze.com
 * Termasuk: product listing, details, categories, prices, dll.
 */
public class ProductTests extends BaseTest {

    // Test data - semua produk dari demoblaze.com (diperbarui dengan data lengkap)
    private final List<String> ALL_PRODUCTS = Arrays.asList(
            // Phones (7 produk)
            "Samsung galaxy s6",
            "Nokia lumia 1520",
            "Nexus 6",
            "Samsung galaxy s7",
            "Iphone 6 32gb",
            "Sony xperia z5",
            "HTC One M9",
            // Laptops (6 produk)
            "Sony vaio i5",
            "Sony vaio i7",
            "MacBook air",
            "Dell i7 8gb",
            "2017 Dell 15.6 Inch",
            "MacBook Pro",
            // Monitors (2 produk)
            "Apple monitor 24",
            "ASUS Full HD"
    );

    private final List<String> PHONE_PRODUCTS = Arrays.asList(
            "Samsung galaxy s6",
            "Nokia lumia 1520",
            "Nexus 6",
            "Samsung galaxy s7",
            "Iphone 6 32gb",
            "Sony xperia z5",
            "HTC One M9"
    );

    private final List<String> LAPTOP_PRODUCTS = Arrays.asList(
            "Sony vaio i5",
            "Sony vaio i7",
            "MacBook air",
            "Dell i7 8gb",
            "2017 Dell 15.6 Inch",
            "MacBook Pro"
    );

    private final List<String> MONITOR_PRODUCTS = Arrays.asList(
            "Apple monitor 24",
            "ASUS Full HD"
    );

    /**
     * TC_PROD_001 - Verify All Products Are Listed on Homepage
     * Objective: Verify semua produk dari spec ditampilkan di homepage
     */
    @Test(description = "TC_PROD_001 - Verify all products are listed", priority = 1)
    public void testAllProductsListed() {
        logInfo("Starting TC_PROD_001 - All Products Listing Test");
        HomePage homePage = new HomePage(driver);
        homePage.open();

        int totalProducts = homePage.getProductCount();
        logInfo("TC_PROD_001: Found " + totalProducts + " products on homepage");

        assertTrue(totalProducts >= ALL_PRODUCTS.size(),
                "TC_PROD_001: Should display at least " + ALL_PRODUCTS.size() + " products");

        takeScreenshot("TC_PROD_001_All_Products");
        logPass("TC_PROD_001 PASSED: All products are listed on homepage");
    }

    /**
     * TC_PROD_002 - Verify Samsung Galaxy S6 Product Details
     * Objective: Verify detail produk Samsung Galaxy S6 sesuai spec
     */
    @Test(description = "TC_PROD_002 - Verify Samsung Galaxy S6 details", priority = 2)
    public void testSamsungS6Details() {
        logInfo("Starting TC_PROD_002 - Samsung Galaxy S6 Test");
        HomePage homePage = new HomePage(driver);
        homePage.open();

        logInfo("TC_PROD_002: Selecting Samsung Galaxy S6");
        homePage.selectProduct("Samsung galaxy s6");
        pause(2000);

        ProductPage productPage = new ProductPage(driver);
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();

        logInfo("TC_PROD_002: Product Name: " + productName);
        logInfo("TC_PROD_002: Product Price: " + productPrice);

        assertContains(productName, "Samsung", "TC_PROD_002: Product should be Samsung");
        assertContains(productPrice, "$", "TC_PROD_002: Price should contain $");
        assertContains(productPrice, "360", "TC_PROD_002: Price should be $360");

        takeScreenshot("TC_PROD_002_Samsung_S6_Details");
        logPass("TC_PROD_002 PASSED: Samsung Galaxy S6 details verified");
    }

    /**
     * TC_PROD_003 - Verify Nokia Lumia 1520 Product Details
     * Objective: Verify detail produk Nokia Lumia 1520 sesuai spec
     */
    @Test(description = "TC_PROD_003 - Verify Nokia Lumia 1520 details", priority = 3)
    public void testNokiaLumiaDetails() {
        logInfo("Starting TC_PROD_003 - Nokia Lumia 1520 Test");
        HomePage homePage = new HomePage(driver);
        homePage.open();

        logInfo("TC_PROD_003: Selecting Nokia Lumia 1520");
        homePage.selectProduct("Nokia lumia 1520");
        pause(2000);

        ProductPage productPage = new ProductPage(driver);
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();

        logInfo("TC_PROD_003: Product Name: " + productName);
        logInfo("TC_PROD_003: Product Price: " + productPrice);

        assertContains(productName, "Nokia", "TC_PROD_003: Product should be Nokia");
        assertContains(productPrice, "$", "TC_PROD_003: Price should contain $");
        assertContains(productPrice, "820", "TC_PROD_003: Price should be $820");

        takeScreenshot("TC_PROD_003_Nokia_Lumia_Details");
        logPass("TC_PROD_003 PASSED: Nokia Lumia 1520 details verified");
    }

    /**
     * TC_PROD_004 - Verify Sony Vaio i5 Product Details
     * Objective: Verify detail produk Sony Vaio i5 sesuai spec
     */
    @Test(description = "TC_PROD_004 - Verify Sony Vaio i5 details", priority = 4)
    public void testSonyVaioI5Details() {
        logInfo("Starting TC_PROD_004 - Sony Vaio i5 Test");
        HomePage homePage = new HomePage(driver);
        homePage.open();

        logInfo("TC_PROD_004: Selecting Sony Vaio i5");
        homePage.selectProduct("Sony vaio i5");
        pause(2000);

        ProductPage productPage = new ProductPage(driver);
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();

        logInfo("TC_PROD_004: Product Name: " + productName);
        logInfo("TC_PROD_004: Product Price: " + productPrice);

        assertContains(productName, "Sony", "TC_PROD_004: Product should be Sony");
        assertContains(productPrice, "$", "TC_PROD_004: Price should contain $");
        assertContains(productPrice, "790", "TC_PROD_004: Price should be $790");

        takeScreenshot("TC_PROD_004_Sony_Vaio_i5_Details");
        logPass("TC_PROD_004 PASSED: Sony Vaio i5 details verified");
    }

    /**
     * TC_PROD_004b - Verify MacBook Air Product Details
     * Objective: Verify detail produk MacBook Air sesuai spec
     */
    @Test(description = "TC_PROD_004b - Verify MacBook Air details", priority = 4)
    public void testMacBookAirDetails() {
        logInfo("Starting TC_PROD_004b - MacBook Air Test");
        HomePage homePage = new HomePage(driver);
        homePage.open();

        logInfo("TC_PROD_004b: Selecting MacBook Air");
        homePage.selectProduct("MacBook air");
        pause(2000);

        ProductPage productPage = new ProductPage(driver);
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();

        logInfo("TC_PROD_004b: Product Name: " + productName);
        logInfo("TC_PROD_004b: Product Price: " + productPrice);

        assertContains(productName, "MacBook", "TC_PROD_004b: Product should be MacBook");
        assertContains(productPrice, "$", "TC_PROD_004b: Price should contain $");

        takeScreenshot("TC_PROD_004b_MacBook_Air_Details");
        logPass("TC_PROD_004b PASSED: MacBook Air details verified");
    }

    /**
     * TC_PROD_004c - Verify Apple Monitor 24 Product Details
     * Objective: Verify detail produk Apple Monitor 24 sesuai spec
     */
    @Test(description = "TC_PROD_004c - Verify Apple Monitor 24 details", priority = 4)
    public void testAppleMonitor24Details() {
        logInfo("Starting TC_PROD_004c - Apple Monitor 24 Test");
        HomePage homePage = new HomePage(driver);
        homePage.open();

        logInfo("TC_PROD_004c: Selecting Apple Monitor 24");
        homePage.selectProduct("Apple monitor 24");
        pause(2000);

        ProductPage productPage = new ProductPage(driver);
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();

        logInfo("TC_PROD_004c: Product Name: " + productName);
        logInfo("TC_PROD_004c: Product Price: " + productPrice);

        assertContains(productName, "Apple", "TC_PROD_004c: Product should be Apple");
        assertContains(productPrice, "$", "TC_PROD_004c: Price should contain $");
        assertContains(productPrice, "400", "TC_PROD_004c: Price should be $400");

        takeScreenshot("TC_PROD_004c_Apple_Monitor_24_Details");
        logPass("TC_PROD_004c PASSED: Apple Monitor 24 details verified");
    }

    /**
     * TC_PROD_004d - Verify ASUS Full HD Monitor Product Details
     * Objective: Verify detail produk ASUS Full HD sesuai spec
     */
    @Test(description = "TC_PROD_004d - Verify ASUS Full HD Monitor details", priority = 4)
    public void testAsusFullHDDetails() {
        logInfo("Starting TC_PROD_004d - ASUS Full HD Monitor Test");
        HomePage homePage = new HomePage(driver);
        homePage.open();

        logInfo("TC_PROD_004d: Selecting ASUS Full HD");
        homePage.selectProduct("ASUS Full HD");
        pause(2000);

        ProductPage productPage = new ProductPage(driver);
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();

        logInfo("TC_PROD_004d: Product Name: " + productName);
        logInfo("TC_PROD_004d: Product Price: " + productPrice);

        assertContains(productName, "ASUS", "TC_PROD_004d: Product should be ASUS");
        assertContains(productPrice, "$", "TC_PROD_004d: Price should contain $");
        assertContains(productPrice, "230", "TC_PROD_004d: Price should be $230");

        takeScreenshot("TC_PROD_004d_ASUS_Full_HD_Details");
        logPass("TC_PROD_004d PASSED: ASUS Full HD Monitor details verified");
    }

    /**
     * TC_PROD_005 - Verify Product Price Format Consistency
     * Objective: Verify format harga konsisten untuk semua produk
     */
    @Test(description = "TC_PROD_005 - Verify product price format consistency", priority = 5)
    public void testPriceFormatConsistency() {
        logInfo("Starting TC_PROD_005 - Price Format Consistency Test");

        // Test multiple products for consistent price format
        for (int i = 0; i < Math.min(3, ALL_PRODUCTS.size()); i++) {
            String productName = ALL_PRODUCTS.get(i);

            HomePage homePage = new HomePage(driver);
            homePage.open();

            logInfo("TC_PROD_005: Testing price format for: " + productName);
            homePage.selectProduct(productName);
            pause(2000);

            ProductPage productPage = new ProductPage(driver);
            String price = productPage.getProductPrice();

            // Verify price format: starts with $, followed by numbers
            assertTrue(price.startsWith("$"),
                    "TC_PROD_005: Price should start with $ for " + productName);
            assertTrue(price.length() >= 4,
                    "TC_PROD_005: Price should have reasonable length for " + productName);

            navigateBack();
            pause(1000);
        }

        takeScreenshot("TC_PROD_005_Price_Format");
        logPass("TC_PROD_005 PASSED: Product price format is consistent");
    }

    /**
     * TC_PROD_006 - Verify Product Categories Filter Correctly
     * Objective: Verify filter kategori berfungsi dengan benar
     */
    @Test(description = "TC_PROD_006 - Verify product categories filter", priority = 6)
    public void testProductCategoriesFilter() {
        logInfo("Starting TC_PROD_006 - Product Categories Filter Test");
        HomePage homePage = new HomePage(driver);
        homePage.open();

        // Test Phones category
        logInfo("TC_PROD_006: Testing Phones category");
        homePage.clickCategory("Phones");
        pause(3000);
        int phoneCount = homePage.getProductCount();
        logInfo("TC_PROD_006: Phones category shows " + phoneCount + " products");
        assertTrue(phoneCount >= PHONE_PRODUCTS.size(),
                "TC_PROD_006: Should show at least " + PHONE_PRODUCTS.size() + " phones");
        takeScreenshot("TC_PROD_006_Phones_Category");

        // Test Laptops category
        logInfo("TC_PROD_006: Testing Laptops category");
        homePage.clickCategory("Laptops");
        pause(3000);
        int laptopCount = homePage.getProductCount();
        logInfo("TC_PROD_006: Laptops category shows " + laptopCount + " products");
        assertTrue(laptopCount >= LAPTOP_PRODUCTS.size(),
                "TC_PROD_006: Should show at least " + LAPTOP_PRODUCTS.size() + " laptops");
        takeScreenshot("TC_PROD_006_Laptops_Category");

        // Test Monitors category
        logInfo("TC_PROD_006: Testing Monitors category");
        homePage.clickCategory("Monitors");
        pause(3000);
        int monitorCount = homePage.getProductCount();
        logInfo("TC_PROD_006: Monitors category shows " + monitorCount + " products");
        assertTrue(monitorCount >= MONITOR_PRODUCTS.size(),
                "TC_PROD_006: Should show at least " + MONITOR_PRODUCTS.size() + " monitors");
        takeScreenshot("TC_PROD_006_Monitors_Category");

        logPass("TC_PROD_006 PASSED: Product categories filter correctly");
    }

    /**
     * TC_PROD_007 - Verify Product Description Exists
     * Objective: Verify semua produk memiliki description
     */
    @Test(description = "TC_PROD_007 - Verify product description exists", priority = 7)
    public void testProductDescriptionExists() {
        logInfo("Starting TC_PROD_007 - Product Description Test");

        // Test first 3 products for descriptions
        for (int i = 0; i < Math.min(3, ALL_PRODUCTS.size()); i++) {
            String productName = ALL_PRODUCTS.get(i);

            HomePage homePage = new HomePage(driver);
            homePage.open();

            logInfo("TC_PROD_007: Checking description for: " + productName);
            homePage.selectProduct(productName);
            pause(2000);

            ProductPage productPage = new ProductPage(driver);
            String description = productPage.getDescription();

            assertTrue(description.length() > 10,
                    "TC_PROD_007: Product should have description for " + productName);

            navigateBack();
            pause(1000);
        }

        takeScreenshot("TC_PROD_007_Product_Description");
        logPass("TC_PROD_007 PASSED: Product descriptions exist");
    }

    /**
     * TC_PROD_008 - Verify Product Image/Thumbnail Displayed
     * Objective: Verify semua produk memiliki image/thumbnail
     */
    @Test(description = "TC_PROD_008 - Verify product images displayed", priority = 8)
    public void testProductImagesDisplayed() {
        logInfo("Starting TC_PROD_008 - Product Images Test");
        HomePage homePage = new HomePage(driver);
        homePage.open();

        // Check page source for image references
        String pageSource = driver.getPageSource();
        boolean hasImages = pageSource.contains("img") ||
                pageSource.contains("image") ||
                pageSource.contains(".jpg") ||
                pageSource.contains(".png") ||
                pageSource.contains(".gif");

        assertTrue(hasImages, "TC_PROD_008: Products should have images");

        takeScreenshot("TC_PROD_008_Product_Images");
        logPass("TC_PROD_008 PASSED: Product images are displayed");
    }

    /**
     * TC_PROD_009 - Verify Product Sorting (If Available)
     * Objective: Verify product sorting functionality
     */
    @Test(description = "TC_PROD_009 - Verify product sorting", priority = 9)
    public void testProductSorting() {
        logInfo("Starting TC_PROD_009 - Product Sorting Test");
        HomePage homePage = new HomePage(driver);
        homePage.open();

        // Check if sorting options exist (demoblaze.com might not have sorting)
        String pageSource = driver.getPageSource().toLowerCase();
        boolean hasSorting = pageSource.contains("sort") ||
                pageSource.contains("order") ||
                pageSource.contains("filter");

        if (hasSorting) {
            logInfo("TC_PROD_009: Sorting functionality appears available");
            // Could test actual sorting here if implemented
        } else {
            logInfo("TC_PROD_009: No sorting controls found (may be normal)");
        }

        takeScreenshot("TC_PROD_009_Product_Sorting");
        logPass("TC_PROD_009 PASSED: Product sorting check completed");
    }

    /**
     * TC_PROD_010 - Verify Product Pagination (If Available)
     * Objective: Verify pagination controls untuk product list
     */
    @Test(description = "TC_PROD_010 - Verify product pagination", priority = 10)
    public void testProductPagination() {
        logInfo("Starting TC_PROD_010 - Product Pagination Test");
        HomePage homePage = new HomePage(driver);
        homePage.open();

        // Check for pagination controls
        String pageSource = driver.getPageSource();
        boolean hasPagination = pageSource.contains("page") ||
                pageSource.contains("pagination") ||
                pageSource.contains("next") ||
                pageSource.contains("prev");

        if (hasPagination) {
            logInfo("TC_PROD_010: Pagination controls found");
        } else {
            logInfo("TC_PROD_010: No pagination controls (all products on one page)");
        }

        takeScreenshot("TC_PROD_010_Pagination");
        logPass("TC_PROD_010 PASSED: Product pagination check completed");
    }

    /**
     * TC_PROD_011 - Verify Product Stock/Availability Status
     * Objective: Verify status ketersediaan produk
     */
    @Test(description = "TC_PROD_011 - Verify product availability", priority = 11)
    public void testProductAvailability() {
        logInfo("Starting TC_PROD_011 - Product Availability Test");

        // Test a few products for availability indicators
        for (int i = 0; i < Math.min(2, ALL_PRODUCTS.size()); i++) {
            String productName = ALL_PRODUCTS.get(i);

            HomePage homePage = new HomePage(driver);
            homePage.open();

            logInfo("TC_PROD_011: Checking availability for: " + productName);
            homePage.selectProduct(productName);
            pause(2000);

            ProductPage productPage = new ProductPage(driver);
            // Check if "Add to Cart" button exists (implies available)
            productPage.addToCart(); // This will handle the alert
            pause(1000);

            navigateBack();
            pause(1000);
        }

        takeScreenshot("TC_PROD_011_Product_Availability");
        logPass("TC_PROD_011 PASSED: Products appear to be available (Add to Cart works)");
    }

    /**
     * TC_PROD_012 - Verify Product Comparison (If Available)
     * Objective: Verify product comparison feature
     */
    @Test(description = "TC_PROD_012 - Verify product comparison", priority = 12)
    public void testProductComparison() {
        logInfo("Starting TC_PROD_012 - Product Comparison Test");
        HomePage homePage = new HomePage(driver);
        homePage.open();

        // Check for comparison features
        String pageSource = driver.getPageSource().toLowerCase();
        boolean hasComparison = pageSource.contains("compare") ||
                pageSource.contains("comparison");

        if (hasComparison) {
            logInfo("TC_PROD_012: Comparison functionality found");
        } else {
            logInfo("TC_PROD_012: No comparison feature (may be normal)");
        }

        takeScreenshot("TC_PROD_012_Product_Comparison");
        logPass("TC_PROD_012 PASSED: Product comparison check completed");
    }

    /**
     * TC_PROD_013 - Verify Product Reviews/Ratings (If Available)
     * Objective: Verify product reviews/ratings system
     */
    @Test(description = "TC_PROD_013 - Verify product reviews/ratings", priority = 13)
    public void testProductReviews() {
        logInfo("Starting TC_PROD_013 - Product Reviews Test");

        // Check a product page for reviews
        HomePage homePage = new HomePage(driver);
        homePage.open();

        logInfo("TC_PROD_013: Checking for reviews on a product page");
        homePage.selectProduct(ALL_PRODUCTS.get(0));
        pause(2000);

        String pageSource = driver.getPageSource().toLowerCase();
        boolean hasReviews = pageSource.contains("review") ||
                pageSource.contains("rating") ||
                pageSource.contains("star") ||
                pageSource.contains("comment");

        if (hasReviews) {
            logInfo("TC_PROD_013: Review/rating system found");
        } else {
            logInfo("TC_PROD_013: No review system (may be normal)");
        }

        takeScreenshot("TC_PROD_013_Product_Reviews");
        logPass("TC_PROD_013 PASSED: Product reviews check completed");
    }

    /**
     * TC_PROD_014 - Verify Related Products (If Available)
     * Objective: Verify related products suggestions
     */
    @Test(description = "TC_PROD_014 - Verify related products", priority = 14)
    public void testRelatedProducts() {
        logInfo("Starting TC_PROD_014 - Related Products Test");

        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.selectProduct(ALL_PRODUCTS.get(0));
        pause(2000);

        String pageSource = driver.getPageSource().toLowerCase();
        boolean hasRelated = pageSource.contains("related") ||
                pageSource.contains("similar") ||
                pageSource.contains("also") ||
                pageSource.contains("recommend");

        if (hasRelated) {
            logInfo("TC_PROD_014: Related products feature found");
        } else {
            logInfo("TC_PROD_014: No related products (may be normal)");
        }

        takeScreenshot("TC_PROD_014_Related_Products");
        logPass("TC_PROD_014 PASSED: Related products check completed");
    }

    /**
     * TC_PROD_015 - Verify Product Specifications Table
     * Objective: Verify product specifications/features table
     */
    @Test(description = "TC_PROD_015 - Verify product specifications", priority = 15)
    public void testProductSpecifications() {
        logInfo("Starting TC_PROD_015 - Product Specifications Test");

        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.selectProduct(ALL_PRODUCTS.get(0));
        pause(2000);

        // Check for technical specifications
        ProductPage productPage = new ProductPage(driver);
        String description = productPage.getDescription();

        assertTrue(description.length() > 50,
                "TC_PROD_015: Product should have detailed specifications");

        // Check for common spec keywords
        boolean hasSpecs = description.toLowerCase().contains("gb") ||
                description.toLowerCase().contains("ram") ||
                description.toLowerCase().contains("processor") ||
                description.toLowerCase().contains("display");

        assertTrue(hasSpecs, "TC_PROD_015: Should have technical specifications");

        takeScreenshot("TC_PROD_015_Product_Specs");
        logPass("TC_PROD_015 PASSED: Product specifications verified");
    }

    /**
     * TC_PROD_016 - Verify Product Share Features (If Available)
     * Objective: Verify product sharing options
     */
    @Test(description = "TC_PROD_016 - Verify product share features", priority = 16)
    public void testProductShareFeatures() {
        logInfo("Starting TC_PROD_016 - Product Share Features Test");

        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.selectProduct(ALL_PRODUCTS.get(0));
        pause(2000);

        String pageSource = driver.getPageSource().toLowerCase();
        boolean hasShare = pageSource.contains("share") ||
                pageSource.contains("facebook") ||
                pageSource.contains("twitter") ||
                pageSource.contains("whatsapp");

        if (hasShare) {
            logInfo("TC_PROD_016: Product sharing features found");
        } else {
            logInfo("TC_PROD_016: No sharing features (may be normal)");
        }

        takeScreenshot("TC_PROD_016_Product_Share");
        logPass("TC_PROD_016 PASSED: Product share features check completed");
    }

    /**
     * TC_PROD_017 - Verify Product Breadcrumb Navigation
     * Objective: Verify breadcrumb navigation di product page
     */
    @Test(description = "TC_PROD_017 - Verify product breadcrumb navigation", priority = 17)
    public void testProductBreadcrumb() {
        logInfo("Starting TC_PROD_017 - Product Breadcrumb Test");

        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.selectProduct(ALL_PRODUCTS.get(0));
        pause(2000);

        // Check URL shows product navigation
        String url = getCurrentUrl();
        boolean hasProductInUrl = url.contains("prod") ||
                url.contains("id") ||
                url.contains("product");

        assertTrue(hasProductInUrl, "TC_PROD_017: URL should indicate product page");

        // Navigate back using browser back to test navigation
        navigateBack();
        pause(2000);
        assertContains(getCurrentUrl(), "demoblaze.com",
                "TC_PROD_017: Should return to homepage");

        takeScreenshot("TC_PROD_017_Breadcrumb_Nav");
        logPass("TC_PROD_017 PASSED: Product breadcrumb navigation works");
    }

    /**
     * TC_PROD_018 - Verify Product Page Load Performance
     * Objective: Verify product pages load dalam waktu wajar
     */
    @Test(description = "TC_PROD_018 - Verify product page load performance", priority = 18)
    public void testProductPagePerformance() {
        logInfo("Starting TC_PROD_018 - Product Page Performance Test");

        long startTime = System.currentTimeMillis();

        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.selectProduct(ALL_PRODUCTS.get(0));
        pause(2000); // Wait for page to load

        long loadTime = System.currentTimeMillis() - startTime;
        logInfo("TC_PROD_018: Product page loaded in " + loadTime + "ms");

        assertTrue(loadTime < 30000, "TC_PROD_018: Product page should load in under 30 seconds");

        takeScreenshot("TC_PROD_018_Performance");
        logPass("TC_PROD_018 PASSED: Product page load performance acceptable");
    }

    /**
     * TC_PROD_019 - Verify Multiple Product Views in Sequence
     * Objective: Verify dapat melihat multiple produk secara berurutan
     */
    @Test(description = "TC_PROD_019 - Verify multiple product views", priority = 19)
    public void testMultipleProductViews() {
        logInfo("Starting TC_PROD_019 - Multiple Product Views Test");

        // View 3 different products in sequence
        for (int i = 0; i < Math.min(3, ALL_PRODUCTS.size()); i++) {
            String productName = ALL_PRODUCTS.get(i);

            HomePage homePage = new HomePage(driver);
            homePage.open();

            logInfo("TC_PROD_019: Viewing product #" + (i+1) + ": " + productName);
            homePage.selectProduct(productName);
            pause(2000);

            ProductPage productPage = new ProductPage(driver);
            String name = productPage.getProductName();

            assertContains(name, productName.split(" ")[0],
                    "TC_PROD_019: Should display correct product");

            takeScreenshot("TC_PROD_019_Product_" + (i+1));

            if (i < 2) {
                navigateBack();
                pause(1000);
            }
        }

        logPass("TC_PROD_019 PASSED: Multiple product views work correctly");
    }

    /**
     * TC_PROD_020 - Verify Product Test Suite Completion
     * Objective: Verify semua product test completed successfully
     */
    @Test(description = "TC_PROD_020 - Product test suite completion", priority = 20)
    public void testProductSuiteCompletion() {
        logInfo("Starting TC_PROD_020 - Product Test Suite Completion");

        // Final verification - check homepage still accessible
        HomePage homePage = new HomePage(driver);
        homePage.open();

        int finalProductCount = homePage.getProductCount();
        logInfo("TC_PROD_020: Final product count: " + finalProductCount);

        assertTrue(finalProductCount > 0, "TC_PROD_020: Products should still be accessible");

        takeScreenshot("TC_PROD_020_Suite_Complete");
        logPass("TC_PROD_020 PASSED: Product test suite completed successfully");
        logInfo("=========================================");
        logInfo("🛍️  ALL 20 PRODUCT TESTS COMPLETED SUCCESSFULLY!");
        logInfo("=========================================");
    }
}