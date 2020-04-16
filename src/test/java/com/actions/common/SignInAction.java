package com.actions.common;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.helpers.util.ObjectManipulator;
import com.pageObjects.Body;
import com.pageObjects.Header;
import com.users.User;
import static com.step_definitions.Hooks.driver;

@Component
public class SignInAction {

//      == fields ===
    @Autowired
    private static ObjectManipulator executor;

    @Autowired
    private static Header header;

    @Autowired
    private static Body body;

    private static User user;
    private static final Logger log = LoggerFactory.getLogger(SignInAction.class);

    // == methods ==
    public static void Execute(WebDriver driver, User user) throws Exception {

        log.info("----> Sign In Action Start: ");

        executor.click(header.signInLink);
        log.info("   -> Click My Account link");

        executor.sendKeys(body.userEmailField, user.getUserEmail());
        log.info("   -> User Email field is populated");

        executor.sendKeys(body.passwordField, user.getUserPassword());
        log.info("   -> Password field is populated");

        executor.click(body.signInButton);
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
