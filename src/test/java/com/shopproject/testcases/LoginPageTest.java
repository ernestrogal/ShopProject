package com.shopproject.testcases;

import com.shopproject.base.BaseClass;
import com.shopproject.dataprovider.DataProviders;
import com.shopproject.pageobjects.HomePage;
import com.shopproject.pageobjects.LoginPage;
import com.shopproject.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//Testing Login Page functionalities.
public class LoginPageTest extends BaseClass {
    HomePage homePage;
    LoginPage loginPage;

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void tearDown() {
        getDriver().quit();
    }

    //Testing if the user can log in with valid credentials.
    @Test(dataProvider = "credentials", dataProviderClass = DataProviders.class, groups = "Smoke")
    public void testValidLogin(String uname, String pswd) throws Throwable {
        Log.startTestCase("testValidLogin");
        homePage = new HomePage();
        Log.info("Clicking on Login/SignUp navigation menu link.");
        loginPage = homePage.login_registerClick();
        action.implicitWait(getDriver(), 10);
        Log.info("Sending values into Username and Password and verifying result.");
        //homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        homePage = loginPage.login(uname, pswd);
        String currentUrl = homePage.getCurrentUrl();
        Assert.assertEquals(currentUrl, prop.getProperty("url"));
        Log.info("User successfully logged.");
        Log.endTestCase("testValidLogin");
    }

    //Testing if the user can log in with invalid credentials.
    @Test(dataProvider = "credentials", dataProviderClass = DataProviders.class, groups = "Smoke")
    public void testInvalidLogin(String uname, String pswd) throws Throwable {
        Log.startTestCase("testInvalidLogin");
        homePage = new HomePage();
        Log.info("Clicking on Login/SignUp navigation menu link.");
        loginPage = homePage.login_registerClick();
        Log.info("Sending values into Username and Password and verifying result.");
       // homePage = loginPage.login(prop.getProperty("username") + "byleco", prop.getProperty("password") + "fail");
        homePage = loginPage.login(uname + "byleco", pswd + "fail");
        String currentUrl = homePage.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, prop.getProperty("url"));
        Log.info("User not logged in.");
        Log.endTestCase("testInvalidLogin");
    }

    //Testing if the user can log out after successful log in.
    @Test(groups = "Smoke")
    public void testLogout() throws Throwable {
        Log.startTestCase("testLogout");
        testValidLogin(prop.getProperty("username"), prop.getProperty("password"));
        Log.info("Attempting to log out and verifying result.");
        homePage.logOut();
        String currentUrl = homePage.getCurrentUrl();
        Assert.assertEquals(currentUrl, prop.getProperty("url") + "login");
        Log.info("Logout process successful");
        Log.endTestCase("testLogout");
    }
}
