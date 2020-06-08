package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component("body")
public class Body extends Page {

    // == Constructor(s) ==
    public Body() {
        super();
    }

    // == Elements ==
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

    // == Getters ==
    public WebElement getUserEmailField(){
        return userEmailField;
    }

    public WebElement getUserPasswordField(){
        return userPasswordField;
    }

    public WebElement getSignInButton(){
        return signInButton;
    }

    public WebElement getLoginErrorField(){
        return loginErrorField;
    }

    public WebElement getEmailCreateField(){
        return emailCreateField;
    }

    public WebElement getSubmitCreateUserButton(){
        return submitCreateUserButton;
    }

}
