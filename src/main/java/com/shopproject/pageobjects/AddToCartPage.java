package com.shopproject.pageobjects;

import com.shopproject.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddToCartPage extends BaseClass {

    //WebElements localization.
    @FindBy(id = "quantity")
    WebElement quantityBox;

    @FindBy(xpath = "//button[@type='button']")
    WebElement addToCartBtn;

    @FindBy(xpath = "//u[normalize-space()='View Cart']")
    WebElement viewCartBtn;

    @FindBy(xpath = "//div[@class='view-product']//img[@alt='ecommerce website products']")
    WebElement productId;

    //WebElements initialization.
    public AddToCartPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //Set quantity of the item.
    public void setQuantity(String quantity) {
        if (prop.getProperty("quantity") != null //&& !prop.getProperty("quantity").isBlank()
                && !prop.getProperty("quantity").isEmpty()) {
            System.out.println("Quantity in data set is: " + prop.getProperty("quantity"));
            action.click(getDriver(), quantityBox);
            quantityBox.clear();
            action.type(quantityBox, quantity);
        }
        else {
            System.out.println("Quantity was not set in data file. Actual value is: " + prop.getProperty("quantity"));
        }
    }

    //Click on Add to cart.
    public void clickOnAddToCart() {
        action.scrollByVisibilityOfElement(getDriver(), addToCartBtn);
        action.fluentWait(getDriver(), addToCartBtn, 10);
        action.click1(addToCartBtn, "addToCartBtn");
    }

    //Click on View Cart.
    public CartPage clickOnViewCart() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn));
        action.click1(viewCartBtn, "viewCartBtn");
        return new CartPage();
    }

    //Verify if add to cart button is displayed.
    public boolean verifyCartBtn() {
        return addToCartBtn.isDisplayed();
    }
}
