package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import base.BaseClass;
import base.Utilities;

public class PlaceOrderPage {
	
	WebDriver driver = BaseClass.getDriver();
	Utilities util = new Utilities();
	By displayCountriesList = By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']");
	
	public void enterShippingInfo() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		util.waitByVisibility(displayCountriesList, 5);
		List<WebElement> countries = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
		
		int i =0;
		while(countries.size()>i)
		{
			WebElement countryElement = countries.get(i);
			String country = countryElement.getText();
			if(country.equalsIgnoreCase("India"))
			{
				countryElement.click();
				break;
		}
		
			else {
				i++;
			}
	}
	}
	
	public void personalInfo(String creditCard,String cvvCode,String name) throws InterruptedException {
		
		WebElement cc = driver.findElement(By.xpath("//div[contains(text(), 'Credit Card Number')]/following-sibling::input"));
		cc.clear();
		cc.sendKeys(creditCard);
		WebElement dropdown = driver.findElement(By.xpath("//div[contains(text(),'Expiry Date')]/following-sibling::select[1]"));
		dropdown.click();
		Select select = new Select(dropdown);
		select.selectByVisibleText("03");
		WebElement dropdown2 = driver.findElement(By.xpath("//div[contains(text(),'Expiry Date')]/following-sibling::select[2]"));
		dropdown2.click();
		Select select2 = new Select(dropdown2);
		select2.selectByVisibleText("25");
		driver.findElement(By.xpath("//div[contains(text(),\"CVV Code\")]/following-sibling::input")).sendKeys(cvvCode);
		driver.findElement(By.xpath("//div[contains(text(),\"Name on Card\")]/following-sibling::input")).sendKeys(name);
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		
	}
	
}
