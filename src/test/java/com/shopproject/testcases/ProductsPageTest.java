package com.shopproject.testcases;

import com.shopproject.base.BaseClass;
import com.shopproject.pageobjects.AddToCartPage;
import com.shopproject.pageobjects.HomePage;
import com.shopproject.pageobjects.ProductsPage;
import com.shopproject.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//Testing Products Page functionalities.
public class ProductsPageTest extends BaseClass {

    HomePage homePage;
    ProductsPage productsPage;
    AddToCartPage addToCartPage;

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void tearDown() {
        getDriver().quit();
    }

    //Testing if the user can filter products.
    @Test(groups = "Sanity")
    public void testFilterProducts() throws InterruptedException {
        Log.startTestCase("testFilterProducts");
        homePage = new HomePage();
        Log.info("Navigating to /products.");
        productsPage = homePage.navigateToProducts();
        Log.info("Closing vignette ad.");
        homePage.closeVignetteAd();
        Log.info("Filtering products.");
        productsPage.filterItems();
        Log.info("Products filtered.");
        Assert.assertEquals(homePage.getCurrentUrl(), prop.getProperty("url") + "category_products/3");
        Log.info("Products filtering was successfully processed.");
        Log.endTestCase("testFilterProducts");
    }

    //Testing if the user can inspect the product.
    @Test(groups = "Sanity")
    public void testInspectProduct() throws Throwable {
        Log.startTestCase("testInspectProducts");
        homePage = new HomePage();
        Log.info("Navigating to /products.");
        productsPage = homePage.navigateToProducts();
        Log.info("Closing vignette ad.");
        homePage.closeVignetteAd();
        Log.info("Inspecting first product.");
        addToCartPage = productsPage.clickOnProduct();
        boolean flag = addToCartPage.verifyCartBtn();
        Assert.assertTrue(flag);
        Log.info("Viewing specific product.");
        Log.endTestCase("testInspectProducts");
    }
}
