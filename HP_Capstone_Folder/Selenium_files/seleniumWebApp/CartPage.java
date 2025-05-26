//package seleniumWebApp;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class CartPage {
//
//	
//	WebDriver driver;
//	WebDriverWait wait;
//
//	public CartPage(WebDriver driver) {
//		this.driver = driver;
//		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	}
//	
//
//	public void AddAnotherItem()
//	{
//		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement incrementBtn = wait.until(ExpectedConditions.elementToBeClickable(
//		    By.xpath("//button[@data-a-selector='increment']")));
//		incrementBtn.click();
//
//
//	}
//	
//	public void ContinueToOrder()
//	{ 
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='proceedToRetailCheckout']")));
//		proceedToCheckoutButton.click();
//
//	}
//}

package seleniumWebApp;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage {

	private static final Logger logger = LogManager.getLogger(CartPage.class);

	WebDriver driver;
	WebDriverWait wait;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void AddAnotherItem() {
		logger.info("Starting AddAnotherItem method");

		try {
			logger.info("Waiting for the increment button (plus icon)");
			WebElement incrementBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-a-selector='increment']")));
			highlightElementIncrement(incrementBtn);
			Thread.sleep(3000);
			Assert.assertTrue(incrementBtn.isDisplayed(), "Increment button is not displayed");
			incrementBtn.click();
			logger.info("Clicked increment button to add one more item to cart");
			Thread.sleep(3000);
		} catch (Exception e) {
			logger.error("Error while adding another item to cart: " + e.getMessage());
			Assert.fail("Failed to add another item to cart");
		}
	}

	public void ContinueToOrder() {
		logger.info("Starting ContinueToOrder method");

		try {
			logger.info("Waiting for the 'Proceed to Checkout' button");
			WebElement proceedToCheckoutButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='proceedToRetailCheckout']")));
			highlightElement(proceedToCheckoutButton);
			Thread.sleep(3000);
			Assert.assertTrue(proceedToCheckoutButton.isDisplayed(), "Proceed to Checkout button is not displayed");
			proceedToCheckoutButton.click();
			logger.info("Clicked 'Proceed to Checkout' button");
			Thread.sleep(2000);
		} catch (Exception e) {
			logger.error("Error while proceeding to checkout: " + e.getMessage());
			Assert.fail("Failed to proceed to checkout");
		}
	}

	private void highlightElementIncrement(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		String highlightStyle = "border: 3px solid black; background-color: yellow; "
				+ "box-shadow: 0 0 10px 5px yellow; transition: all 0.3s ease-in-out;";
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, highlightStyle);
		Thread.sleep(700);
	}

	private void highlightElement(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		String highlightStyle = "border: 3px solid yellow; background-color: blue; "
				+ "box-shadow: 0 0 10px 5px yellow; transition: all 0.3s ease-in-out;";
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, highlightStyle);
		Thread.sleep(700);
	}

}
