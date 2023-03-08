package com.shopproject.pageobjects;

import com.shopproject.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BaseClass {
    //WebElements localization.
    @FindBy(id = "id_gender1")
    private WebElement genderMaleRadioButton;

    @FindBy(id = "id_gender2")
    private WebElement genderFemaleRadioButton;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "days")
    private WebElement dayDropdown;

    @FindBy(id = "months")
    private WebElement monthDropdown;

    @FindBy(id = "years")
    private WebElement yearDropdown;

    @FindBy(xpath = "//input[@id='first_name']")
    private WebElement firstNameField;

    @FindBy(id = "last_name")
    private WebElement lastNameField;

    @FindBy(id = "address1")
    private WebElement addressField;

    @FindBy(id = "country")
    private WebElement countryDropdown;

    @FindBy(id = "state")
    private WebElement stateField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "zipcode")
    private WebElement zipCodeField;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberField;
    @FindBy(xpath = "//button[normalize-space()='Create Account']")
    WebElement createAccountBtn;

    //WebElements initialization.
    public RegisterPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //Send values to the registration form.
    public void fillRegisterForm(String uname, String email, String pswd, String gender, String dOfBirth, String mOfBirth, String yOfBirth,
                                  String firstName, String lastName, String address, String country, String state, String city,
                                 String zipcode, String mobileno) {
        //checking and click on correct Gender related button
        action.fluentWait(getDriver(), genderMaleRadioButton, 10);
        if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")) {
            action.click(getDriver(), genderMaleRadioButton);

        } else {
            action.click(getDriver(), genderFemaleRadioButton);
        }
        //filling rest of the form with values from TestData.xlsx SignUpData sheet
        action.type(nameField, uname);
        action.type(emailField, email);
        action.type(passwordField, pswd);
        action.selectByVisibleText(dOfBirth, dayDropdown);
        action.selectByVisibleText(mOfBirth, monthDropdown);
        action.selectByVisibleText(yOfBirth, yearDropdown);
        action.scrollByVisibilityOfElement(getDriver(), firstNameField);
        action.type(firstNameField, firstName);
        action.type(lastNameField, lastName);
        action.type(addressField, address);
        action.selectByVisibleText(country, countryDropdown);
        action.type(stateField, state);
        action.type(cityField, city);
        action.type(zipCodeField, zipcode);
        action.type(mobileNumberField, mobileno);
    }

    //Click on Create account button.
    public AccountCreatedPage clickOnCreateAccount() {
        action.scrollByVisibilityOfElement(getDriver(), createAccountBtn);
        action.click(getDriver(), createAccountBtn);
        return new AccountCreatedPage();
    }
}
