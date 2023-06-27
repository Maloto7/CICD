package com.encora.demo.testUtils;


import com.encora.demo.frameworkCore.BaseUtility;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public class AllureListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Executing listener on faliure");
        WebDriver driver = BaseUtility.getDriver(result.getTestContext());
        System.out.println(driver);
        try{
            Allure.addAttachment(UUID.randomUUID().toString(),new ByteArrayInputStream(((TakesScreenshot)driver).
                    getScreenshotAs(OutputType.BYTES)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
