package helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


public class PropertiesUtil {

    private static WebDriver driver = null;
    private static ConfigFileReader configFileReader = new ConfigFileReader();

    static Logger log = Logger.getLogger("PropertiesUtil");

    //Load  properties file
    public static void loadConfiguration() {
        configFileReader.loadProperties();
    }

    public static String getDriver() {
        return configFileReader.properties.getProperty("driver");
    }

    //Get desired browser driver from the config file
    public static WebDriver loadDriver() throws Exception {

        try {

            switch (getDriver()) {
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


    // Get other fields from the properties file
    public static String getUrl() {
        return configFileReader.properties.getProperty("url");
    }

    public static int getDriverImplicitlyWait() {
        return Integer.parseInt(configFileReader.properties.getProperty("driver.implicitlyWait"));
    }

}
