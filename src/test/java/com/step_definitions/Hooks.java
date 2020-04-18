package com.step_definitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.actions.common.SignInAction;
import com.helpers.configuration.ConfigFileReader;
import com.helpers.util.WebDriverFactory;
import com.helpers.util.ScreenShotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import com.helpers.configuration.SpringConfig;
import org.springframework.stereotype.Component;


@Component
public class Hooks {

    public static WebDriver driver;
    public static Scenario scenario;
    private static final Logger log = LoggerFactory.getLogger(SignInAction.class);

    @Before
    public void setUp(Scenario scenario) throws Exception {

        log.debug("   --- BEFORE HOOK START ---   ");


        try {
            log.info("Loading configurations");
            ConfigFileReader.loadProperties();

            log.info("Start browser invoked");
            driver = WebDriverFactory.startBrowser(ConfigFileReader.getBrowser());
            log.info("Browser instance created for " + ConfigFileReader.getBrowser());
            log.info(WebDriverFactory.getWebDriverInstanceInfo(driver));

        } catch (Exception e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }

        // == setup Spring config ==
        log.debug("Setting Spring context");
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        log.info("CONFIG: Configuration Loaded");
        log.info("BROWSER: Clean start:");

        driver.manage().deleteAllCookies();
        log.info("   -> Deleting Cookies");

        driver.manage().window().maximize();
        log.info("   -> Maximizing browser window");

        log.debug("   --- BEFORE HOOK END ---   ");
        log.info("Starting scenario:"
                + "\n##################################################################################################"
                + "\n                            [" + scenario.getName() + "]"
                + "\n##################################################################################################"
        );
    }

    public static class teardown {

        //Capture screen for the failed scenario
        @After
        public void embedScreenshot(Scenario scenario) throws InterruptedException {
            log.debug("   --- AFTER HOOK START ---   ");
            if (scenario.isFailed()) {
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

            }
            log.info("Completed scenario:"
                    + "\n##################################################################################################"
                    + "\n                            [" + scenario.getName() + "]"
                    + "\n##################################################################################################"
            );
            Thread.sleep(1000);
            driver.quit();
            log.info("BROWSER: Closed");
            log.debug("   --- AFTER HOOK END ---   \n\n\n");
        }

//    public static void writeExtentReport() {
//        Reporter.loadXMLConfig();
//    }

    }

}