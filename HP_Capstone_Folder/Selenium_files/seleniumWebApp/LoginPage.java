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

public class LoginPage {
	
	private static final Logger logger = LogManager.getLogger(LoginPage.class);
	
	WebDriver driver;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	
	public void login() throws Exception
	{ 
		logger.info("Entering User number ");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("7483730015");
		Thread.sleep(2000);
		logger.info("Clicked Submit ");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(2000);
		logger.info("Entering User password ");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Nitish@123");
		Thread.sleep(2000);
		logger.info("Clicked Sign in");
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
		Thread.sleep(2000);
		
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
