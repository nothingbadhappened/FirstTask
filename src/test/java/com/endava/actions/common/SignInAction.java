package com.endava.actions.common;


import com.endava.helpers.util.ObjectManipulator;
import com.endava.pageObjects.Page;
import com.endava.pageObjects.PageFactory;
import com.endava.users.User;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignInAction {

    private final Logger log = LoggerFactory.getLogger(SignInAction.class);

    @Autowired
    private ObjectManipulator executor;

    private final Page header = PageFactory.getPageObject("header");
    private final Page body = PageFactory.getPageObject("body");

    public void execute(@NotNull User user) {
        log.info("----> Sign In Action Start: ");

        executor.click(header.getElementByName("signInLink"));
        log.info("   -> Click My Account link");

        executor.sendKeys(body.getElementByName("getUserEmailField"), user.getUserEmail());
        log.info("   -> User Email field is populated");

        executor.sendKeys(body.getElementByName("getUserPasswordField"), user.getUserPassword());
        log.info("   -> Password field is populated");

        executor.click(body.getElementByName("getSignInButton"));
        log.info("   -> Click Submit button");

        log.info("----> Sign In action complete");
    }
}
