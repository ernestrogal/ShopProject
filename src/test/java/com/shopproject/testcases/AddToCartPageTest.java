package com.shopproject.testcases;

import com.shopproject.base.BaseClass;
import com.shopproject.pageobjects.AddToCartPage;
import com.shopproject.pageobjects.CartPage;
import com.shopproject.pageobjects.HomePage;
import com.shopproject.pageobjects.ProductsPage;
import com.shopproject.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddToCartPageTest extends BaseClass {
    HomePage homePage;
    ProductsPage productsPage;
    AddToCartPage addToCartPage;
    CartPage cartPage;

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void tearDown() { getDriver().quit(); }


    //helper method should be included in a helper class
    //Navigate to the specific product page.
    private AddToCartPage navigateToAddToCartPage() throws Throwable {
        homePage = new HomePage();
        Log.info("Navigating to /products.");
        productsPage = homePage.navigateToProducts();
        Log.info("Closing vignette ad.");
        homePage.closeVignetteAd();
        Log.info("Inspecting first product.");
        Thread.sleep(5000);
        return productsPage.clickOnProduct();
    }

    //Testing if the user can add the product to the cart.
    @Test(groups = {"Smoke", "Regression"})
    public void testAddToCart() throws Throwable {
        Log.startTestCase("testAddToCart");
        addToCartPage = navigateToAddToCartPage();
        Log.info("Adding product to the cart and viewing cart.");
        homePage.closeVignetteAd();
        addToCartPage.clickOnAddToCart();
        cartPage = addToCartPage.clickOnViewCart();
        Assert.assertTrue(cartPage.verifyCheckoutBtn());
        Log.info("Viewing cart page successful.");
        Log.endTestCase("testAddToCart");
    }

    //Testing if the user is able to change the quantity of the product.
    @Test(groups = "Smoke")
    public void testSetQuantity() throws Throwable {
        Log.startTestCase("testSetQuantity");
        addToCartPage = navigateToAddToCartPage();
        Log.info("Setting product quantity, adding to the cart and viewing cart.");
        addToCartPage.setQuantity(prop.getProperty("quantity"));
        addToCartPage.clickOnAddToCart();
        cartPage = addToCartPage.clickOnViewCart();
        Log.info("Verifying if given quantity was passed to the cart.");
        Assert.assertEquals(cartPage.getQuantity(), prop.getProperty("quantity"));
        Log.info("Verification successful");
        Log.endTestCase("testSetQuantity");
    }
}
