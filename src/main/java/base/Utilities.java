package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

	WebDriver driver = BaseClass.getDriver();
	
	public boolean waitByVisibility(By byLocator, int seconds)
	{
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(seconds));
	WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));  //"//div/div[@class='card-body']")
	return ele.isDisplayed();
	}
	
	public void waitUntilNonVisible(By byLocator, int seconds)
	{
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(seconds));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
	
	}
	
	public void clickTheCart() {
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	}
	
	public String takeScreenshot(String testcasename,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source  = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"//reports//"+testcasename+System.currentTimeMillis()+".png";
		File destination = new File(path);
		FileUtils.copyFile(source, destination);
		return path;
	}
	
}
