package modules;
//import helpers.Log;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import pageObjects.Body;
import pageObjects.Header;


public class SignInAction {
    public static void Execute(WebDriver driver, HashMap map) throws Exception{

        Thread.sleep(3000);

        Header.sign_in.click();
//        Log.info("Click action is performed on My Account link" );

        Body.LoginPage.email.sendKeys((CharSequence) map.get("username"));
//        Log.info(" is entered in UserName text box" );

        Body.LoginPage.password.sendKeys((CharSequence) map.get("password"));
//        Log.info(" is entered in Password text box" );

        Body.LoginPage.signin_button.click();
 //       Log.info("Click action is performed on Submit button");

 //       Reporter.log("SignIn Action is successfully performed");
    }
}