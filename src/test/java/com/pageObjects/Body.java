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
    public static WebElement userEmailField;

    @FindBy(how = How.ID, using = "passwd")
    public static WebElement passwordField;

    @FindBy(how = How.ID, using = "SubmitLogin")
    public static WebElement signInButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/div[1]/ol/li")
    public static WebElement login_error;

    @FindBy(how = How.ID, using = "email_create")
    public static WebElement email_create;

    @FindBy(how = How.ID, using = "SubmitCreate")
    public static WebElement submit_create;

}
