package com.shopproject.pageobjects;

import com.shopproject.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BaseClass {

    //WebElements localization.
    @FindBy(xpath = "//input[@name='name_on_card']")
    WebElement cardNameBox;

    @FindBy(xpath = "//input[@name='card_number']")
    WebElement cardNumberBox;

    @FindBy(xpath = "//input[@class='form-control card-cvc']")
    WebElement cardCvcBox;

    @FindBy(xpath = "//input[@name='expiry_month']")
    WebElement cardExpiryMonthBox;

    @FindBy(xpath = "//input[@name='expiry_year']")
    WebElement cardExpiryYearBox;

    @FindBy(id = "submit")
    WebElement payAndConfirmOrderBtn;

    //WebElements initialization.
    public PaymentPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //Send value to the Card owner's name field.
    public void inputCardName(String cardOwner) {
        cardNameBox.clear();
        action.type(cardNameBox, cardOwner);
    }

    //Send value to the Card number field.
    public void inputCardNumber(String cardNumber) {
        cardNumberBox.clear();
        action.type(cardNumberBox, cardNumber);
    }

    //Send value to the Card CVC field.
    public void inputCardCvc(String cardCvc) {
        cardCvcBox.clear();
        action.type(cardCvcBox, cardCvc);
    }

    //Send values to the Card expiration fields.
    public void inputCardExpiration(String cardExpiryMonth, String cardExpiryYear) {
        cardExpiryMonthBox.clear();
        action.type(cardExpiryMonthBox, cardExpiryMonth);
        cardExpiryYearBox.clear();
        action.type(cardExpiryYearBox, cardExpiryYear);
    }

    //Click on Confirm order button.
    public OrderConfirmationPage clickOnPayAndConfirmOrder() {
        action.click(getDriver(), payAndConfirmOrderBtn);
        return new OrderConfirmationPage();
    }

    //Verify if Card owner name box is displayed.
    public boolean isCardNameBoxDisplayed() {
        return cardNameBox.isDisplayed();
    }

    //Getter for Card owner name box.
    public WebElement getCardNameBox() {
        return cardNameBox;
    }
}
