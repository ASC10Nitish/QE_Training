package seleniumWebAppTest;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import seleniumWebApp.CartPage;
import seleniumWebApp.LoginPage;
import seleniumWebApp.ProductPage;


public class TestBase {

	public WebDriver driver;
	public LoginPage lp;
	public ProductPage pp;
	public CartPage cp;
	
	public Properties prop;

	@BeforeTest
	public void launchApp() throws Exception {
		System.out.println("========launchApp==========");

		
//		Properties is a built-in Java class used to read .properties files easily
		prop = new Properties();
//		prop i.e object will hold key-value pairs loaded from a configuration file (like mode=headless).
		
		FileInputStream fis = new FileInputStream("src/test/java/config.properties");
		prop.load(fis);

//      from prop object value is accessed in mode variable as a value from key
		String mode = prop.getProperty("mode");
		ChromeOptions options = new ChromeOptions();

		if ("headless".equalsIgnoreCase(mode)) {
			options.addArguments("--headless=new");
			options.addArguments("--window-size=1920,1080");
		}

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=900&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fyourstore%2Fhome%3Fpath%3D%252Fgp%252Fyourstore%252Fhome%26signIn%3D1%26useRedirectOnSuccess%3D1%26action%3Dsign-out%26ref_%3Dnav_AccountFlyout_signout&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		Thread.sleep(2000); 
        
		lp=new LoginPage(driver);
		pp = new ProductPage(driver);
		cp = new CartPage(driver);
	}

	@AfterTest
	public void closeApp() throws Exception {
		Thread.sleep(5000);
		System.out.println("========closeApp==========");
		driver.close();
		driver.quit();
	}

	public void highlight(WebElement element) {
		
		//JavascriptExecutor helps in executing javascript code on loaded page 
		//casting webdriver instance driver to 
		//webdriver doesnt has method executorscript to apply stying so casted to js
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
}
