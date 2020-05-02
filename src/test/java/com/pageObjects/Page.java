package com.pageObjects;

import org.openqa.selenium.support.PageFactory;
import static com.hooks.Hooks.driver;

public abstract class Page {

    // == This is to instantiate any extended Page Object ==
    Page(){
        PageFactory.initElements(driver, this);
       }

}
