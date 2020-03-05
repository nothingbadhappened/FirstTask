package helpers;
import org.apache.log4j.Logger;

public class Log {

    private static Logger logger = Logger.getLogger(Log.class.getName());

    public static void startTestCase(String sTestCaseName){
        logger.info("Started Test case");
    }


    public static void endTestCase(String sTestCaseName){
        logger.info("Ended Test Case");
    }

    public static void info(String message)
    {
        logger.info(message);
    }

}