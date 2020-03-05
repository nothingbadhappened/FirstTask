package stepDefinitions;

import helpers.ConfigFileReader;
import helpers.PropertiesUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static helpers.PropertiesUtil.getDriverImplicitlyWait;

public class Hooks{

    public static WebDriver driver;

    @Before
    public void setUp() throws Exception {
        try {
            PropertiesUtil.loadConfiguration();
            driver = PropertiesUtil.loadDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */

    public void openBrowser() throws Exception {

        System.out.println("Called openBrowser");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(getDriverImplicitlyWait(), TimeUnit.SECONDS);
    }

    @BeforeStep
    public void logStep(){

    }

    @After

//    public static void writeExtentReport() {
//        Reporter.loadXMLConfig();
//    }

    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {

        if(scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png", "Screen");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }

        }
        driver.quit();

    }

}