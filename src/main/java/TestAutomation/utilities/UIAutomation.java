package TestAutomation.utilities;

import static org.testng.Assert.fail;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIAutomation extends Base{
	private static WebDriver driver;
	
	private static final int _120_SECONDS = 120;
	private static final int NUMBER_OF_TRIES_TO_CLICK = 4;
	private static final String CLICK_ERROR_MSG_ELEMENT_NOT_CLICKABLE = "Failure in Class [ UiInteraction ] Method [ ClickElement ] - The test tried " + NUMBER_OF_TRIES_TO_CLICK + " times to scroll-Up for element visibility without success";
	private static final String CLICK_ERROR_MSG_GENERIC_EXCEPTION = "Failure in Class [ UiInteraction ] Method [ ClickElement ] - The test found an Exception, with the ErrorMessage: ";
	
	public void waitForObjectAndClick(WebDriver driver, By locator) throws Exception
	{
		this.driver=driver;
		waitForVisibility(locator);
		clickElement(locator);
	}
	
	public static Wait<WebDriver> getWebDriverWait() {
		return new WebDriverWait(driver, _120_SECONDS);
	}
	
	
	public void waitForVisibility(By locator) {
		this.driver = driver;
		UIAutomation.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void clickElement(By selector) throws Exception{
		
		this.scrollToElement(selector);
		UIAutomation.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(selector));

		boolean flagForClick = this.getFlagAfterTryToAvoidOverlap(selector);
		if (!flagForClick) {
			fail(CLICK_ERROR_MSG_ELEMENT_NOT_CLICKABLE);
		}
	}
	
	public void scrollToElement(By locator) throws Exception {
		if (this.isElementPresent(locator)) {
			try {
				WebElement element = driver.findElement(locator);
				this.executeJavaScript("arguments[0].scrollIntoView();", element);
				this.pause(2000); // Allow time for scroll to complete
			} catch (NoSuchElementException e) {
				System.out.println("Element was present but can no longer be located");
				throw new CustomException(driver, e);
			}
		}

	}
	public boolean isElementPresent(By locator) {
		return !driver.findElements(locator).isEmpty();
	}
	
	public Object executeJavaScript(String script, WebElement targetElement) {
		return ((JavascriptExecutor) driver).executeScript(script, targetElement);
	}
	
	public void pause(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			System.out.println("Thread sleep interrupted");
		}
		
	}
	
	private static boolean getFlagForClickFromScrollToClickablePoint(By selector, boolean scrollUp) {
		boolean flagForClick = false;
		int numberOfTries = NUMBER_OF_TRIES_TO_CLICK;

		if (!scrollUp) {
			numberOfTries = 1; // Scrolldown just once, for very few cases
		}

		for (int i = 0; i < numberOfTries; i++) {
			try {
				driver.findElement(selector).click();
				//				driver.findElement(ExpectedConditions.elementToBeClickable(selector)).click();
				flagForClick = true;
				break;
			} catch (Exception ex) { // unknown Exception
				if (!ex.getMessage().contains("not clickable at point")) {
					fail(CLICK_ERROR_MSG_GENERIC_EXCEPTION + " - " + ex.getMessage());
				}
				driver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
			}
		}
		return flagForClick;
	}
	private  boolean getFlagAfterTryToAvoidOverlap(By selector) {
		boolean flagForClick = false;

		// Try ScrollDown *A few cases have been detected that require this
		flagForClick = UIAutomation.getFlagForClickFromScrollToClickablePoint(selector, false);

		if (!flagForClick) {
			// Try ScrollDown *Must of the times the issue is avoid with this
			flagForClick = UIAutomation.getFlagForClickFromScrollToClickablePoint(selector, true);
		}

		return flagForClick;
	}
public void waitForObjectAndEnterAddress(WebDriver driver, By locator, String text) {
		
		this.driver=driver;
		waitForVisibility(locator);
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(text);
	}

public void waitForElementVisibility(By locator) {
	// TODO Auto-generated method stub
	
}

}
