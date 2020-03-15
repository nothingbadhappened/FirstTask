package helpers;

import org.apache.log4j.Logger;

public class Log {

    private static Logger logger = Logger.getLogger(Log.class);

    public static void startTestCase(String testCaseName) {
        logger.info("TEST: Started " + testCaseName);
    }

    public static void endTestCase(String testCaseName) {
        logger.info("TEST: Completed " +  testCaseName);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(Exception e) {
        logger.error(e);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

}