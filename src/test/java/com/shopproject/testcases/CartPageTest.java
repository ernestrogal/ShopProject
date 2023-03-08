package com.shopproject.testcases;

import com.shopproject.base.BaseClass;
import com.shopproject.pageobjects.*;
import com.shopproject.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CartPageTest extends BaseClass {

    HomePage homePage;
    LoginPage loginPage;
    ProductsPage productsPage;
    AddToCartPage addToCartPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void tearDown() {
        getDriver().quit();
    }

    //helper method should be included in a helper class
    //Navigate to the specific product page logged in.
    private AddToCartPage navigateToAddToCartPageLoggedIn() throws Throwable {
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
        Log.info("Filtering products and inspecting first product.");
        return addToCartPage = productsPage.clickOnProduct();
    }

    //helper method should be included in a helper class
    //Navigate to the specific product page.
    private AddToCartPage navigateToAddToCartPage() throws Throwable {
        homePage = new HomePage();
        Log.info("Navigating to /products.");
        productsPage = homePage.navigateToProducts();
        Log.info("Closing vignette ad.");
        homePage.closeVignetteAd();
        Log.info("Inspecting first product.");
        addToCartPage = productsPage.clickOnProduct();
        return addToCartPage;
    }

    //Testing if the correct product has been added to the cart.
    @Test(groups = {"Smoke", "Regression"})
    public void testCartValidation() throws Throwable {
        Log.startTestCase("testCartValidation");
        addToCartPage = navigateToAddToCartPage();
        Log.info("Viewing specific product and adding to the cart.");
        Log.info("Getting product id for verification purposes.");
        String productId = homePage.getCurrentUrl();
        productId = productId.replaceAll(prop.getProperty("url"), "");
        System.out.println("Our product ID: " + productId);
        addToCartPage.clickOnAddToCart();
        Log.info("Navigating to the cart page.");
        cartPage = addToCartPage.clickOnViewCart();
        Log.info("Checking if the product ID matches the product ID of item in the cart.");
        Assert.assertTrue(getDriver().getPageSource().contains(productId));
        Log.info("Verification successful.");
        Log.endTestCase("testCartValidation");
    }

    //Testing if the user is able to change the quantity value on the Cart Page level.
    @Test(groups = "Sanity")
    public void testCartQuantityBox() throws Throwable {
        Log.startTestCase("testCartQuantityBox");
        addToCartPage = navigateToAddToCartPage();
        Log.info("Viewing specific product and adding to the cart.");
        addToCartPage.clickOnAddToCart();
        Log.info("Navigating to the cart page then trying to change quantity.");
        cartPage = addToCartPage.clickOnViewCart();
        boolean flag = cartPage.tryCartQuantityBox("5");
        // Assert depends on value of quantityEnabled in Config file
        if (prop.getProperty("quantityEnabled").equalsIgnoreCase("true")) {
            System.out.println("Is quantity enabled: " + flag);
            Assert.assertTrue(flag);
            Log.info("Quantity can be changed on the cart page level.");
            Log.endTestCase("testCartQuantityBox");
        } else if (prop.getProperty("quantityEnabled").equalsIgnoreCase("false")) {
            System.out.println("Is quantity enabled: " + flag);
            Assert.assertFalse(flag);
            Log.info("Quantity can not be changed on the cart page level.");
            Log.endTestCase("testCartQuantityBox");
        }
    }

    //Testing if the Total Price is calculated correctly.
    @Test(groups = "Regression")
    public void testTotalPrice() throws Throwable {
        Log.startTestCase("testTotalPrice");
        addToCartPage = navigateToAddToCartPage();
        Log.info("Viewing specific product, setting product quantity and adding to the cart.");
        addToCartPage.setQuantity(prop.getProperty("quantity"));
        addToCartPage.clickOnAddToCart();
        Log.info("Navigating to the cart page and verifying Total Price.");
        cartPage = addToCartPage.clickOnViewCart();
        boolean flag = cartPage.verifyTotalPrice();
        Assert.assertTrue(flag);
        Log.info("Total price calculated correctly.");
        Log.endTestCase("testTotalPrice");
    }

    //Testing if the user can proceed to checkout.
    @Test(groups = {"Sanity", "Regression"})
    public void testProceedToCheckout() throws Throwable {
        Log.startTestCase("testProceedToCheckout");
        addToCartPage = navigateToAddToCartPageLoggedIn();
        Thread.sleep(5000);
        Log.info("Viewing specific product, adding product to the cart and viewing cart.");
        addToCartPage.clickOnAddToCart();
        cartPage = addToCartPage.clickOnViewCart();
        Log.info("Click on Proceed to checkout then Place Order and verifying result.");
        checkoutPage = cartPage.clickOnCheckout();
        paymentPage = checkoutPage.clickOnPlaceOrder();
        boolean flag = paymentPage.isCardNameBoxDisplayed();
        System.out.println("Flag value is: " + flag);
        Assert.assertTrue(flag);
        Log.info("Redirection to the payment page successful.");
        Log.endTestCase("testProceedToCheckout");
    }
}
