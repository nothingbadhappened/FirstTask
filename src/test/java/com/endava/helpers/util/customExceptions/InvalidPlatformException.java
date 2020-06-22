package com.endava.helpers.util.customExceptions;

public class InvalidPlatformException extends Exception {
    public InvalidPlatformException(String platform) {

        super("\n!!! Invalid platform argument \"" + platform + "\". Please specify either \"win\" for Windows or \"mac\" for Mac OS. !!!\n\nSee below Examples for more info:\n" +
                "To run the tests on Windows platform, please use VM argument \"-Dplatform=win\" or run maven goal with \"mvn test -Dplatform=win\"\n" +
                "To run the tests on Mac OS platform, please use VM argument \"-Dplatform=mac\" or run maven goal with \"mvn test -Dplatform=mac\"\n");
    }
}
