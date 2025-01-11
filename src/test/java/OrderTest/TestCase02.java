package OrderTest;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Resources.Retry;
import base.BaseClass;
import pageObjects.Dashboard;
import pageObjects.LoginPage;
import pageObjects.OrderConfirmation;
import pageObjects.PlaceOrderPage;
import pageObjects.ProductCatalogue;

public class TestCase02 extends BaseClass{

	
	LoginPage login;
	ProductCatalogue products;
	Dashboard dash;
	PlaceOrderPage placeorder;
	OrderConfirmation orderconfirm;
	
	@BeforeMethod(alwaysRun = true)
	public void setup() throws IOException {
		launchApplication();
		login = new LoginPage();
		products = new ProductCatalogue();
		dash = new Dashboard();
		placeorder = new PlaceOrderPage();
		orderconfirm = new OrderConfirmation();
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		 if (getDriver() != null) {
			 getDriver().quit();
	}
	}
	
	@Test(retryAnalyzer=Retry.class)
	public void orderConfirmationTest() throws InterruptedException {
		login.LoginToApplication("sandeep@gmail.com","Sandeep1#");
		List<String> selectProducts = List.of("IPHONE 13 PRO","ADIDAS ORIGINAL","QWERTY");
		products.selectProduct(selectProducts);
			List<String> ItemsInCart = dash.validateProductsInCart(selectProducts);
		Assert.assertEquals(ItemsInCart.toString(),selectProducts.toString());
		dash.proceedToCheckout();
		placeorder.enterShippingInfo();
		placeorder.personalInfo("4315487854876589", "342","Sandeep");
		Boolean orderConfirmation = orderconfirm.validateOrderConfirmation();
		Assert.assertTrue(orderConfirmation);

	}


}
