package com.helpers.util.customExceptions;

public class InvalidPlatformException extends Exception {
    public InvalidPlatformException(String platform){

        super();

        System.out.println("\n !!! Invalid platform argument \"" + platform + "\". Please specify either \"win\" for Windows or \"mac\" for Mac OS. !!!");

        System.out.println("See below Examples for more info:\n" +
                "To run tests on Windows platform, use VM argument \"-Dplatform=win\" or run maven goal with \"mvn test -Dplatform=win\"\n" +
                "To run tests in Mac OS platform, use argument \"-Dplatform=mac\" or run maven goal with \"mvn test -Dplatform=mac\"");
    }
}
