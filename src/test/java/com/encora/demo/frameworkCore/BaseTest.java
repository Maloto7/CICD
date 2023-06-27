package com.encora.demo.frameworkCore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseTest {

    private WebDriver driver;

    public WebDriver createLocalDriver(){
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();
//		options.addArguments("start-maximized");
//		options.addArguments("enable-automation");
//		options.addArguments("--no-sandbox");
//		options.addArguments("--disable-infobars");
		options.addArguments("--headless");
//		options.addArguments("--disable-dev-shm-usage");
//		options.addArguments("--disable-browser-side-navigation");
//		options.addArguments("--disable-gpu");
		driver = new FirefoxDriver(options);
		return driver;
	}
}

