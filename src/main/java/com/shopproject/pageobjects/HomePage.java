package com.shopproject.pageobjects;

import com.shopproject.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BaseClass {

    //WebElements localization.
    @FindBy(xpath = "//a[normalize-space()='Signup / Login']")
    WebElement login_registerBtn;

    @FindBy(xpath = "//img[@alt='Website for automation practice']")
    WebElement shopProjectLogoImg;

    @FindBy(xpath = "//a[@href='/products']")
    WebElement productsBtn;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement logoutBtn;

    @FindBy(xpath = "//div[@id='dismiss-button']")
    WebElement vignetteDismiss;

    @FindBy(xpath = "/html[1]/ins[1]/div[1]/iframe[1]")
    WebElement iframee;
    //WebElements initialization.
    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    //Click on login/register.
    public LoginPage login_registerClick() throws Throwable {
        action.click1(login_registerBtn, "login_registerBtn");
        return new LoginPage();
    }

    //Validate if Shop Logo is displayed.
    public boolean validateShopProjectLogo() throws Throwable {
        return action.isDisplayed(getDriver(), shopProjectLogoImg);
    }

    //Get the title of the current window.
    public String getShopProjectTitle() {
        String shopProjectTitle = getDriver().getTitle();
        return shopProjectTitle;
    }

//        public void closeVignetteAd() {
//        try {
//            action.explicitWait(getDriver(), vignetteDismiss, 5);
//            System.out.println("wait finished");
//            getDriver().switchTo().frame(iframee);
//            System.out.println("Switched to frame1");
//            WebElement frame2 = getDriver().findElement(By.id("ad_iframe"));
//            getDriver().switchTo().frame(frame2);
//            System.out.println("Switched to frame2");
//            action.click1(vignetteDismiss, "vignetteDismiss");
//            getDriver().switchTo().defaultContent();
//            System.out.println("Switched to default content");
//        }
//        catch (Exception e) {
//            getDriver().switchTo().defaultContent();
//            System.out.println("Switched to default content - exception");
//            e.printStackTrace();
//        }
//    }

 //   Close vignette Ad.
    public void closeVignetteAd() {
        String currUrl = getCurrentUrl();
        if (currUrl.contains("#google_vignette")) {
            try {
                //iFrames location.
                action.explicitWait(getDriver(), iframee, 5);
                System.out.println("Current url is: " + currUrl);
             //   WebElement frame1 = getDriver().findElement(By.id("aswift_5"));
                System.out.println("FRAME 1 is located: " + iframee);
                getDriver().switchTo().frame(iframee);
                System.out.println("Switched to frame1");
                action.click1(vignetteDismiss, "vignetteDismiss on frame1 level");
                WebElement frame2 = null;
                if (getDriver().findElements(By.id("ad_iframe")).size() > 0) {
                    frame2 = getDriver().findElement(By.id("ad_iframe"));
                    System.out.println("Frame2 located");
                } else {
                    System.out.println("Unable to locate element with id 'ad_iframe'");
                    return;
                }
                if (frame2 != null && frame2.isDisplayed()) {
                    getDriver().switchTo().frame(frame2);
                    System.out.println("Switched to frame2");
                    action.click1(vignetteDismiss, "vignetteDismiss on frame2 level");
                } else {
                    System.out.println("Frame with id 'ad_iframe' is detached");
                    return;
                }
                //Switching back to the default content from iFrames.
                getDriver().switchTo().defaultContent();
                System.out.println("Switching back to the Default Content.");
            } catch (Exception e) {
                getDriver().switchTo().defaultContent();
                System.out.println("Switching back to the Default Content.");
                e.printStackTrace();
            }
        }
        else {
            getDriver().switchTo().defaultContent();
            System.out.println("Switching back to the Default Content.");
            System.out.println("Ad was not displayed.");
        }
    }

    //Click on products navigation link.
    public ProductsPage navigateToProducts() throws InterruptedException {
        action.click(getDriver(), productsBtn);
        return new ProductsPage();
    }

    //Verify if logout button is displayed.
    public boolean logOut() throws Throwable {
        if (action.isDisplayed(getDriver(), logoutBtn)) {
            action.click(getDriver(), logoutBtn);
            return true;
        } else return false;
    }

    //Get the current url.
    public String getCurrentUrl() {
        String homePageUrl = action.getCurrentURL(getDriver());
        return homePageUrl;
    }
}