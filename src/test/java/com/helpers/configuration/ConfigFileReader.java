package com.helpers.configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.util.StatusPrinter;
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

                // == LOGBACK CONFIG START ==
//                System.out.println("System.getProperty(\"user.dir\") returned: " + System.getProperty("user.dir") + "\n");
//                String rawConfigPath = System.getProperty("user.dir") + properties.getProperty("loggerConfigPath");
//                // == set the path with forward slashes due to System.getProperty returns the path with backslashes and this is not working for logback ==
//                final String LOGBACK_CONFIG_PATH = rawConfigPath.replace("\\", "/");
//                System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, LOGBACK_CONFIG_PATH);
                // == LOGBACK CONFIG END ==

                final String CHROME_PATH = System.getProperty("user.dir") + properties.getProperty("webdriver.chrome.driver");
                final String FIREFOX_PATH = System.getProperty("user.dir") + properties.getProperty("webdriver.firefox.driver");

                //set driver path
                System.setProperty("webdriver.chrome.driver", CHROME_PATH);
                System.setProperty("webdriver.gecko.driver", FIREFOX_PATH);

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