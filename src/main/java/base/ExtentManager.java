package base;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	    private static ExtentReports extent;

	    public static ExtentReports getInstance() {
	        if (extent == null) {
	            extent = new ExtentReports();
	            File file = new File(System.getProperty("user.dir") + "\\Reports\\report" + System.currentTimeMillis() + ".html");
	            ExtentSparkReporter reporter = new ExtentSparkReporter(file);
	            reporter.config().setReportName("Automation Suite");
	            reporter.config().setDocumentTitle("Test Report");
	            extent.attachReporter(reporter);
	        }
	        return extent;
	}
	
}
