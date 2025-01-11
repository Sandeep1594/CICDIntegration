package loginTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Resources.Retry;
import base.BaseClass;
import base.Utilities;
import pageObjects.Dashboard;
import pageObjects.LoginPage;
import pageObjects.ProductCatalogue;

public class TestCase01 extends BaseClass{
	LoginPage login;
	ProductCatalogue products;
	Dashboard dash;
	
	@BeforeMethod(alwaysRun = true)
	
	public void setup() throws IOException {
		launchApplication();
		login = new LoginPage();
		products = new ProductCatalogue();
		dash = new Dashboard();
	}
	
	@	AfterMethod(alwaysRun = true)
	public void tearDown() {
		 if (getDriver() != null) {
			 getDriver().quit();
			 
			 
	}
	}
	
	@Test(dataProvider= "getData",groups="Sanity")
	public void loginTest(HashMap<String, String> input) throws InterruptedException {
		
		String result = login.LoginToApplication(input.get("email"),input.get("password"));
		//	Assert.assertTrue(result);
	}
	
	Utilities utils = new Utilities();
	@Test(dataProvider="getData",groups={"Regression"})
	public void validateProductCart(HashMap<String, String> input) throws InterruptedException, IOException
	{		
	//	extent.createTest("Validate Products Cart Test");
		login.LoginToApplication(input.get("email"),input.get("password"));
		List<String> selectProducts = List.of("IPHONE 13 PRO","ADIDAS ORIGINAL","QWERTY");
		products.selectProduct(selectProducts);
			List<String> ItemsInCart = dash.validateProductsInCart(selectProducts);
			//utils.takeScreenshot("validateProductCart", getDriver());
		Assert.assertEquals(ItemsInCart.toString(),selectProducts.toString());
	
	}	
			
}
