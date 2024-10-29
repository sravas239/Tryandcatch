package com.test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.pages.Cartpage;
import com.pages.Checkoutoverview;
import com.pages.Checkoutpage;
import com.pages.Loginpage;
import com.pages.OrderConfirmationPage;
import com.pages.productpage;
import com.utilits.Extentutilits;
import com.utilits.Screenshotutilities;

public class ECommercesite {
	WebDriver driver;
	Loginpage loginPage;
	productpage productPage;
	Cartpage cartPage;
	Checkoutpage checkoutPage;
	Checkoutoverview CheckoutoverviewPage;
	OrderConfirmationPage orderConfirmationPage;

	private ExtentReports extent;
	private ExtentTest test;

	@BeforeClass
	public void setup() {
		
		Extentutilits.getExtentReports(); // Initialize the report
        test = Extentutilits.createTest("E-commerce Purchase Flow Test");
        test.info("Test setup initialized");
		driver = new ChromeDriver();
		extent = Extentutilits.getExtentReports();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com");
		loginPage = new Loginpage(driver);
		productPage = new productpage(driver);
		cartPage = new Cartpage(driver);
		checkoutPage = new Checkoutpage(driver);
		CheckoutoverviewPage = new Checkoutoverview(driver);
		orderConfirmationPage = new OrderConfirmationPage(driver);
	}

	@Test
	public void checkoutFlowTest() throws Exception {
		
		test.pass("Purchase flow completed successfully");
		try {
			// Login
			loginPage.login("standard_user", "secret_sauce");
			Screenshotutilities.captureScreenshot(driver, "Login_Success");
			System.out.println("Step 1: Login successful");
		} catch (Exception e) {
			Screenshotutilities.captureScreenshot(driver, "Login_Failure");
			Assert.fail("Failed to login: " + e.getMessage());
			   test.pass("Login successful");
		}

		try {
			// Add product to cart
			productPage.addFirstProductToCart();
			productPage.goToCart();
			Screenshotutilities.captureScreenshot(driver, "Product_Added_To_Cart");
			System.out.println("Step 2: Product added to cart");
		} catch (Exception e) {
			Screenshotutilities.captureScreenshot(driver, "Add_Product_Failure");
			Assert.fail("Failed to add product to cart: " + e.getMessage());
		}

		try {
			// Proceed to checkout
			cartPage.proceedToCheckout();
			Screenshotutilities.captureScreenshot(driver, "Checkout_Started");
			System.out.println("Step 3: Checkout started");
		} catch (Exception e) {
			Screenshotutilities.captureScreenshot(driver, "Checkout_Proceed_Failure");
			Assert.fail("Failed to proceed to checkout: " + e.getMessage());
			 test.pass("Product search successful");
		}

		try {
			// Enter shipping details
			checkoutPage.fillShippingDetails("sravani", "Guduru", "524406");
			Screenshotutilities.captureScreenshot(driver, "Shipping_Details_Entered");
			System.out.println("Step 4: Shipping details entered");
		} catch (Exception e) {
			Screenshotutilities.captureScreenshot(driver, "Shipping_Details_Failure");
			Assert.fail("Failed to enter shipping details: " + e.getMessage());
			 test.pass("Product added to cart");
		}

		 try {
	            // Step 5: Complete the purchase
	            CheckoutoverviewPage.completePurchase();
	            Screenshotutilities.captureScreenshot(driver, "Process_Completed");
	            test.pass("Purchase completed successfully");
	        } catch (Exception e) {
	            Screenshotutilities.captureScreenshot(driver, "Complete_Purchase_Failure");
	            test.fail("Complete purchase failed: " + e.getMessage());
	            Assert.fail("Complete purchase failed: " + e.getMessage());
	        }

//	        try {
//	            // Step 6: Verify order confirmation
//	            String confirmationMessage = orderConfirmationPage.getConfirmationMessage();
//	            Assert.assertEquals(confirmationMessage, "THANK YOU FOR YOUR ORDER");
//	            Screenshotutilities.captureScreenshot(driver, "Order_Confirmation_Page");
//	            test.pass("Order confirmation verified successfully");
//	        } catch (Exception e) {
//	            Screenshotutilities.captureScreenshot(driver, "Order_Confirmation_Failure");
//	            test.fail("Order confirmation failed: " + e.getMessage());
//	            Assert.fail("Order confirmation failed: " + e.getMessage());
//	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        Extentutilits.flushReports();
	        driver.quit();
	    }
	}
