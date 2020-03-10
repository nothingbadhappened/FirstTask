package modules;
//import helpers.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import pageObjects.Header;

public class SignOutAction {
    public static void Execute(WebDriver driver) throws Exception{

        Header.sign_out.click();
//        Log.info("Sign out is performed");

    }
}