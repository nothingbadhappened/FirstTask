package com.endava.actions.common;

import com.endava.helpers.util.actionsUtil.ObjectManipulatorImpl;
import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.MyAccountPage;
import com.endava.steps.context.ContextKeys;
import com.endava.steps.context.StepContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;

@Component
public class SignOut {

    private final static Logger log = LoggerFactory.getLogger(SignOut.class);

    @Autowired
    private ObjectManipulatorImpl executor;

    @Autowired
    private Browser browser;

    @Autowired
    private StepContext context;

    public void execute() {
        MyAccountPage myAccountPage = new MyAccountPage(browser);

        log.debug("Updating Step Context: Current page is My Account Page");
        context.setContext(ContextKeys.CURRENT_PAGE, myAccountPage);
        context.setContext(ContextKeys.MY_ACCOUNT_PAGE, myAccountPage);

        log.info("   -> Click Sign Out button");
        executor.click(myAccountPage.getHeaderElementByName("signOutButton"));

        context.setContext(ContextKeys.CURRENT_PAGE, context.getContext(ContextKeys.LOGIN_PAGE));
        log.info("   -> SIGN OUT: ACTION COMPLETE");
    }
}