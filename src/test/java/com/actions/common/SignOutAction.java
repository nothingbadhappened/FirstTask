package com.actions.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.helpers.util.ObjectManipulatorImpl;
import org.openqa.selenium.WebDriver;
import com.pageObjects.Header;
import org.springframework.stereotype.Component;

@Component
public class SignOutAction {
    private static Logger log = LoggerFactory.getLogger(SignOutAction.class);

    @Autowired
    private ObjectManipulatorImpl executor;

    @Autowired
    private Header header;

    public void execute(WebDriver driver) throws Exception{
        log.info("   -> Click Sign Out button");
        executor.click(header.signOutButton);
        log.info("   -> SIGN OUT: ACTION COMPLETE");

    }
}