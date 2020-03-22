package step_definitions;

import helpers.Log;
import helpers.PropertiesUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.concurrent.TimeUnit;

import static helpers.PropertiesUtil.getDriverImplicitlyWait;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp(Scenario scenario) throws Exception {

        try {
            PropertiesUtil.loadConfiguration();
            driver = PropertiesUtil.loadDriver();

        } catch (Exception e) {
            Log.error(e);
            e.printStackTrace();
        }

        Log.debug(">>> BEFORE HOOK START <<<");
        Log.info("SCENARIO: START - [" + scenario.getName() + "]");
        Log.info("CONFIG: Configuration Loaded");

        Log.info("BROWSER: Clean start");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(getDriverImplicitlyWait(), TimeUnit.SECONDS);
        Log.debug(">>> BEFORE HOOK END <<<");
    }

    public static class teardown {

        //Capture screen for the failed scenario
        @After
        public void embedScreenshot(Scenario scenario) throws InterruptedException {
            Log.debug(">>> AFTER HOOK START <<<");
            if (scenario.isFailed()) {
                try {

                    Log.info("SCENARIO: FAIL - " + "Current Page URL is " + driver.getCurrentUrl());

                    scenario.write("Current Page URL is " + driver.getCurrentUrl());
                    Log.info("SCENARIO: FAIL - Attaching Screenshot");

                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.embed(screenshot, "image/png", "Screen");

                } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                    System.err.println(somePlatformsDontSupportScreenshots.getMessage());
                    Log.error("BROWSER: Cannot capture screen", somePlatformsDontSupportScreenshots);
                }

            }
            Log.info("SCENARIO: COMPLETED - [" + scenario.getName() + "]");
            Thread.sleep(1000);
            driver.quit();
            Log.info("BROWSER: Closed");
            Log.debug(">>> AFTER HOOK END <<<\n\n\n");
        }




//    public static void writeExtentReport() {
//        Reporter.loadXMLConfig();
//    }

    }

}