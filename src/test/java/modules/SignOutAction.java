package modules;
import helpers.Log;
import org.openqa.selenium.WebDriver;
import pageObjects.Header;

public class SignOutAction {
    public static void Execute(WebDriver driver) throws Exception{

        Log.info("SIGN OUT: Click Sign Out button");
        Header.sign_out.click();
        Log.info("SIGN OUT: ACTION SUCCESS");

    }
}