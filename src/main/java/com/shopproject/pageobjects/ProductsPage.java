package com.shopproject.pageobjects;

import com.shopproject.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage extends BaseClass {

    //WebElements localization
    @FindBy(xpath = "//a[normalize-space()='Men']")
    WebElement menFilterBtn;

    @FindBy(xpath = "//a[normalize-space()='Tshirts']")
    WebElement tshirtsFilterBtn;

    @FindBy(xpath = "(//a[text()[contains(.,'View Product')]])[1]")
    WebElement firstListedItem;
//    @FindBy(xpath = "(//a[contains(text(),'View Product')])[1]")
//    WebElement firstListedItem;

    //WebElements initialization
    public ProductsPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //Set filters to Men - Tshirt.
    public void filterItems() {
        action.fluentWait(getDriver(), menFilterBtn, 5);
        action.scrollByVisibilityOfElement(getDriver(), menFilterBtn);
     //   action.scrollByVisibilityOfElement(getDriver(), menFilterBtn);
        action.click1(menFilterBtn, "menFilterBtn");
     //   driverWait.until(ExpectedConditions.elementToBeClickable(tshirtsFilterBtn));
        action.scrollByVisibilityOfElement(getDriver(), tshirtsFilterBtn);
        action.click1(tshirtsFilterBtn, "tshirtsFilterBtn");
    }

    //Click on first listed product.
    public AddToCartPage clickOnProduct() {
            action.scrollByVisibilityOfElement(getDriver(), firstListedItem);
            action.click1(firstListedItem, "firstListedItem");
        return new AddToCartPage();
    }

}
