package modules;

import helpers.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import pageObjects.Body;
import pageObjects.Header;
import users.User;


public class SignInAction_New {

        public static void Execute(WebDriver driver, User user) throws Exception{

            Thread.sleep(3000);

            Header.sign_in.click();
            Log.info("Click action is performed on My Account link" );

            Body.LoginPage.email.sendKeys(user.getUserName());
            Log.info(" is entered in UserName text box" );

            Body.LoginPage.password.sendKeys(user.getPassword());
            Log.info(" is entered in Password text box" );

            Body.LoginPage.signin_button.click();
            Log.info("Click action is performed on Submit button");

            Reporter.log("SignIn Action is successfully performed");

    }

}
