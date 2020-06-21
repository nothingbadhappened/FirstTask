package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    // == This is to instantiate any extended Page Object ==
    Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    Page(){

    }

    public WebElement getElementByName(String elementName) {
        return null;
    }

}
