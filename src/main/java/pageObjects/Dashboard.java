package pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.BaseClass;
import base.Utilities;

public class Dashboard {
	

	// New comments to test CI CD test
	WebDriver driver = BaseClass.getDriver();
	Utilities util = new Utilities();
	
	public List<String> validateProductsInCart(List<String> products) throws InterruptedException {
	
	util.waitByVisibility(By.xpath("//h1[text()='My Cart']"), 5);
	List<WebElement> cartProds = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	List<String> cartProductNames= cartProds.stream().map(WebElement::getText).collect(Collectors.toList());
	
	//Boolean prodValidate = products.stream().allMatch(cartProductNames::contains);
	
	return cartProductNames;
	
	}
	
	public void proceedToCheckout() throws InterruptedException {

	WebElement checkout = driver.findElement(By.xpath("//button[text()='Checkout']"));

	long lastHeight = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

	while (true) {
	    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	    Thread.sleep(1000); // Wait for content to load if it's dynamic
	    
	    long newHeight = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
	    if (newHeight == lastHeight) {
	        break; // Exit loop if no new content is loaded
	    }
	    lastHeight = newHeight;
	}
	
	checkout.click();
	
	}
}
