package com.endava.pageObjects;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.modules.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class HomePage extends Page {

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);

    private final Header header;

    @FindBy(how = How.XPATH, using = "//*[@id=\"home-page-tabs\"]/li[1]/a")
    private WebElement popularTabItem;

    @FindBy(how = How.XPATH, using = "//*[@id=\"home-page-tabs\"]/li[2]/a")
    private WebElement bestsellersTabItem;

    public HomePage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getWebDriver());
    }

    public WebElement getHeaderElementByName(String elementName) {
        return header.getHeaderElementByName(elementName);
    }

    @Override
    public WebElement getElementByName(String elementName) {
        WebElement element;
        switch (elementName) {

            case "popularTabItem":
                element = getPopularTabItem();
                break;
            case "bestsellersTabItem":
                element = getBestsellersTabItem();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + elementName);
        }
        return element;
    }

    public Header getHeader() {
        return header;
    }

    public WebElement getPopularTabItem() {
        return popularTabItem;
    }

    public WebElement getBestsellersTabItem() {
        return bestsellersTabItem;
    }

    @Override
    public String toString() {
        log.debug("Home Page Object toString() method invoked");

        for (Field f : this.getClass().getFields()) {
            try {
                log.debug(f.getGenericType() + " " + f.getName() + " = " + f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "[HOME PAGE OBJECT]";
    }
}
