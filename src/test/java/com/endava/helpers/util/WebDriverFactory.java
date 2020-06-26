package com.endava.helpers.util;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverFactory {

    private static final Logger log = LoggerFactory.getLogger(WebDriverFactory.class);

    private static WebDriver driver;

    public static WebDriver getDriver(String driverType) {
        if (driver == null) {
            log.info("Creating Webdriver instance");
            try {
                switch (driverType.toLowerCase()) {
                    case "firefox":
                        driver = new FirefoxDriver();
                        break;
                    case "safari":
                        driver = new SafariDriver();
                        break;
                    case "chrome":
                        driver = new ChromeDriver();
                        break;
                    default:
                        System.out.println("Unable to find driver type of \"" + driverType + "\", starting default Chrome!");
                        driver = new ChromeDriver();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static String getWebDriverInstanceInfo() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        String os = cap.getPlatform().toString();
        String v = cap.getVersion();

        return "WebDriver instance info:"
                + "\n---------------"
                + "\n" + "Browser:" + browserName
                + "\n" + "Operating System: " + os
                + "\n" + "Browser Version: " + v
                + "\n---------------";
    }
}
