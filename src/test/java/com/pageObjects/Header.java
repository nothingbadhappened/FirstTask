package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component("header")
public class Header extends Page {

    // == Constructor(s) ==
    public Header() {
        super();
    }

    // == elements ==
    @FindBy(how = How.LINK_TEXT, using = "Sign in")
    private static WebElement signInLink;

    @FindBy(how = How.CLASS_NAME, using = "page-heading")
    private static WebElement pageHeading;

    @FindBy(how = How.LINK_TEXT, using = "Contact us")
    private static WebElement contactUs;

    @FindBy(how = How.LINK_TEXT, using = "Sign out")
    private static WebElement signOutButton;

    @FindBy(how = How.LINK_TEXT, using = "Women")
    private static WebElement menuWomen;

    @FindBy(how = How.XPATH, using = "//*a[@title='Dresses']")
    private static WebElement menuDresses;

    @FindBy(how = How.XPATH, using = "//*a[@title='T-shirts']")
    private static WebElement menuTshirts;


    public static WebElement getSignInLink() {
        return signInLink;
    }

    public static WebElement getPageHeading() {
        return pageHeading;
    }

    public static WebElement getContactUs() {
        return contactUs;
    }

    public static WebElement getSignOutButton() {
        return signOutButton;
    }

    public static WebElement getMenuWomen() {
        return menuWomen;
    }

    public static WebElement getMenuDresses() {
        return menuDresses;
    }

    public static WebElement getMenuTshirts() {
        return menuTshirts;
    }
}
