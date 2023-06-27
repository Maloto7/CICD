package com.encora.demo.Pages;

import com.encora.demo.frameworkCore.BaseUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;

public class WelcomePage extends BaseUtility{
    public @FindBy(xpath = "//*[@id='root']/div/h1") WebElement welcomeTitle;
    public WelcomePage(ITestContext iTestContext, WebDriver driver) {
        super(iTestContext, driver);
        PageFactory.initElements(driver, this);
    }
    public boolean verifyLoadWelcomeTitle() 
	{
		System.out.println("verifyLoadWelcomeTitle");
        return waitForElementVisible(welcomeTitle);
	}
    public String getTitleText()
    {
        return getTextFormElement(welcomeTitle);
    }
}
