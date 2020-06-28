package com.endava.pageObjects;

import com.endava.helpers.util.Browser;
import com.endava.pageObjects.modules.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountPage extends Page {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private Header header;

    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")
    private WebElement navBarUserFullName;

    public MyAccountPage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getWebDriver());
    }

    public WebElement getNavBarUserFullName() {
        return navBarUserFullName;
    }

    @Override
    public WebElement getElementByName(String elementName) {

        WebElement element;
        switch (elementName) {
            case "getNavBarUserFullName":
                element = getNavBarUserFullName();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + elementName);
        }
        return element;
    }

    //TODO: move this to Header class
    public WebElement getHeaderElementByName(String elementName) {
        WebElement element;
        switch (elementName) {
            case "signInLink":
                element = header.getSignInLink();
                break;
            case "signOutButton":
                element = header.getSignOutButton();
                break;
            case "contactUs":
                element = header.getContactUs();
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
}
