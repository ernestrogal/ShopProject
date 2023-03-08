package com.shopproject.pageobjects;

import com.shopproject.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatedPage extends BaseClass {

    //WebElements localization.
    @FindBy(xpath = "//b[text()[contains(.,'Account Created!')]]")
    WebElement accountCreatedConfirmation;

    //WebElements initialization.
    public AccountCreatedPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //Verify if Account created confirmation message is displayed.
    public boolean verifyIfAccountCreated() {
        return accountCreatedConfirmation.isDisplayed();
    }
}
