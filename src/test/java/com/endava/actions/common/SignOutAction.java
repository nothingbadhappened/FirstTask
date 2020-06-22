package com.endava.actions.common;

import com.endava.helpers.util.ObjectManipulatorImpl;
import com.endava.pageObjects.Header;
import org.openqa.selenium.WebDriver;
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
    private Header header;

    public void execute(WebDriver driver) throws Exception {
        log.info("   -> Click Sign Out button");
        executor.click(header.getSignOutButton());
        log.info("   -> SIGN OUT: ACTION COMPLETE");

    }
}