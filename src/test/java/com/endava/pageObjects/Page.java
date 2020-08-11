package com.endava.pageObjects;

import com.endava.helpers.util.browser.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Page {

    private static final Logger log = LoggerFactory.getLogger(Page.class);

    public Page(Browser browser) {
        WebDriver driver = browser.getDriver();
        log.debug("Instantiating Page Object: \"" + this.getClass().getSimpleName() + "\"" + " with webdriver instance: " + driver.toString());
        PageFactory.initElements(driver, this);
    }

    public abstract WebElement getElementByName(String elementName);
    public abstract WebElement getHeaderElementByName(String elementName);

    @Override
    public String toString() {
        return "[PARENT PAGE OBJECT]";
    }
}
