package com.herokuapp.theinternet.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserDriverFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String browser;


	public BrowserDriverFactory(String browser) {
		this.browser = browser.toLowerCase();
	}


	public WebDriver createDriver() {
		System.out.println("Starting " + browser + " locally");

		// Creating driver
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver.set(new ChromeDriver());
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver.set(new FirefoxDriver());
			break;
		}

		return driver.get();
	}


	public WebDriver createDriverGrid() {
		String hubUrl = "http://192.168.99.100:4444/wd/hub";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		System.out.println("Starting " + browser + " on grid");

		// Creating driver
		switch (browser) {
		case "chrome":
			//capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
			capabilities.setBrowserName("chrome");
			capabilities.setPlatform(Platform.LINUX);
			ChromeOptions options = new ChromeOptions();
			options.merge(capabilities);
			break;

		case "firefox":
			//capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			capabilities.setBrowserName("firefox");
			capabilities.setPlatform(Platform.LINUX);
			FirefoxOptions options2 = new FirefoxOptions();
			options2.merge(capabilities);
			break;
		}

		try {
			driver.set(new RemoteWebDriver(new URL(hubUrl), capabilities));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return driver.get();
	}

}
