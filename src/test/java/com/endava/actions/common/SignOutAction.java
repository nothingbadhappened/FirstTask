package com.endava.actions.common;

import com.endava.helpers.util.Browser;
import com.endava.helpers.util.ObjectManipulatorImpl;
import com.endava.pageObjects.MyAccountPage;
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
        log.info("   -> Click Sign Out button");
        executor.click(myAccountPage.getHeaderElementByName("signOutButton"));
        log.info("   -> SIGN OUT: ACTION COMPLETE");
    }
}