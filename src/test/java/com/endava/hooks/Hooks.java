package com.endava.hooks;

import com.endava.helpers.configuration.SpringConfig;
import com.endava.helpers.util.actionsUtil.ScreenShotUtil;
import com.endava.helpers.util.browser.Browser;
import com.endava.helpers.util.browser.WebDriverFactory;
import com.endava.steps.context.StepContext;
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

    @Autowired
    private StepContext context;

    @Before
    public void setUp(Scenario scenario) {
        log.debug("\n\n\n----- BEFORE HOOK START -----");
        log.info("Starting scenario:"
                + "\n##################################################################################################"
                + "\n                  Starting Scenario: [" + scenario.getName() + "]"
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

        log.debug("Resetting Step Context");
        context.reset();

        log.debug("----- AFTER HOOK END -----");
        log.info("Completed scenario:"
                + "\n##################################################################################################"
                + "\n     Completed scenario: [" + scenario.getName() + "] --> Status:" + scenario.getStatus()
                + "\n##################################################################################################\n\n\n"
        );
    }

    @After
    public void teardown() {
        log.debug("Resetting Step Context");
        context.reset();
    }

}