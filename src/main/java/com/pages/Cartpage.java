package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cartpage {
    WebDriver driver;

    // Locators
    @FindBy(id = "checkout")
    WebElement checkoutButton;

    // Constructor
    public Cartpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods
    public void proceedToCheckout() {
        checkoutButton.click();
    }
}


	

