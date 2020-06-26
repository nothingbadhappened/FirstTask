package com.endava.actions.common;


import com.endava.helpers.util.Browser;
import com.endava.helpers.util.ObjectManipulator;
import com.endava.pageObjects.HomePage;
import com.endava.users.User;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SignInAction {

    private final Logger log = LoggerFactory.getLogger(SignInAction.class);

    @Autowired
    private ObjectManipulator executor;

    @Autowired
    private Browser browser;

    private HomePage homePage;

    @PostConstruct
    private void init() {
        homePage = new HomePage(browser);
    }

    public void execute(@NotNull User user) {
        log.info("----> Sign In Action Start: ");

        executor.click(homePage.getHeaderElementByName("signInLink"));
        log.info("   -> Click My Account link");

        executor.sendKeys(homePage.getElementByName("userEmailField"), user.getUserEmail());
        log.info("   -> User Email field is populated");

        executor.sendKeys(homePage.getElementByName("userPasswordField"), user.getUserPassword());
        log.info("   -> Password field is populated");

        executor.click(homePage.getElementByName("signInButton"));
        log.info("   -> Click Submit button");

        log.info("----> Sign In action complete");
    }
}
