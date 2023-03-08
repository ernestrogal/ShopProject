package com.shopproject.testcases;

import com.shopproject.base.BaseClass;
import com.shopproject.dataprovider.DataProviders;
import com.shopproject.pageobjects.AccountCreatedPage;
import com.shopproject.pageobjects.HomePage;
import com.shopproject.pageobjects.LoginPage;
import com.shopproject.pageobjects.RegisterPage;
import com.shopproject.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseClass {

    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
    AccountCreatedPage accountCreatedPage;

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
    public void tearDown() {
        getDriver().quit();
    }

    @Test(dataProvider = "searchProduct", dataProviderClass = DataProviders.class, groups = "Smoke")
    public void testRegisterAccount(String uname, String email, String pswd, String gender, String dOfBirth, String mOfBirth, String yOfBirth,
                                    String firstName, String lastName, String address, String country, String state, String city,
                                    String zipcode, String mobileno) throws Throwable {
        Log.startTestCase("testRegisterAccount");
        homePage = new HomePage();
        Log.info("Clicking on Login/SignUp navigation menu link.");
        loginPage = homePage.login_registerClick();
        action.implicitWait(getDriver(), 10);
        Log.info("Sending values into Name and Email.");
        registerPage = loginPage.registerNewUser(uname, email);
        Log.info("Filling out the registration form and verifying result.");
        registerPage.fillRegisterForm(uname, email, pswd, gender, dOfBirth, mOfBirth,
                                    yOfBirth, firstName, lastName, address, country, state, city, zipcode, mobileno);
        accountCreatedPage = registerPage.clickOnCreateAccount();
        boolean flag = accountCreatedPage.verifyIfAccountCreated();
        Assert.assertTrue(flag);
        Log.info("Account created successfully.");
        Log.endTestCase("testRegisterAccount");
    }
}
