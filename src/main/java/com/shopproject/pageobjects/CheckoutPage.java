package com.shopproject.pageobjects;

import com.shopproject.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseClass {

    //WebElements localization.
    @FindBy(xpath = "//*[text()[contains(.,'Place Order')]]")
    WebElement placeOrderBtn;

    //WebElements initialization.
    public CheckoutPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //Click on place order button.
    public PaymentPage clickOnPlaceOrder() {
        try {
            action.scrollByVisibilityOfElement(getDriver(), placeOrderBtn);
            action.click1(placeOrderBtn, "placeOrderBtn");
            return new PaymentPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PaymentPage();
    }

    //Check if place order button is displayed.
    public boolean placeOrderBtnIsDisplayed() {
        action.scrollByVisibilityOfElement(getDriver(), placeOrderBtn);
        return placeOrderBtn.isDisplayed();
    }
}
