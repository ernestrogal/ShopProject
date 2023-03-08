package com.shopproject.testcases;

import com.shopproject.base.BaseClass;
import com.shopproject.pageobjects.HomePage;
import com.shopproject.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//Testing HomePage functionalities
public class HomePageTest extends BaseClass {

    HomePage homePage;

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void tearDown() {
        getDriver().quit();
    }

    //Testing if the ShopProject logo is present.
    @Test(groups = "Smoke")
    public void testLogo() throws Throwable {
        Log.startTestCase("testLogo");
        homePage = new HomePage();
        Log.info("Checking for presence of ShopProject Logo.");
        boolean result = homePage.validateShopProjectLogo();
        Assert.assertTrue(result);
        Log.info("Logo is present.");
        Log.endTestCase("testLogo");
    }

    //Testing if the actual title of the ShopProject homepage is as expected.
    @Test(groups = "Smoke")
    public void testTitle() {
        Log.startTestCase("testTitle");
        homePage = new HomePage();
        Log.info("Getting title from actual window and verifying result.");
        String actualTitle = homePage.getShopProjectTitle();
        Assert.assertEquals(actualTitle, prop.getProperty("urlTitle"));
        Log.info("Actual title matches expected title.");
        System.out.println(actualTitle);
        Log.endTestCase("testTitle");
    }

}