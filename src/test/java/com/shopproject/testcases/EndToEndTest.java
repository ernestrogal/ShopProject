package com.shopproject.testcases;

import com.shopproject.base.BaseClass;
import com.shopproject.pageobjects.*;
import com.shopproject.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EndToEndTest extends BaseClass {
    PaymentPage paymentPage;
    HomePage homePage;
    LoginPage loginPage;
    ProductsPage productsPage;
    AddToCartPage addToCartPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderConfirmationPage orderConfirmationPage;

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void tearDown() {
        getDriver().quit();
    }

    @Test(groups = "Regression")
    public void testEndToEnd() throws Throwable {
        Log.startTestCase("testEndToEnd");
        homePage = new HomePage();
        Log.info("Clicking on Login/SignUp navigation menu link.");
        loginPage = homePage.login_registerClick();
        action.implicitWait(getDriver(), 10);
        Log.info("Sending values into Username and Password.");
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Log.info("Navigating to /products.");
        productsPage = homePage.navigateToProducts();
        Log.info("Closing vignette ad.");
        homePage.closeVignetteAd();
        Log.info("Filtering products and adding the first product to the cart then viewing cart page.");
        productsPage.filterItems();
        homePage.closeVignetteAd();
        addToCartPage = productsPage.clickOnProduct();
     //   homePage.closeVignetteAd();
        addToCartPage.clickOnAddToCart();
        cartPage = addToCartPage.clickOnViewCart();
        Log.info("Click on Proceed to checkout then Place Order and verifying result.");
        checkoutPage = cartPage.clickOnCheckout();
        homePage.closeVignetteAd();
        paymentPage = checkoutPage.clickOnPlaceOrder();
        homePage.closeVignetteAd();
        Log.info("Filling Card details and verifying result.");
        paymentPage.inputCardName(prop.getProperty("fakeCardOwnerName"));
        paymentPage.inputCardNumber(prop.getProperty("fakeCardNumber"));
        paymentPage.inputCardCvc(prop.getProperty("fakeCardCvc"));
        paymentPage.inputCardExpiration(prop.getProperty("fakeCardExpirationMonth"),
                prop.getProperty("fakeCardExpirationYear"));
        orderConfirmationPage = paymentPage.clickOnPayAndConfirmOrder();
        boolean flag = orderConfirmationPage.verifyConfirmMessage();
        Assert.assertTrue(flag);
        Log.info("Payment completed.");
        Log.endTestCase("testEndToEnd");
    }
}
