package com.helpers.util;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.helpers.configuration.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WebDriverFactory {

    private WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(WebDriverFactory.class);

    //Pick browser driver from the config file
    public WebDriver getDriver(String driverType) {

        log.info("Creating Webdriver instance");
        try {
            switch (driverType.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "":
                    throw new NoSuchFieldException();
                default:
                    System.out.println("Invalid driver type, cannot start browser instance!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;

    }

    @Autowired
    public String getWebDriverInstanceInfo(WebDriver driver) {
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
