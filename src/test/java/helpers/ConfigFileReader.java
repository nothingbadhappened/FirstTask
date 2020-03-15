package helpers;

import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {

    Properties properties = new Properties();


    public void loadProperties() {

        try {

            //Set path to properties files
            InputStream props = ConfigFileReader.class.getClassLoader().getResourceAsStream("configurations/configuration.properties");
            InputStream logProps = ConfigFileReader.class.getClassLoader().getResourceAsStream("configurations/log4j.properties");

            //Load properties
            properties.load(props);

            //load log4j properties
            PropertyConfigurator.configure(logProps);

        } catch (IOException e) {
            System.out.println("Properties file not found");
            e.printStackTrace();
        }
    }


    //debug helper
    public void showProps() {
        properties.forEach((key, val) -> System.out.println(key + ": " + val));
    }


}