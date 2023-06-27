package com.encora.demo.frameworkCore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class BaseUtility {

	// Driver to find the elements
	private WebDriver basicPageDriver;
	private WebDriverWait wait;

	// Set the diver instance.
	public BaseUtility(ITestContext iTestContext, WebDriver driver) {
		this.basicPageDriver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		this.setDriver(iTestContext,driver);
		System.out.println("xx basicPageDriver");
		System.out.println(this.basicPageDriver);
	}

	// Navigate to a given URL
	public boolean navigateTo(String url) {
		try {
			basicPageDriver.navigate().to(url);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	// Get text of a element
	public String getTextFormElement(WebElement element) {
		try {
			if (waitForElementVisible(element)) {
				return element.getText();
			} else {
				return "";
			}

		} catch (Exception e) {
			return "I couldn't complety the getText action!!";
		}

	}

	// Get an atribute from of a element
	public String getAtributeFromElement(WebElement element, String attribute) {
		try {
			if (waitForElementVisible(element)) {
				return element.getAttribute(attribute);
			} else {
				return "";
			}

		} catch (Exception e) {
			return "I couldn't get the attribute";
		}

	}

	// Click a element
	public boolean clickOnElement(WebElement element) {

		try {
			if (waitForElementEnable(element)) {
				element.click();
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("This element doesn't exist!");
			return false;
		}
	}

	// Write on element
	public boolean writeOnElement(WebElement element, String text) {

		try {
			if (waitForElementEnable(element)) {
				element.clear();
				element.sendKeys(text);
				;
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("I couldn't send keys to this element");
			return false;
		}
	}


	// Wait for a element is visible for a given time
	public boolean waitForElementVisibleWithTime(WebElement element, int time) {
		if (element.isDisplayed()) {
			return true;
		} else {
			try {
				Wait<WebDriver> waitCustom = new FluentWait<WebDriver>(this.basicPageDriver)
						.withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofMillis(600))
						.ignoring(NoSuchElementException.class);

				System.out.println("Waiting for element to be visible");
				return waitCustom.until(ExpectedConditions.visibilityOf(element)).isDisplayed();

			} catch (Exception e) {
				System.out.println("Waiting more or maybe this element is not going to be visible");
				return false;
			}

		}

	}

	// Wait for a element to be enable for a given time
	public boolean waitForElementEnabled(WebElement element, int time) {
		if (element.isEnabled()) {
			return true;
		} else {
			try {
				Wait<WebDriver> waitCustom = new FluentWait<WebDriver>(this.basicPageDriver)
						.withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofMillis(600))
						.ignoring(NoSuchElementException.class);

				System.out.println("Waiting for element to be visible");
				return waitCustom.until(ExpectedConditions.visibilityOf(element)).isEnabled();

			} catch (Exception e) {
				System.out.println("Waiting more or maybe this element is not going to be visible");
				return false;
			}

		}
	}

	// public booleaan Waits, Propiedades con waits, Refactorizar.

	public WebElement findMyElement(By locator) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		} catch (Exception e) {
			System.out.println("We can't find that element");
			return null;
		}

	}

	// Wait the default time set up before in the constructor
	public boolean waitForElementVisible(WebElement element) {
		try {
			return this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();

		} catch (Exception e) {
			System.out.println("Something happens to that element");
			return false;
		}
	}

	// Wait the default time set up before in the constructor
	public boolean waitForElementEnable(WebElement element) {
		try {
			return this.wait.until(ExpectedConditions.elementToBeClickable(element)) != null;

		} catch (Exception e) {
			System.out.println("Something happens to that element");
			return false;
		}
	}

	// Send a javascriptExecutor to verify if the DOM is ready, then verify is the
		// basicElements are on the screen.
		// isdisplayed, isenable, isnotdisplayed.
		public boolean verifyLoadVisible(List<WebElement> basicWebElements) {
			try {

				for (int i = 0; i < basicWebElements.size(); i++) {
					if (!waitForElementVisible(basicWebElements.get(i))) {
						System.out.println("Element on location " + basicWebElements.get(i).getLocation() + "is not displayed");
						return false;
					}
				}
				return true;

			} catch (Exception e) {
				System.out.println("Something happens, page is not ready");
				return false;
			}

		}

	public boolean verifyLoadEnable(List<WebElement> basicWebElements) {
		try {

			for (int i = 0; i < basicWebElements.size(); i++) {
				if (!waitForElementEnable(basicWebElements.get(i))) {
					System.out
							.println("Element on location " + basicWebElements.get(i).getLocation() + "is not enable");
					return false;
				}
			}
			return true;

		} catch (Exception e) {
			System.out.println("Something happens, page is not ready");
			return false;
		}

	}

	public boolean verifyDocumentVerify() {
		try {
			return true;
		} catch (Exception e) {
			System.out.println("Something happens, page is not ready");
			return false;
		}
	}
	

	
	public WebElement returnJustOnChild(WebElement element) 
	{ 
		WebElement child = element.findElement(By.xpath("./child::*"));
		return child;
	}

	public static WebDriver getDriver(ITestContext iTestContext){
		return (WebDriver) iTestContext.getAttribute("WebDriver");
	}
	public static void setDriver(ITestContext iTestContext, WebDriver driver){
		iTestContext.setAttribute("WebDriver",driver);
	}

}

