package com.endava.hooks;

import com.endava.actions.common.SignInAction;
import com.endava.helpers.configuration.SpringConfig;
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
    private WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {

        log.info("Starting scenario:"
                + "\n##################################################################################################"
                + "\n                            [" + scenario.getName() + "]"
                + "\n##################################################################################################"
        );
        log.debug("----- BEFORE HOOK START -----");

        // == Clean up browser session before scenario run Begin ==
        log.info("BROWSER: Clean start:");
        log.debug(WebDriverFactory.getWebDriverInstanceInfo(driver));

        driver.manage().deleteAllCookies();
        log.info("   -> Deleting Cookies");

        driver.manage().window().maximize();
        log.info("   -> Maximizing browser window");

        log.debug(" ----- BEFORE HOOK END -----");
        // == Clean up browser session before scenario run End ==
    }


    @After
    // == Capture screen for the failed scenario ==
    public void embedScreenshot(Scenario scenario) {
        log.debug("----- AFTER HOOK START -----");
        if (scenario.isFailed() && driver != null) {
            try {
                log.info("SCENARIO: FAIL - " + "Current Page URL is " + driver.getCurrentUrl());
                scenario.write("Current Page URL is " + driver.getCurrentUrl());

                log.info("SCENARIO: FAIL - Attaching Screenshot");
                //Screenshot helper captures screen shot and saves file
                ScreenShotUtil screenShotUtil = new ScreenShotUtil(driver, scenario);
                //Embed screenshot to Cucumber report
                scenario.embed(
                        screenShotUtil.getByteScreenshotFullPage(),
                        "image/png",
                        screenShotUtil.getScreenshotName()
                );

            } catch (Exception e) {
                System.err.println(e.getMessage());
                log.error("BROWSER: Cannot capture screen", e);
            }

            // == Closing browser session after Scenario run and logging run status ==
            log.info("BROWSER: Closed");
        }

        driver.quit();
        log.debug("----- AFTER HOOK END -----");
        log.info("Completed scenario:"
                + "\n##################################################################################################"
                + "\n           [" + scenario.getName() + "] --> Status:" + scenario.getStatus()
                + "\n##################################################################################################\n\n\n"
        );
    }


}