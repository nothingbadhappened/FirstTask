package com.endava.helpers.util.browser;

import com.endava.helpers.configuration.PlatformConfig;
import com.endava.helpers.util.customExceptions.InvalidPlatformException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Component
public class Browser {

    private static final Logger log = LoggerFactory.getLogger(Browser.class);
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;

    public Browser(Environment environment) {
        log.info("Initialising webdriver: ");
        String driverName = environment.getProperty("driver");

        setDriverPath(environment);
        driver = WebDriverFactory.getDriver(driverName);

        log.info("Setting WebDriver Wait for " + driver.toString());
        webDriverWait = new WebDriverWait(driver, 10, 1000);
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
    }

    @PreDestroy
    public void closeBrowser() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void goToUrl(String url) {
        driver.navigate().to(url);
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void waitUntilElementIsVisible(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    private static void setDriverPath(Environment environment){
        final String WEB_DRIVER_PATH = System.getProperty("user.dir") + environment.getProperty("webdriver.path");

        try {
            final String CHROME_DRIVER_BIN_TYPE = PlatformConfig.getChromeDriverBinType();
            final String FIREFOX_DRIVER_BIN_TYPE = PlatformConfig.getFirefoxDriverBinType();

            //set driver path
            System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH + CHROME_DRIVER_BIN_TYPE);
            System.setProperty("webdriver.gecko.driver", WEB_DRIVER_PATH + FIREFOX_DRIVER_BIN_TYPE);

        } catch (InvalidPlatformException invalidPlatform) {
            log.debug("Could not set driver path: \n{}", invalidPlatform.getMessage());
            invalidPlatform.printStackTrace();
            System.exit(1);
        }
    }
}
