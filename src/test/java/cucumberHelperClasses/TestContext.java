package cucumberHelperClasses;
//
//import managers.PageObjectManager;
//import managers.WebDriverManager;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import TestAutomation.utilities.Base;

public class TestContext {
	WebDriver driver;
	Properties prop;
	private Base webDriverManager = new Base();
	public WebDriver getWebDriverManager() throws IOException {
		this.driver =  webDriverManager.initializeDriver();
		System.out.println("started browser here");
		return driver;
	}
	public Properties getProperties() throws IOException {
		this.prop =  webDriverManager.loadpropertiesFile();
		return prop;
	}

}
