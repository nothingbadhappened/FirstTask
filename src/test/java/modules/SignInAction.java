package modules;

import helpers.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import pageObjects.Body;
import pageObjects.Header;
import users.User;


public class SignInAction {

        public static void Execute(WebDriver driver, User user) throws Exception{

            Thread.sleep(3000);

            Header.sign_in.click();
            Log.info("SIGN IN: Click My Account link" );

            Body.LoginPage.email.sendKeys(user.getUserName());
            Log.info("SIGN IN: Username field is populated" );

            Body.LoginPage.password.sendKeys(user.getPassword());
            Log.info("SIGN IN: Password field is populated");

            Body.LoginPage.signin_button.click();
            Log.info("SIGN IN: Click Submit button");

            Reporter.log("SIGN IN: ACTION SUCCESS");

    }

}
