package TestAutomation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class Base {

	public static Logger log = LogManager.getLogger(Base.class.getName());
	public WebDriver driver;
	public Properties prop;
	
	public Properties loadpropertiesFile() throws FileNotFoundException, IOException
	{
		prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\TestAutomation\\testRunnerItems\\data.properties");
		prop.load(fis);
		return prop;
	}
	
	public WebDriver initializeDriver() throws IOException
	{
		System.out.println("BeforTest");
		loadpropertiesFile();
		String browser = prop.getProperty("browser");
		System.out.println(browser);
		if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\lalit\\eclipse-workspace\\UIAPISelenium\\src\\main\\java\\TestAutomation\\resources\\chromedriver.exe");
			driver = new ChromeDriver();			
		}
		else if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\lalit\\eclipse-workspace\\UIAPISelenium\\src\\main\\java\\TestAutomation\\resources\\chromedriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equals("edge"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\lalit\\eclipse-workspace\\UIAPISelenium\\src\\main\\java\\TestAutomation\\resources\\chromedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		return driver;
	}
	public String getScreenShotPath(String TestcaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile=System.getProperty("user.dir")+"\\reports\\Screenshots\\"+TestcaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
		
	}
	
	
}