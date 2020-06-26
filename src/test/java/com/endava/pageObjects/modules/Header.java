package com.endava.pageObjects.modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class Header {

    private static final Logger log = LoggerFactory.getLogger(Header.class);

    @FindBy(how = How.LINK_TEXT, using = "Sign in")
    private WebElement signInLink;

    @FindBy(how = How.CLASS_NAME, using = "page-heading")
    private WebElement pageHeading;

    @FindBy(how = How.LINK_TEXT, using = "Contact us")
    private WebElement contactUs;

    @FindBy(how = How.LINK_TEXT, using = "Sign out")
    private WebElement signOutButton;

    @FindBy(how = How.LINK_TEXT, using = "Women")
    private WebElement menuWomen;

    @FindBy(how = How.XPATH, using = "//*a[@title='Dresses']")
    private WebElement menuDresses;

    @FindBy(how = How.XPATH, using = "//*a[@title='T-shirts']")
    private WebElement menuTshirts;

    public Header(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getSignInLink() {
        return signInLink;
    }

    public WebElement getPageHeading() {
        return pageHeading;
    }

    public WebElement getContactUs() {
        return contactUs;
    }

    public WebElement getSignOutButton() {
        return signOutButton;
    }

    public WebElement getMenuWomen() {
        return menuWomen;
    }

    public WebElement getMenuDresses() {
        return menuDresses;
    }

    public WebElement getMenuTshirts() {
        return menuTshirts;
    }

    @Override
    public String toString() {
        log.debug("Header Page Object toString() method invoked");

        for (Field f : this.getClass().getFields()) {
            try {
                log.debug(f.getGenericType() + " " + f.getName() + " = " + f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "Body Page Object created";
    }
}
