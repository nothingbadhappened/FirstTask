package com.endava.pageObjects;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.modules.Header;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;


public class MyAccountPage extends Page {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private final Header header;

    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/h1")
    private WebElement myAccountHeading;

    public MyAccountPage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getDriver());
    }

    public Header getHeader() {
        return header;
    }

    public WebElement getMyAccountHeading() {
        return myAccountHeading;
    }

    @Override
    public String toString() {
        log.debug("My Account Page Object toString() method invoked");

        for (Field f : this.getClass().getFields()) {
            try {
                log.debug(f.getGenericType() + " " + f.getName() + " = " + f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "[MY ACCOUNT PAGE OBJECT]";
    }
}
