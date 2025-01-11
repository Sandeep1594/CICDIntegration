package Resources;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;

import base.BaseClass;
import base.ExtentManager;
import base.Utilities;

public class Listeners extends BaseClass implements ITestListener {
	Utilities utils = new Utilities();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	WebDriver driver;
	ExtentReports report = ExtentManager.getInstance();

	public void onTestStart(ITestResult result) {
	    // not implemented
		
		test= report.createTest(result.getMethod().getMethodName()); 
		extentTest.set(test);
        System.out.println("Test Method" +result.getMethod().getMethodName());
	  }

	  public void onTestSuccess(ITestResult result) {
		  
		  extentTest.get().log(Status.PASS, result.getMethod().getMethodName()+" Test case passed");

	            System.out.println("onTestSuccess - Test instance: " + result.getMethod().getMethodName());
		  }
	  
	  public void onTestSkipped(ITestResult result) {
		  if (result.wasRetried()) {
			  extentTest.get().log(Status.SKIP, "Retrying the test: " + result.getMethod().getMethodName());
			  onTestFailure(result);  
		  }		  
		  }
	  
	  
	  public void onTestFailure(ITestResult result) { 
		  
		        extentTest.get().fail(result.getThrowable());
		        try {
		        	driver = getDriver();
//		             driver = (WebDriver) result.getTestClass()
//		                    .getRealClass()
//		                    .getField("driver")
//		                    .get();
		            System.out.println("Driver details "+getDriver());
		            if (driver != null) {
		                String path = utils.takeScreenshot(result.getMethod().getMethodName(),driver);
		                test.addScreenCaptureFromPath(path);
		            } else {
		                System.out.println("onTestFailure - WebDriver is NULL, cannot capture screenshot.");
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    } 
		 		  
	  public void onFinish(ITestContext context) {
		    // not implemented
		  if (report != null) {
		  	report.flush();
		  }
	  }
}
