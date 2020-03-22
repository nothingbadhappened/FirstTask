package modules;

import helpers.Log;
import org.openqa.selenium.WebDriver;
import pageObjects.Body;
import pageObjects.Header;
import users.User;


public class SignInAction {

        public static void Execute(WebDriver driver, User user) throws Exception{

            Thread.sleep(3000);

            Header.signIn.click();
            Log.info("   -> Click My Account link" );

            Body.LoginPage.email.sendKeys(user.getUserName());
            Log.info("   -> Username field is populated" );

            Body.LoginPage.password.sendKeys(user.getPassword());
            Log.info("   -> Password field is populated");

            Body.LoginPage.signin_button.click();
            Log.info("   -> Click Submit button");

            Log.info("   -> SIGN IN ACTION COMPLETE");

    }

}
