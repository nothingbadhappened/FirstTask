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


    private static Properties properties = new Properties();
    private static String path = System.getProperty("user.dir");
    private static LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();


    public static void loadProperties() {

        try {

            //Set path to properties files
            InputStream props = ConfigFileReader.class.getClassLoader().getResourceAsStream("configurations/configuration.properties");

            //Load properties
            if (props != null) {
                properties.load(props);
            } else {
                System.out.println("Cannot load properties file:\n" + new NullPointerException().toString());
            }

            //set driver path
            System.setProperty("webdriver.chrome.driver", path + properties.getProperty("webdriver.chrome.driver"));
            System.setProperty("webdriver.gecko.driver", path + properties.getProperty("webdriver.gecko.driver"));

            //load logback Config
            System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, path + properties.getProperty("loggerConfigPath"));

        } catch (IOException e) {
            System.out.println("Properties file not found");
            e.printStackTrace();
        }
    }

    // Get other fields from the properties file
    public static String getUrl() {
        return ConfigFileReader.properties.getProperty("url");
    }

    public static int getDriverImplicitlyWait() {
        return Integer.parseInt(ConfigFileReader.properties.getProperty("driver.implicitlyWait"));
    }

    public static String getBrowser() {
        return ConfigFileReader.properties.getProperty("driver");
    }

    //debug helper
    public static void showProps() {
        properties.forEach((key, val) -> System.out.println(key + ": " + val));
    }

    @Test
    public void test(){
        System.out.println("User dir path: " + path);
        loadProperties();
        showProps();
        System.out.println("Logger XML file location: " + System.getProperty(ContextInitializer.CONFIG_FILE_PROPERTY));
        StatusPrinter.print(context);
    }

}