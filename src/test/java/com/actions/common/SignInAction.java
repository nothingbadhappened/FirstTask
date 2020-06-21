package com.actions.common;

import com.helpers.util.ObjectManipulator;
import com.pageObjects.Page;
import com.pageObjects.PageFactory;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import com.users.User;

@Component
@DependsOn({"driver"})
public class SignInAction {

    // == fields ===
    @Autowired
    ObjectManipulator executor;

    @Autowired
    WebDriver driver;

    private PageFactory pageFactory = new PageFactory();
    private Page header = pageFactory.getPageObject("header", driver);
    private Page body = pageFactory.getPageObject("body", driver);
    private final Logger log = LoggerFactory.getLogger(SignInAction.class);

    // == methods ==
    public void Execute(@NotNull User user) {

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
