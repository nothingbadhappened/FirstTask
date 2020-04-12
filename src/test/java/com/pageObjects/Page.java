package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    private WebElement webElement;
    private WebDriver driver;

    // == constructors ==

    public Page(){}

    public Page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
       }

}
