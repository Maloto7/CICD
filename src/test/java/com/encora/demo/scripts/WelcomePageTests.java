package com.encora.demo.scripts;


import com.encora.demo.Pages.WelcomePage;
import com.encora.demo.frameworkCore.BaseTest;
import com.encora.demo.frameworkCore.BaseUtility;
import com.encora.demo.testUtils.AllureListener;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.time.Duration;


@Listeners(AllureListener.class)
public class WelcomePageTests {
	BaseUtility baseUtility;
	BaseTest baseTest;
	public WebDriver driver;
	public WebDriverWait wait;
	
	public static WelcomePage welcomePage;

	@BeforeMethod
	public void setUp(ITestContext iTestContext) {
		baseTest = new BaseTest();
		this.driver = baseTest.createLocalDriver();
		baseUtility = new BaseUtility(iTestContext, this.driver);
		String url = "http://192.168.144.10:8083/";
		baseUtility.navigateTo(url);
		welcomePage = new WelcomePage(iTestContext,this.driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void WelcomePageLoadTest() {
		Assert.assertEquals(welcomePage.verifyLoadWelcomeTitle(), true);
	}


	@Epic("Epic")
	@Severity(SeverityLevel.CRITICAL)
	@Step("test step")
	@Description("test Description")
	@Test()
	public void WelcomePageLoadTextPassTest() {
		Assert.assertEquals(welcomePage.getTitleText(), "Welcome to the Encora demo");
	}

    @Test
	public void WelcomePageLoadTextFailTest() {
		Assert.assertEquals(welcomePage.getTitleText(), "Welcome to the Encora demo");
	}

	@AfterMethod
	public void afterMethod() {
		this.driver.quit();
		System.out.println("Driver destroy!!! D:");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("Execution completed!!!!!!!!!!!!");
	}
}
