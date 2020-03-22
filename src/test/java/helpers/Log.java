package helpers;

import org.apache.log4j.Logger;

public class Log {

    private static final Logger logger = Logger.getLogger(Log.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void error(Exception e) {
        logger.error(e);
    }

    public static void error(String description, Exception e) {
        logger.error(description, e);
    }

}