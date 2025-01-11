package StepDefinition;

import java.io.IOException;

import org.testng.Assert;

import base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.Dashboard;
import pageObjects.LoginPage;
import pageObjects.OrderConfirmation;
import pageObjects.PlaceOrderPage;
import pageObjects.ProductCatalogue;

public class ImplementationClass extends BaseClass {
	
	LoginPage login;
	ProductCatalogue products;
	Dashboard dash;
	PlaceOrderPage placeorder;
	OrderConfirmation orderconfirm;
	
	@Given("Launching the application")
	public void Login_the_application() throws IOException {
		launchApplication();
		login = new LoginPage();
		products = new ProductCatalogue();
		dash = new Dashboard();
		placeorder = new PlaceOrderPage();
		orderconfirm = new OrderConfirmation();
	}
	
	@Given("^Login with username (.+) and password (.+) and I verify the (.+) in step$")
	public void Login_with_Username_and_Password_Verify_Status(String username, String password,String status) {
		String result = login.LoginToApplication(username,password);
		getDriver().quit();
		Assert.assertEquals(result, status.toLowerCase());
	}

}
