package com.endava.pageObjects;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.modules.Header;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyAccountPage extends Page {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private Header header;
    private WebElement element;

    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/h1")
    WebElement myAccountHeading;

    public MyAccountPage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getWebDriver());
    }

    public Header getHeader() {
        return header;
    }

    @Override
    public WebElement getElementByName(String elementName) {
        switch (elementName) {
            case "myAccountHeading":
                element = getMyAccountHeading();
                break;
            default:
                throw new InvalidArgumentException("Invalid body element: " + elementName);
        }
        return element;
    }

    public WebElement getMyAccountHeading() {
        return myAccountHeading;
    }

    @Override
    public WebElement getHeaderElementByName(String elementName) {
        return header.getHeaderElementByName(elementName);
    }

    @Override
    public String toString() {
        return "[MY ACCOUNT PAGE OBJECT]";
    }
}
