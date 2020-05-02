package com.actions.common;

import com.helpers.util.ObjectManipulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pageObjects.Body;
import com.pageObjects.Header;
import com.users.User;

@Component
public class SignInAction {

    // == fields ===
    @Autowired
    ObjectManipulator executor;

    @Autowired
    private Header header;

    @Autowired
    private Body body;

    private static final Logger log = LoggerFactory.getLogger(SignInAction.class);

    // == methods ==
    public void Execute(User user) throws Exception {

        log.info("----> Sign In Action Start: ");

        executor.click(header.getSignInLink());
        log.info("   -> Click My Account link");

        executor.sendKeys(body.getUserEmailField(), user.getUserEmail());
        log.info("   -> User Email field is populated");

        executor.sendKeys(body.getUserPasswordField(), user.getUserPassword());
        log.info("   -> Password field is populated");

        executor.click(body.getSignInButton());
        log.info("   -> Click Submit button");

        log.info("----> Sign In action complete");

    }

}
