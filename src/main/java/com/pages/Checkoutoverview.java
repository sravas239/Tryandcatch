package com.pages;


	

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Checkoutoverview {

    WebDriver driver;

 // Locators
    @FindBy(css="#finish")
    WebElement finishbutton;

    

    // Constructor
    public Checkoutoverview(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void finishbuttont() {
    	finishbutton.click();
    }

	public void completePurchase() {
		// TODO Auto-generated method stub
		
	}

}
