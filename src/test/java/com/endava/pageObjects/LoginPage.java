package com.endava.pageObjects;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.modules.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class LoginPage extends Page {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    private final Header header;

    @FindBy(how = How.ID, using = "email")
    private WebElement userEmailField;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement userPasswordField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/div[1]/ol/li")
    private WebElement loginErrorField;

    @FindBy(how = How.ID, using = "email_create")
    private WebElement emailCreateField;

    @FindBy(how = How.ID, using = "SubmitCreate")
    private WebElement submitCreateUserButton;

    @FindBy(how = How.CLASS_NAME, using = "page-heading")
    private WebElement pageHeading;

    @FindBy(how = How.ID, using = "SubmitLogin")
    private WebElement signInButton;

    public LoginPage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getDriver());
    }

    public WebElement getUserEmailField() {
        return userEmailField;
    }

    public WebElement getUserPasswordField() {
        return userPasswordField;
    }

    public WebElement getLoginErrorField() {
        return loginErrorField;
    }

    public WebElement getEmailCreateField() {
        return emailCreateField;
    }

    public WebElement getSubmitCreateUserButton() {
        return submitCreateUserButton;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public WebElement getPageHeading() {
        return pageHeading;
    }

    public Header getHeader() {
        return header;
    }

    @Override
    public String toString() {
        log.debug("Login Page Object toString() method invoked");

        for (Field f : this.getClass().getFields()) {
            try {
                log.debug(f.getGenericType() + " " + f.getName() + " = " + f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "[LOGIN PAGE OBJECT]";
    }
}
