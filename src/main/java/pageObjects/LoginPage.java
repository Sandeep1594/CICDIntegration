package pageObjects;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BaseClass;
import base.Utilities;

public class LoginPage{
	
	WebDriver driver = BaseClass.getDriver();
	
	Utilities util = new Utilities();
	By loginSuccessMsg = By.xpath("//div[@aria-label='Login Successfully']");
	
	public String LoginToApplication(String username, String password) {
		
	driver.findElement(By.id("userEmail")).sendKeys(username);			//"sandeep1@gmail.com"
	driver.findElement(By.id("userPassword")).sendKeys(password);				//Sandeep1@
	driver.findElement(By.id("login")).click();
	util.waitByVisibility(loginSuccessMsg, 5);
	WebElement element = driver.findElement(By.xpath("//*[contains(text(), 'Sign Out')]"));
	String result;
	if(element.isDisplayed())
	{
		result="pass";
	}
	else
	{
		result="fail";
	}
	
	return result;
	}
}
