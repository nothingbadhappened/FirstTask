package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component("header")
public class Header extends Page {

    // == Constructor(s) ==
    public Header(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getElementByName(String elementName) {

        WebElement element;

        switch (elementName) {
            case "signInLink":
                element = signInLink;
                break;
            case "pageHeading":
                element = pageHeading;
                break;
            case "contactUs":
                element = contactUs;
                break;
            case "signOutButton":
                element = signOutButton;
                break;
            case "menuWomen":
                element = menuWomen;
                break;
            case "menuDresses":
                element = menuDresses;
            case "menuTshirts":
                element = menuTshirts;
            default:
                throw new IllegalStateException("Unexpected value: " + elementName);
        }

        return element;
    }

    // == elements ==
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
}
