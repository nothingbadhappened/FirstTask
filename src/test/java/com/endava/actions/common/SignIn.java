package com.endava.actions.common;

import com.endava.helpers.util.actionsUtil.ObjectManipulator;
import com.endava.pageObjects.LoginPage;
import com.endava.pageObjects.MyAccountPage;
import com.endava.steps.context.ContextKeys;
import com.endava.steps.context.StepContext;
import com.endava.users.User;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignIn {

    private final Logger log = LoggerFactory.getLogger(SignIn.class);

    @Autowired
    private ObjectManipulator executor;

    @Autowired
    private StepContext context;

    public void execute(@NotNull User user) {
        log.info("----> Sign In Action Start: ");

        log.debug("Updating Step Context: Current page is Login Page");
        LoginPage loginPage = (LoginPage) context.getContext(ContextKeys.LOGIN_PAGE);
        context.setContext(ContextKeys.CURRENT_PAGE, loginPage);

        log.info("   -> Clicking My Account link");
        executor.click(loginPage.getHeader().getSignInLink());

        log.info("   -> Populating user email field");
        executor.sendKeys(loginPage.getUserEmailField(), user.getUserEmail());

        log.info("   -> Populating Password field");
        executor.sendKeys(loginPage.getUserPasswordField(), user.getUserPassword());

        log.info("   -> Clicking Submit button");
        executor.click(loginPage.getSignInButton());

        log.debug("Updating Step Context: Current page is My Account Page");
        MyAccountPage myAccountPage = (MyAccountPage) context.getContext(ContextKeys.MY_ACCOUNT_PAGE);
        context.setContext(ContextKeys.CURRENT_PAGE, myAccountPage);

        log.info("----> Sign In action complete");

    }
}
