package com.endava.helpers.util.browser;

import com.endava.helpers.configuration.PlatformHelper;
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

@Component
public class Browser {

    private static final Logger log = LoggerFactory.getLogger(Browser.class);

    private final WebDriver webDriver;

    private final WebDriverWait webDriverWait;

    public Browser(Environment environment) {
        log.info("Initialising webdriver: ");
        String driverName = environment.getProperty("driver");

        setDriverPath(environment);
        webDriver = WebDriverFactory.getDriver(driverName);

        log.info("Setting WebDriver Wait for " + webDriver.toString());
        webDriverWait = new WebDriverWait(webDriver, 10, 1000);
    }

    @PreDestroy
    public void closeBrowser() {
        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
        }
    }

    private static void setDriverPath(Environment environment){
        final String WEB_DRIVER_PATH = System.getProperty("user.dir") + environment.getProperty("webdriver.path");

        try {
            final String CHROME_DRIVER_BIN_TYPE = PlatformHelper.getChromeDriverBinType();
            final String FIREFOX_DRIVER_BIN_TYPE = PlatformHelper.getFirefoxDriverBinType();

            //set driver path
            System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH + CHROME_DRIVER_BIN_TYPE);
            System.setProperty("webdriver.gecko.driver", WEB_DRIVER_PATH + FIREFOX_DRIVER_BIN_TYPE);

        } catch (InvalidPlatformException invalidPlatform) {
            invalidPlatform.getMessage();
            invalidPlatform.printStackTrace();
            System.exit(1);
        }
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public void goToUrl(String url) {
        webDriver.navigate().to(url);
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
    }

    public String getPageTitle() {
        return webDriver.getTitle();
    }

    public String getPageUrl() {
        return webDriver.getCurrentUrl();
    }

    public void waitUntilElementIsVisible(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
}
