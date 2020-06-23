package com.endava.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class Page {

    private static final Logger log = LoggerFactory.getLogger(Page.class);

    @Autowired
    WebDriver driver;

    // == This is to instantiate any extended Page Object ==
    Page() {
        log.debug("Instantiating Page Object: \"" + this.getClass().getSimpleName() + "\"" + " with webdriver instance: " + driver.toString());
        PageFactory.initElements(driver, this);
    }

    public abstract WebElement getElementByName(String elementName);

    @Override
    public String toString(){
        return "This is Parent Page Object";
    }
}
