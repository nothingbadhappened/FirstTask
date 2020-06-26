package com.endava.hooks;

import com.endava.helpers.configuration.SpringConfig;
import com.endava.helpers.util.Browser;
import com.endava.helpers.util.ScreenShotUtil;
import com.endava.helpers.util.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = SpringConfig.class)
public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    @Autowired
    private Browser browser;

    @Before
    public void setUp(Scenario scenario) {
        log.debug("----- BEFORE HOOK START -----");
        log.info("Starting scenario:"
                + "\n##################################################################################################"
                + "\n                            [" + scenario.getName() + "]"
                + "\n##################################################################################################"
        );

        log.info("BROWSER: Clean start:");
        WebDriver driver = browser.getWebDriver();
        log.info("Browser has started with following params {}", WebDriverFactory.getWebDriverInstanceInfo());

        log.info("   -> Deleting Cookies");
        driver.manage().deleteAllCookies();
        log.info("   -> Maximizing browser window");
        driver.manage().window().maximize();
        log.debug(" ----- BEFORE HOOK END -----");
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        log.debug("----- AFTER HOOK START -----");
        WebDriver driver = browser.getWebDriver();
        if (scenario.isFailed() && driver != null) {
            try {
                String pageUrl = browser.getPageUrl();
                log.info("SCENARIO: FAIL - " + "Current Page URL is " + pageUrl);
                scenario.write("Current Page URL is " + pageUrl);

                log.info("SCENARIO: FAIL - Attaching Screenshot");
                ScreenShotUtil screenShotUtil = new ScreenShotUtil(driver, scenario);
                scenario.embed(
                        screenShotUtil.getByteScreenshotFullPage(),
                        "image/png",
                        screenShotUtil.getScreenshotName()
                );
            } catch (Exception e) {
                System.err.println(e.getMessage());
                log.error("BROWSER: Cannot capture screen", e);
            }
            log.info("BROWSER: Closed");
        }
        browser.closeBrowser();
        log.debug("----- AFTER HOOK END -----");
        log.info("Completed scenario:"
                + "\n##################################################################################################"
                + "\n           [" + scenario.getName() + "] --> Status:" + scenario.getStatus()
                + "\n##################################################################################################\n\n\n"
        );
    }
}