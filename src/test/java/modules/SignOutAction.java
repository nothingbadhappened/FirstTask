package modules;
import helpers.Log;
import org.openqa.selenium.WebDriver;
import pageObjects.Header;

public class SignOutAction {
    public static void execute(WebDriver driver) throws Exception{

        Log.info("   -> Click Sign Out button");
        Header.signOut.click();
        Log.info("   -> SIGN OUT: ACTION COMPLETE");

    }
}