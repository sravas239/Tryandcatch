package com.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class EcommerceTestWithExceptionHandling {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.automationexercise.com");
    }

    @Test
    public void completePurchaseFlow() {
        performLogin();
        searchProduct();
        addToCart();
        proceedToCheckout();
    }

    private void performLogin() {
        try {
            WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Signup / Login']")));
            loginLink.click();

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-email']")));
            emailField.sendKeys("sravani95.guduru@gmail.com");

            WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
            passwordField.sendKeys("Sravas@08");

            WebElement loginButton = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
            loginButton.click();

            Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Logout']"))).isDisplayed(), "Login failed");

        } catch (NoSuchElementException | TimeoutException e) {
            System.err.println("Login failed: " + e.getMessage());
        }
    }

    private void searchProduct() {
        try {
            WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#search")));
            searchField.sendKeys("product name");

            WebElement searchButton = driver.findElement(By.id("searchButton"));
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

            Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-list"))).isDisplayed(), "Product search failed");

        } catch (NoSuchElementException | ElementNotInteractableException e) {
            System.err.println("Search failed: " + e.getMessage());
        }
    }

    private void addToCart() {
        try {
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Product Name")));
            productLink.click();

            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("addToCart")));
            addToCartButton.click();

            Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart"))).isDisplayed(), "Add to cart failed");

        } catch (StaleElementReferenceException e) {
            // Retry logic for StaleElementReferenceException
            System.err.println("Add to cart failed - Retrying due to StaleElementReferenceException: " + e.getMessage());
            try {
                WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Product Name")));
                productLink.click();
                
                WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("addToCart")));
                addToCartButton.click();
            } catch (Exception ex) {
                System.err.println("Retry failed for add to cart: " + ex.getMessage());
            }
        } catch (WebDriverException e) {
            System.err.println("Add to cart failed: " + e.getMessage());
        }
    }

    private void proceedToCheckout() {
        try {
            WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkoutButton")));
            checkoutButton.click();

            WebElement paymentFrame = driver.findElement(By.id("paymentFrame"));
            driver.switchTo().frame(paymentFrame);

            WebElement cardNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cardNumber")));
            cardNumberField.sendKeys("4111111111111111");

            WebElement expirationField = driver.findElement(By.id("expiryDate"));
            expirationField.sendKeys("12/25");

            WebElement cvvField = driver.findElement(By.id("cvv"));
            cvvField.sendKeys("123");

            WebElement payButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("payButton")));
            payButton.click();

            Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderConfirmation"))).isDisplayed(), "Order confirmation not found");

        } catch (NoSuchElementException e) {
            System.err.println("Checkout failed: " + e.getMessage());
        } catch (TimeoutException e) {
            System.err.println("Checkout process timed out: " + e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
