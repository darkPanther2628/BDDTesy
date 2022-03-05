package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import TestAutomation.utilities.Base;
import cucumberHelperClasses.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends Base{
	public WebDriver driver;
	public Properties prop;
	
	public Hooks(TestContext testContext) throws IOException
	{
		driver = testContext.getWebDriverManager();
		prop = testContext.getProperties();
	}
	
	@Before("not @LoginTests")
    public void beforeScenario(){
		System.out.println("I am here 1");
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();		
		log.info("Web Browser initiated");
    }	
	
	@After("not @LoginTests")
    public void afterScenario(){
		System.out.println("I am here 2");
		driver.close();
    }
	@Before("@LoginTests")
    public void beforeLoginTestScenario(){
		System.out.println("I am before login test 1");
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();		
		log.info("Web Browser initiated");
    }	
	
	@After("@LoginTests")
    public void afterLoginTestScenario(){
		System.out.println("I am after login test 2");
		driver.close();
    }

}
