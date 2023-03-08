package com.shopproject.pageobjects;

import com.shopproject.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BaseClass {

    //WebElements localization.
    @FindBy(xpath = "//button[@class='disabled']")
    WebElement cartQuantityBox;
    @FindBy(className = "cart_price")
    WebElement cartItemPrice;
    @FindBy(className = "cart_total_price")
    WebElement cartTotalPrice;

    @FindBy(xpath = "//a[@class='btn btn-default check_out']")
    WebElement checkoutBtn;

    //WebElements initialization.
    public CartPage() {
        PageFactory.initElements(getDriver(), this);
    }

    // Check if quantity value can be changed on the Cart Page level.
    public boolean tryCartQuantityBox(String cartQuantityNo) {
        String cartQ = getQuantity();
        System.out.println("CartQ is: " + cartQ);
        String cartQNew = cartQuantityNo;
        System.out.println("CartQNew is: " + cartQNew);
        setQuantity(cartQNew);
        String cartQN = getQuantity();
        System.out.println("CartQN fetched from the box is: " + cartQN);
        return cartQNew.equals(cartQN);
    }

    //Verify if total price = item price * quantity.
    public boolean verifyTotalPrice() {
        //Getting value of the Total price then parsing it into double.
        String totalCost = cartTotalPrice.getText();
        double totalCostInt = Double.parseDouble(totalCost.replaceAll("\\D", ""));
        //Getting value of the Item price then parsing it into double.
        String itemCost = cartItemPrice.getText();
        double itemCostInt = Double.parseDouble(itemCost.replaceAll("\\D", ""));
        //Getting value of the quantity box then parsing it into integer.
        String quantityString = cartQuantityBox.getText();
        int quantityInt = Integer.parseInt(quantityString);
        System.out.println("Item price is: " + itemCostInt + " multiplied by quantity: " + quantityInt + " | Total: " + (itemCostInt * quantityInt));
        return (itemCostInt * quantityInt == totalCostInt);
    }

    //Click on checkout button.
    public CheckoutPage clickOnCheckout() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        action.click1(checkoutBtn, "checkoutBtn");
        return new CheckoutPage();
    }

    //Verify if checkout button is displayed.
    public boolean verifyCheckoutBtn() {
        return checkoutBtn.isDisplayed();
    }

    //Getter for quantity button.
    public String getQuantity() {
        return cartQuantityBox.getText();
    }

    //Setter for quantity button.
    public void setQuantity(String cartQuantityNo) {
        action.type(cartQuantityBox, cartQuantityNo);
    }
}
