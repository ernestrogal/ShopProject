package com.shopproject.testcases;

import com.shopproject.base.BaseClass;
import com.shopproject.pageobjects.*;
import com.shopproject.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PaymentPageTest extends BaseClass {

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

    //Verify if the required fields are filled in.
    private void verifyRequiredFields() {
        if (prop.getProperty("fakeCardOwnerName").isEmpty() || prop.getProperty("fakeCardOwnerName").isBlank()
                && paymentPage.getCardNameBox().getAttribute("outerHTML").contains("required")) {
            System.out.println("Card name cannot be empty! Is set to: " + prop.getProperty("fakeCardOwnerName"));
        } else if (prop.getProperty("fakeCardNumber").isEmpty() || prop.getProperty("fakeCardNumber").isBlank()
                && paymentPage.getCardNameBox().getAttribute("outerHTML").contains("required")) {
            System.out.println("Card number cannot be empty! Is set to: " + prop.getProperty("fakeCardNumber"));
        } else if (prop.getProperty("fakeCardCvc").isEmpty() || prop.getProperty("fakeCardCvc").isBlank()
                && paymentPage.getCardNameBox().getAttribute("outerHTML").contains("required")) {
            System.out.println("Card cvc cannot be empty! Is set to: " + prop.getProperty("fakeCardCvc"));
        } else if (prop.getProperty("fakeCardExpirationMonth").isEmpty() || prop.getProperty("fakeCardExpirationMonth").isBlank()
                && paymentPage.getCardNameBox().getAttribute("outerHTML").contains("required")) {
            System.out.println("Card Expiration Month cannot be empty! Is set to: " + prop.getProperty("fakeCardExpirationMonth"));
        } else if (prop.getProperty("fakeCardExpirationYear").isEmpty() || prop.getProperty("fakeCardExpirationYear").isBlank()
                && paymentPage.getCardNameBox().getAttribute("outerHTML").contains("required")) {
            System.out.println("Card Expiration Year cannot be empty! Is set to: " + prop.getProperty("fakeCardExpirationYear"));
        } else System.out.println("All required fields are filled");
    }

    //Testing if the payment is processed with valid card data.
    @Test(groups = "Sanity")
    public void testValidPayment() throws Throwable {
        Log.startTestCase("testValidPayment");
        Log.info("Clicking on Login/SignUp navigation menu link.");
        homePage = new HomePage();
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
        addToCartPage = productsPage.clickOnProduct();
        addToCartPage.clickOnAddToCart();
        cartPage = addToCartPage.clickOnViewCart();
        Log.info("Click on Proceed to checkout then Place Order and verifying result.");
        checkoutPage = cartPage.clickOnCheckout();
        paymentPage = checkoutPage.clickOnPlaceOrder();
        Log.info("Filling Card details and verifying result.");
        paymentPage.inputCardName(prop.getProperty("fakeCardOwnerName"));
        paymentPage.inputCardNumber(prop.getProperty("fakeCardNumber"));
        paymentPage.inputCardCvc(prop.getProperty("fakeCardCvc"));
        paymentPage.inputCardExpiration(prop.getProperty("fakeCardExpirationMonth"),
                prop.getProperty("fakeCardExpirationYear"));
        verifyRequiredFields();
        orderConfirmationPage = paymentPage.clickOnPayAndConfirmOrder();
        boolean flag = orderConfirmationPage.verifyConfirmMessage();
        Assert.assertTrue(flag);
        Log.info("Payment completed.");
        Log.endTestCase("testValidPayment");

    }

    //Testing if payment is processed with invalid data.
    //Only requirement is any field not null; payment accepted with fake data;
    @Test(groups = "Sanity")
    public void testInvalidPayment() throws Throwable {
        Log.startTestCase("testInvalidPayment");
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
        addToCartPage = productsPage.clickOnProduct();
        addToCartPage.clickOnAddToCart();
        cartPage = addToCartPage.clickOnViewCart();
        Log.info("Click on Proceed to checkout then Place Order and verifying result.");
        checkoutPage = cartPage.clickOnCheckout();
        paymentPage = checkoutPage.clickOnPlaceOrder();
        Log.info("Filling Card details and verifying result.");
        paymentPage.inputCardName(prop.getProperty("fakeCardInvalidOwnerName"));
        paymentPage.inputCardNumber(prop.getProperty("fakeCardInvalidNumber"));
        paymentPage.inputCardCvc(prop.getProperty("fakeCardInvalidCvc"));
        paymentPage.inputCardExpiration(prop.getProperty("fakeCardInvalidExpirationMonth"),
                prop.getProperty("fakeCardInvalidExpirationYear"));
        //verifyRequiredFields();
        orderConfirmationPage = paymentPage.clickOnPayAndConfirmOrder();
        boolean flag = orderConfirmationPage.verifyConfirmMessage();
        Assert.assertTrue(flag);
        Log.info("Payment completed.");
        Log.endTestCase("testInvalidPayment");
    }

}
