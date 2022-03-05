package stepDefinitions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestAutomation.utilities.DBUtility;
import TestAutomation.utilities.UIAutomation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPage{
	
	public WebDriver driver;
	public Properties prop;
	
	public LoginPage(Hooks testContext) throws IOException {
		driver = testContext.driver;
		prop = testContext.prop;
		
	}

	UIAutomation uiAutomation = new UIAutomation();
	DBUtility dbUtility = new DBUtility();
	
	By signIn_Email = By.xpath("//input[@id='session_email']");
	By signIn_Password = By.xpath("//input[@id='session_password']");
	By signIn_Button = By.xpath("//input[@type='submit']");
	By signUp_button = By.cssSelector("//a[href*='Sign up']");

	
	@Given("^User is on signIn Page$")
	public void user_aready_on_login_page() throws IOException {
//		driver.get(prop.getProperty("url"));
//		driver.manage().window().maximize();		
//		log.info("Web Browser initiated");
	}
	@When("^Title of SignIn Page is AddressBook SignIn$")
	public void validate_SignInPage_Title()
	{
		
	}
	@Then("User enters {word} and {word}")
	public void enterUserNameAndPassword(String userName, String passWord)
	{
		uiAutomation.waitForObjectAndEnterAddress(driver,signIn_Email ,userName);
		uiAutomation.waitForObjectAndEnterAddress(driver,signIn_Password ,passWord);
	}
	@And("User clicks on SignIn button")
	public void click_SignIn_button() throws Exception
	{
		uiAutomation.waitForObjectAndClick(driver,signIn_Button);
	}
	@Then("User is on home Page")
	public void validateHomePage() throws SQLException
	{
		String query = "select * from employee where empid=1001";
		String assigneeNameValidator =  dbUtility.getValueFromResultSetString(query, "EMPNAME");
		System.out.println("Employee name is "+assigneeNameValidator);
	}
}
