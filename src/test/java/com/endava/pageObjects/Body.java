package com.endava.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Body extends Page {

    Body() {
        super();
    }

    private static final Logger log = LoggerFactory.getLogger(Body.class);

    @FindBy(how = How.ID, using = "email")
    private static WebElement userEmailField;
    @FindBy(how = How.ID, using = "passwd")
    private static WebElement userPasswordField;

    @FindBy(how = How.ID, using = "SubmitLogin")
    private static WebElement signInButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/div[1]/ol/li")
    private static WebElement loginErrorField;

    @FindBy(how = How.ID, using = "email_create")
    private static WebElement emailCreateField;

    @FindBy(how = How.ID, using = "SubmitCreate")
    private static WebElement submitCreateUserButton;

    @Override
    public WebElement getElementByName(String elementName) {

        WebElement element;

        switch (elementName) {
            case "userEmailField":
                element = userEmailField;
                break;
            case "userPasswordField":
                element = userPasswordField;
                break;
            case "signInButton":
                element = signInButton;
                break;
            case "loginErrorField":
                element = loginErrorField;
                break;
            case "emailCreateField":
                element = emailCreateField;
                break;
            case "submitCreateUserButton":
                element = submitCreateUserButton;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + elementName);
        }

        return element;
    }

    public WebElement getUserEmailField() {
        return userEmailField;
    }

    public WebElement getUserPasswordField() {
        return userPasswordField;
    }

    public WebElement getSignInButton() {
        return signInButton;
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

    @Override
    public String toString() {
        log.debug("Body Page Object toString() method invoked");

        for (Field f : this.getClass().getFields()) {
            try {
                log.debug(f.getGenericType() + " " + f.getName() + " = " + f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        final String message = "Body Page Object created";
//                "\nuserEmailField: " + userEmailField +
//                "\nuserPasswordField: " + userPasswordField +
//                "\nsignInButton: " + signInButton +
//                "\nloginErrorField: " + loginErrorField +
//                "\nemailCreateField: " + emailCreateField +
//                "\nsubmitCreateUserButton: " + submitCreateUserButton;
        return message;
    }

}
