package springTest.actions.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springTest.helpers.util.ObjectManipulatorImpl;
import org.openqa.selenium.WebDriver;
import springTest.pageObjects.Header;

public class SignOutAction {
    private static Logger log = LoggerFactory.getLogger(SignOutAction.class);

    @Autowired
    static ObjectManipulatorImpl executor;

    public static void execute(WebDriver driver) throws Exception{
        log.info("   -> Click Sign Out button");
        executor.click(Header.signOutButton);
        log.info("   -> SIGN OUT: ACTION COMPLETE");

    }
}