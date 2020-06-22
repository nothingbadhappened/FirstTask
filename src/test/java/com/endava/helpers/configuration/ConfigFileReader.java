package com.endava.helpers.configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.util.StatusPrinter;
import com.endava.helpers.util.customExceptions.InvalidPlatformException;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {

    private static LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    private static Properties properties = new Properties();

    public static void loadProperties() {

        try {

            //Set path to properties files
            InputStream props = ConfigFileReader.class.getClassLoader().getResourceAsStream("configurations/configuration.properties");

            //Load properties
            if (props != null) {
                properties.load(props);

                final String WEB_DRIVER_PATH = System.getProperty("user.dir") + properties.getProperty("webdriver.path");

                try {
                    final String CHROME_DRIVER_BIN = PlatformHelper.getChromeDriverBin();
                    final String FIREFOX_DRIVER_BIN = PlatformHelper.getFirefoxDriverBin();

                    //set driver path
                    System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH + CHROME_DRIVER_BIN);
                    System.setProperty("webdriver.gecko.driver", WEB_DRIVER_PATH + FIREFOX_DRIVER_BIN);

                } catch (InvalidPlatformException invalidPlatform) {
                    invalidPlatform.getMessage();
                    invalidPlatform.printStackTrace();
                    System.exit(1);
                }

            } else {
                System.out.println("Cannot load properties file!");
            }

        } catch (IOException e) {
            System.out.println("Properties file not found");
            e.printStackTrace();
        }
    }

    // Get other fields from the properties file
    public static String getUrl() {
        return properties.getProperty("url");
    }

    public int getDriverImplicitlyWait() {
        return Integer.parseInt(properties.getProperty("driver.implicitlyWait"));
    }

    public static String getBrowser() {
        return properties.getProperty("driver");
    }

    //debug helper
    public static void showProps() {
        properties.forEach((key, val) -> System.out.println(key + ": " + val));
    }

    @Test
    public void test() {
        System.out.println("User dir path: " + System.getProperty("user.dir"));
        System.out.println("\nLoading properties file..");
        loadProperties();
        System.out.println("Properties file has loaded successfully:");
        showProps();
        System.out.println("\nExtracting Logger XML file location from system property: " + System.getProperty(ContextInitializer.CONFIG_FILE_PROPERTY));
        System.out.println("If this location is pasted into run command line (i.e. WIN+R) and the XML file location directory opens, then the path is correct and logger should pick it up.");
        System.out.println("\nLogger Status:");
        StatusPrinter.print(context);
    }
}