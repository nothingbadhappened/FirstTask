package com.endava.pageObjects;

import com.endava.helpers.util.Browser;
import com.endava.pageObjects.modules.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class HomePage extends Page {

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);

    private Header header;

    @FindBy(how = How.ID, using = "SubmitLogin")
    private WebElement signInButton;

    public HomePage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getWebDriver());
    }

    //TODO: move this to Header class
    public WebElement getHeaderElementByName(String elementName) {
        WebElement element;
        switch (elementName) {
            case "signInLink":
                element = header.getSignInLink();
                break;
            case "contactUs":
                element = header.getContactUs();
                break;
            case "signOutButton":
                element = header.getSignOutButton();
                break;
            case "menuWomen":
                element = header.getMenuWomen();
                break;
            case "menuDresses":
                element = header.getMenuDresses();
                break;
            case "menuTshirts":
                element = header.getMenuTshirts();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + elementName);
        }
        return element;
    }

    @Override
    public WebElement getElementByName(String elementName) {
        WebElement element;
        switch (elementName) {

            case "signInButton":
                element = getSignInButton();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + elementName);
        }
        return element;
    }

    public Header getHeader() {
        return header;
    }

    public WebElement getSignInButton() {
        return signInButton;
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
        return "Body Page Object created";
    }
}
