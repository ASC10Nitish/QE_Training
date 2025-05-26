package seleniumWebApp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class ProductPage {

	private static final Logger logger = LogManager.getLogger(ProductPage.class);

	WebDriver driver;
	WebDriverWait wait;

	public ProductPage(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void navigateToEchoPop() throws Exception {
		logger.info("Navigating to Echo Pop");

		WebElement allMenu = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.//span[text()='All']]")));
		highlightElement(allMenu);
		logger.info("Clicking 'All' menu");
		allMenu.click();
		Thread.sleep(3000);

		WebElement echoAlexa = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[@class='hmenu-item' and .//div[text()='Echo & Alexa']]")));
		highlightElement(echoAlexa);
		logger.info("Clicking 'Echo & Alexa' category");
		echoAlexa.click();
		Thread.sleep(3000);

		WebElement alexaDevicesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//a[@href='/gp/browse.html?node=14156834031&ref_=nav_em__shopall_catpage_0_2_2_2' and text()='See all devices with Alexa']")));
		highlightElement(alexaDevicesLink);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", alexaDevicesLink);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", alexaDevicesLink);
		logger.info("Clicked 'See all devices with Alexa'");
		Thread.sleep(3000);
	}

	public void addProductToCart() throws Exception {
		logger.info("Clicking 'Echo Pop' product image");

		WebElement echoPopImage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//img[@alt='Echo Pop| Smart speaker with Alexa and Bluetooth| Loud sound, balanced bass, crisp vocals| Green']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int yOffset = echoPopImage.getLocation().getY();
		for (int i = 0; i <= yOffset; i += 50) {
			js.executeScript("window.scrollTo(0, arguments[0]);", i);
			Thread.sleep(30);
		}
		js.executeScript("window.scrollBy(0, -100);");
		highlightElement(echoPopImage);
		echoPopImage.click();
		Thread.sleep(3000);

		logger.info("Clicking 'Add to Cart' button");

		WebElement addToCartButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='add-to-cart-button']")));
		int yOffset1 = addToCartButton.getLocation().getY();
		for (int i = 0; i <= yOffset1; i += 50) {
			js.executeScript("window.scrollTo(0, arguments[0]);", i);
			Thread.sleep(20);
		}
		js.executeScript("window.scrollBy(0, -100);");
		highlightElement(addToCartButton);
		addToCartButton.click();
		Thread.sleep(3000);
	}

	public void goToCartAndVerify() throws Exception {
		logger.info("Handling 'No thanks' modal");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a-popover-5")));
		By noThanksLocator = By.xpath("//*[text()='No thanks' or contains(text(),'No thanks')]");
		WebElement noThanksBtn = wait.until(ExpectedConditions.elementToBeClickable(noThanksLocator));
		highlightElement(noThanksBtn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", noThanksBtn);
		Thread.sleep(3000);

		logger.info("Clicking 'Go to Cart' link");

		WebElement goToCartLink = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[@data-csa-c-content-id='sw-gtc_CONTENT' and normalize-space(text())='Go to Cart']")));
		highlightElement(goToCartLink);
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", goToCartLink);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", goToCartLink);

		Thread.sleep(3000);
		String currentUrl = driver.getCurrentUrl();
		logger.info("Current URL after clicking 'Go to Cart': " + currentUrl);
		Assert.assertTrue(currentUrl.contains("/cart"), "Expected URL to contain '/cart'");
		logger.info("Successfully navigated to cart");
	}

	public void verifyAmazonCartURL(String expectedUrlPart) throws Exception {
		logger.info("Verifying current URL contains: " + expectedUrlPart);
		Thread.sleep(3000);
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains(expectedUrlPart), "Current URL does not contain expected part.");
		logger.info("URL verification passed");
	}

	private void highlightElement(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		String highlightStyle = "border: 3px solid yellow; background-color: yellow; "
				+ "box-shadow: 0 0 10px 5px black; transition: all 0.3s ease-in-out;";
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, highlightStyle);
		Thread.sleep(700);
	}

}
