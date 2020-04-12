package com.actions.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.openqa.selenium.WebDriver;
import com.helpers.util.ObjectManipulator;
import com.pageObjects.Body;
import com.pageObjects.Header;
import com.users.User;

@Component
public class SignInAction {

//      == fields ===
//      this works
//      private static ObjectManipulatorImpl executor = new ObjectManipulatorImpl();

//      this does not work, find the reason why...
        @Autowired
        private static ObjectManipulator executor;

        private static final Logger log = LoggerFactory.getLogger(SignInAction.class);

        // == methods ==
        public static void Execute(WebDriver driver, User user) throws Exception{

            log.info("----> Sign In Action Start: ");

            executor.click(Header.signInLink);
            log.info("   -> Click My Account link");

            executor.sendKeys(Body.AccountPage.userEmailField, user.getUserEmail());
            log.info("   -> User Email field is populated");

            executor.sendKeys(Body.AccountPage.passwordField, user.getPassword());
            log.info("   -> Password field is populated");

            executor.click(Body.AccountPage.signInButton);
            log.info("   -> Click Submit button");

            log.info("Sign In action complete");

// ~~~~~~~~~~~~~~~~~~~~~~~           OLD CODE             ~~~~~~~~~~~~~~~~~~~~~~~
//
//            Header.signInLink.click();
//            Log.info("   -> Click My Account link" );
//
//            Body.AccountPage.userEmailField.sendKeys(user.getUserEmail());
//            Log.info("   -> Username field is populated" );
//
//            Body.AccountPage.passwordField.sendKeys(user.getPassword());
//            Log.info("   -> Password field is populated");
//
//            Body.AccountPage.signInButton.click();
//            Log.info("   -> Click Submit button");
//
//            Log.info("----> Sign In Action complete");
//
// ~~~~~~~~~~~~~~~~~~~~~~~           OLD CODE             ~~~~~~~~~~~~~~~~~~~~~~~

    }

}
