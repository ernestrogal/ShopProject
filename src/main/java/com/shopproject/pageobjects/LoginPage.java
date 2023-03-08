package com.shopproject.pageobjects;

import com.shopproject.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {

    //WebElements localization.
    @FindBy(xpath = "//input[@data-qa='login-email']")
    WebElement loginEmailBox;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement passwordBox;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement loginBtn;

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement registerNameBox;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement registerEmailBox;

    @FindBy(xpath = "//button[normalize-space()='Signup']")
    WebElement registerBtn;

    //WebElements initialization.
    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //Send values to the username and password login boxes.
    public HomePage login(String uname, String pswd) throws Throwable {
        action.type(loginEmailBox, uname);
        action.type(passwordBox, pswd);
        action.scrollByVisibilityOfElement(getDriver(), loginBtn);
        action.click1(loginBtn, "loginBtn");
        return new HomePage();
    }

    //Send values to the Name and Email registration boxes.
    public RegisterPage registerNewUser(String registerNewName, String registerNewEmail) throws Throwable {
        action.type(registerNameBox, registerNewName);
        action.type(registerEmailBox, registerNewEmail);
        action.click(getDriver(), registerBtn);
        WebElement emailTakenMessage = null;
        //Check if email is available to use.
        try {
            emailTakenMessage = getDriver().findElement(By.xpath("//p[text()='Email Address already exist!']"));
            System.out.println("Email is already in use");
            throw new AssertionError("Email is already in use");
        } catch (NoSuchElementException e) {
            System.out.println("Email taken message not found.");
        }
        return new RegisterPage();
    }
}
