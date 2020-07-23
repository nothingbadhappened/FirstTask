package com.endava.actions.common;

import com.endava.helpers.util.actionsUtil.ObjectManipulatorImpl;
import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.MyAccountPage;
import com.endava.steps.context.StepContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignOutAction {

    private final static Logger log = LoggerFactory.getLogger(SignOutAction.class);

    @Autowired
    private ObjectManipulatorImpl executor;

    @Autowired
    private Browser browser;

    public void execute() {
        MyAccountPage myAccountPage = new MyAccountPage(browser);

        StepContext.setCurrentPage(myAccountPage);
        log.debug("My Account page has been passed to Step Context.");

        log.info("   -> Click Sign Out button");
        executor.click(myAccountPage.getHeaderElementByName("signOutButton"));

        log.info("   -> SIGN OUT: ACTION COMPLETE");
    }
}