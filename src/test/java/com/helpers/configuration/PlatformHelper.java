package com.helpers.configuration;

import com.helpers.util.customExceptions.InvalidPlatformException;

public class PlatformHelper {

    private static String platform;
    private static String chromeDriverBin;
    private static String firefoxDriverBin;

    public static void setPlatform() throws InvalidPlatformException {

        platform = System.getProperty("platform");

        if (platform.equals("win")) {
            chromeDriverBin = "chromedriver.exe";
            firefoxDriverBin = "geckodriver.exe";
        } else if (platform.equals("mac")) {
            chromeDriverBin = "chromedriver";
            firefoxDriverBin = "geckodriver";
        } else {
            throw new InvalidPlatformException(platform);
        }

    }

    public static String getChromeDriverBin() throws InvalidPlatformException {
        setPlatform();
        return chromeDriverBin;
    }

    public static String getFirefoxDriverBin() throws InvalidPlatformException {
        setPlatform();
        return firefoxDriverBin;
    }

}
