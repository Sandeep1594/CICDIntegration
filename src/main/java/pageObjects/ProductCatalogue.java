package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BaseClass;
import base.Utilities;

public class ProductCatalogue {
	
	WebDriver driver = BaseClass.getDriver();
	
	Utilities util = new Utilities();
	By spinner = By.xpath("//div[@class='overlay-container']");
	By toastMsg= By.id("toast-container");
	
	public void selectProduct(List<String> productname) {
	
		
	List<WebElement> products = driver.findElements(By.xpath("//div/div[@class='card-body']"));
	
	List<String> desiredProducts = productname;
	
	desiredProducts.forEach(item -> {
	    WebElement prod = products.stream()
	            .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(item))
	            .findFirst()
	            .orElse(null);

	    if (prod != null) {	
	    	prod.findElement(By.xpath(".//button[@class='btn w-10 rounded']")).click();
	    	try {
	    		util.waitUntilNonVisible(spinner, 2);
	    		util.waitByVisibility(toastMsg,2);
	    	}
	    	catch(Exception e){
	    		System.out.println("Toast Message not displayed "+e);
	    	}
	    }
	});

	util.clickTheCart();
	
	}
}