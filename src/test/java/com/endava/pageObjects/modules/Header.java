package com.endava.pageObjects.modules;

import org.openqa.selenium.NoSuchElementException;
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

    @FindBy(how = How.ID, using = "search_query_top")
    private WebElement headerSearchBox;

    @FindBy(how = How.ID_OR_NAME, using = "submit_search")
    private WebElement headerSearchButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")
    private WebElement userFullName;

    private WebElement headerCartItem;

    public Header(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getHeaderCartItem() {
        if (headerCartItem != null){
            return headerCartItem;
        }
        else throw new NoSuchElementException("Item is not present in the cart.");
    }

    public WebElement getSignInLink() {
        return signInLink;
    }

    public WebElement getContactUs() {
        return contactUs;
    }

    public WebElement getSignOutButton() {
        return signOutButton;
    }

    public WebElement getHeaderSearchBox() {
        return headerSearchBox;
    }

    public WebElement getHeaderSearchButton() {
        return headerSearchButton;
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

    public WebElement getUserFullName() {
        return userFullName;
    }

    public WebElement getHeaderElementByName(String elementName) {
        WebElement element;
        switch (elementName) {
            case "signInLink":
                element = getSignInLink();
                break;
            case "contactUs":
                element = getContactUs();
                break;
            case "headerSearchBox":
                element = getHeaderSearchBox();
                break;
            case "getHeaderSearchButton":
                element = getHeaderSearchButton();
                break;
            case "signOutButton":
                element = getSignOutButton();
                break;
            case "menuWomen":
                element = getMenuWomen();
                break;
            case "menuDresses":
                element = getMenuDresses();
                break;
            case "menuTshirts":
                element = getMenuTshirts();
                break;
            case "userFullName":
                element = getUserFullName();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + elementName);
        }
        return element;
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
        return "[HEADER OBJECT]";
    }
}
