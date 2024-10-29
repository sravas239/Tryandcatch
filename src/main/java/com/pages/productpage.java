package com.pages;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class productpage {

    WebDriver driver;

 // Locators
    @FindBy(css = ".inventory_item_name")
    WebElement firstProduct;

    @FindBy(css = ".btn_primary.btn_inventory")
    WebElement addToCartButton;

    @FindBy(id = "shopping_cart_container")
    WebElement cartButton;

    // Constructor
    public productpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods
    public void addFirstProductToCart() {
        firstProduct.click();
        addToCartButton.click();
    }

    public void goToCart() {
        cartButton.click();
    }

	

	

	public void addProductToCart() {
		// TODO Auto-generated method stub
		
	}

	
}
  





