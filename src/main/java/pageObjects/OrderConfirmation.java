package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import base.BaseClass;
import base.Utilities;

public class OrderConfirmation {

	WebDriver driver = BaseClass.getDriver();
	
	Utilities util = new Utilities();
	
	public boolean validateOrderConfirmation() {
		
		util.waitByVisibility(By.xpath("//h1[contains(text(),'Thankyou for the order')]"), 5);
		String order = driver.findElement(By.xpath("//h1[contains(text(),'Thankyou for the order')]")).getText();
		if(order.equalsIgnoreCase("Thankyou for the order."))
		{
				return true;
		}
		
		else
		{
			return false;
		}
	
	}
	
	
}
