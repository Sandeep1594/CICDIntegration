	package base;
	
	import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
	
	public class BaseClass {
		
			
		public Properties prop;
		//public static WebDriver driver;
	    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
		
		public String getProperty(String propertyKey) throws IOException {
			prop = new Properties();
	    	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\GlobalData.properties");
	    	prop.load(fis);
	    	return prop.getProperty(propertyKey);
		}
		
	    public void initializeDriver() throws IOException {
	    	
	    	String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") :prop.getProperty("browser");
	    	
	    	if(browserName.contains("chrome"))
	    	{
	    		ChromeOptions options = new ChromeOptions();
	    		WebDriverManager.chromedriver().setup();
	    		if(browserName.contains("headless")) {
	    		options.addArguments("headless");
	    		}
	    	
	          driver.set(new ChromeDriver(options));
	        
	    }
	    	else if(browserName.equalsIgnoreCase("edge")){
	    		
	    		WebDriverManager.edgedriver().setup();
	           driver.set(new EdgeDriver()); 
	    	}
	    	getDriver().manage().window().maximize();
	    }
		
	    public static WebDriver getDriver() {
	    	return driver.get();
	    }
	    
		public void launchApplication() throws IOException {
			String url = getProperty("url");
			initializeDriver();
			getDriver().get(url);
		}
		
		// 1. Read the JSON Content and converted that data into list
		
		public List<HashMap<String, String>> readJson() throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\main\\java\\testData\\data.json"),
				StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		 
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		return data;
		}
		
		//2. Push the List data to the respective test cases whoever called
		@DataProvider
		public Object[][] getData() throws IOException {
					
			List<HashMap<String,String>> data = readJson();
			return new Object[][] {{data.get(0)}};	
		}
		
		//  Directly pass the data
		
		/*	@DataProvider
			public Object[][] getData() {
				
				return new Object[][] {{"sandeep1@gmail.com","Sandeep1@"},{"sandeep@gmail.com","Sandeep1@"}};
				
			}*/
		
		// Data Sent through HashMap with Key value pair
		
		/*	HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("email", "sandeep1@gmail.com");
			map.put("password", "Sandeep1@");
			
			HashMap<Object, Object> map1 = new HashMap<Object, Object>();
			map1.put("email", "sandeep@gmail.com");
			map1.put("password", "Sandeep1#"); 
			
			int i =0;
			Object[][] data=new();;
			while(data.size()!=0) {
				data1 = new Object[][] {{data.get(i)}};
				i=i+1;
		
			}
			return data1; */
		
	}
