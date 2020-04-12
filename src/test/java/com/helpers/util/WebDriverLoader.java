package com.helpers.util;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.helpers.configuration.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


public class WebDriverLoader {

    private static WebDriver driver;

    private static Logger log = LoggerFactory.getLogger(WebDriverLoader.class);

    //Load  properties file

    public static void loadConfiguration() {
        log.info("Loading properties");
        ConfigFileReader.loadProperties();
    }

    //Pick browser driver from the config file
    public static WebDriver startBrowser() throws Exception {

        log.info("Creating Webdriver instance");
        try {

            switch (ConfigFileReader.getBrowser()) {
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
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;

    }


    public static String getInstanceInfo(WebDriver driver){
        Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        String os = cap.getPlatform().toString();
        String v = cap.getVersion().toString();

        String info = "Browser: " + browserName
                + "\n" + "Operating System: " + os
                + "\n" + "Browser Version: " + v;

        return info;
    }


}
