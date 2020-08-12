package com.endava.helpers.configuration;

import com.endava.helpers.util.customExceptions.InvalidPlatformException;

public class PlatformConfig {

    private static String chromeDriverBinType;
    private static String firefoxDriverBinType;

    private static void setPlatform() throws InvalidPlatformException {

        String platform;
        if (System.getProperty("platform") == null) {
            System.out.println("Platform has not been specified, trying to auto-detect your system:");

            if (System.getProperty("os.name").startsWith("Windows")) {
                System.out.println("Your system has been identified as Windows");
                System.setProperty("platform", "win");
                platform = "win";
            } else if (System.getProperty("os.name").startsWith("Mac")) {
                System.out.println("Your system has been identified as MAC OS");
                System.setProperty("platform", "mac");
                platform = "mac";
            } else {
                System.out.println("Your system is not yet supported.");
                throw new InvalidPlatformException(System.getProperty("os.name"));
            }

        } else {
            platform = System.getProperty("platform");
        }

        if (platform.equals("win")) {
            chromeDriverBinType = "chromedriver.exe";
            firefoxDriverBinType = "geckodriver.exe";
        } else if (platform.equals("mac")) {
            chromeDriverBinType = "chromedriver";
            firefoxDriverBinType = "geckodriver";
        } else {
            throw new InvalidPlatformException(platform);
        }

    }

    public static String getChromeDriverBinType() throws InvalidPlatformException {
        setPlatform();
        return chromeDriverBinType;
    }

    public static String getFirefoxDriverBinType() throws InvalidPlatformException {
        setPlatform();
        return firefoxDriverBinType;
    }

}