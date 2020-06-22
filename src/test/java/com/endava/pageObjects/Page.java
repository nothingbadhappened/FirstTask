package com.endava.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    // == This is to instantiate any extended Page Object ==
    Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public abstract WebElement getElementByName(String elementName);
}
