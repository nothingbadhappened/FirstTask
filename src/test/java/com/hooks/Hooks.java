package com.hooks;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.actions.common.SignInAction;
import com.helpers.util.ScreenShotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.helpers.configuration.SpringConfig;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("driver")
public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(SignInAction.class);

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

        // == Setup Spring ==
        log.debug("Setting Spring context");
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        log.debug("Spring configuration has loaded");

        // == Setup Configs Start ==
//        try {
//            log.info("Loading configurations");
//            ConfigFileReader.loadProperties();
//            log.info("CONFIG: Configuration Loaded");
//
//            log.info("Start browser invoked");
//            driver = WebDriverFactory.getDriver(ConfigFileReader.getBrowser());
//            log.info("Browser instance created for " + ConfigFileReader.getBrowser());
//
//
//        } catch (Exception e) {
//            log.error(String.valueOf(e));
//            e.printStackTrace();
//        }
        // == Setup Configs End ==


        // == Clean up browser session before scenario run Begin ==
        log.info("BROWSER: Clean start:");

        driver.manage().deleteAllCookies();
        log.info("   -> Deleting Cookies");

        driver.manage().window().maximize();
        log.info("   -> Maximizing browser window");

        log.debug(" ----- BEFORE HOOK END -----");
        // == Clean up browser session before scenario run End ==
    }


    @After
    // == Capture screen for the failed scenario ==
    public void embedScreenshot(Scenario scenario) throws InterruptedException {
        log.debug("----- AFTER HOOK START -----");
        if (scenario.isFailed() && driver!=null) {
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
            driver.quit();
            log.info("BROWSER: Closed");
        } else {
            System.out.println("Could not close browser. System will exit.");
            System.exit(-1);
        }

        log.debug("----- AFTER HOOK END -----");
        log.info("Completed scenario:"
                + "\n##################################################################################################"
                + "\n           [" + scenario.getName() + "] --> Status:" + scenario.getStatus()
                + "\n##################################################################################################\n\n\n"
        );
    }


}