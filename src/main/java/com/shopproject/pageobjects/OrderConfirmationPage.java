package com.shopproject.pageobjects;

import com.shopproject.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage extends BaseClass {

    //WebElements localization.
    @FindBy(xpath = "//b[normalize-space()='Order Placed!']")
    WebElement confirmMessageTxt;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    WebElement continueBtn;

    //WebElements initialization.
    public OrderConfirmationPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //Verify if Order confirmation message is displayed.
    public boolean verifyConfirmMessage() {
        action.fluentWait(getDriver(), confirmMessageTxt, 5);
        boolean confirmMsg = confirmMessageTxt.isDisplayed();
        return confirmMsg;
    }

    //Click on Continue button.
    public HomePage clickOnContinue() {
        action.click(getDriver(), continueBtn);
        return new HomePage();
    }
}
