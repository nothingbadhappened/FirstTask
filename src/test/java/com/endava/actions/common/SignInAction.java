package com.endava.actions.common;

import com.endava.helpers.util.actionsUtil.ObjectManipulator;
import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.LoginPage;
import com.endava.pageObjects.MyAccountPage;
import com.endava.steps.context.StepContext;
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
    private User user;

    @Autowired
    private ObjectManipulator executor;

    @Autowired
    private Browser browser;

    private LoginPage loginPage;

    private MyAccountPage myAccountPage;

    @PostConstruct
    private void init() {
        this.loginPage = new LoginPage(browser);
        this.myAccountPage = new MyAccountPage(browser);
    }

    public void execute(@NotNull User user) {
        log.info("----> Sign In Action Start: ");

        this.user = user;
//        StepContext.setDriver(browser.getWebDriver());
        StepContext.setCurrentPage(loginPage);

        log.info("   -> Clicking My Account link");
        executor.click(loginPage.getHeaderElementByName("signInLink"));

        log.info("   -> Populating user email field");
        executor.sendKeys(loginPage.getElementByName("userEmailField"), user.getUserEmail());

        log.info("   -> Populating Password field");
        executor.sendKeys(loginPage.getElementByName("userPasswordField"), user.getUserPassword());

        log.info("   -> Clicking Submit button");
        executor.click(loginPage.getElementByName("signInButton"));
        StepContext.setCurrentPage(myAccountPage);

        log.info("----> Sign In action complete");

    }
}
