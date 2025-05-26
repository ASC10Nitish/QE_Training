package seleniumWebAppTest;

import org.testng.annotations.Test;

public class ProductTest extends TestBase {

	@Test(priority = 0)
	public void VerifyLogin() throws Exception {

		lp.login();

	}

	@Test(priority = 1)
	public void VerifyAdminMenu() throws Exception {

		pp.navigateToEchoPop();
		pp.addProductToCart();
		pp.goToCartAndVerify();

	}

	@Test(priority = 2)
	public void VerifyToAddAndPlaceOrder() throws Exception {

		cp.AddAnotherItem();
		cp.ContinueToOrder();

	}

}
