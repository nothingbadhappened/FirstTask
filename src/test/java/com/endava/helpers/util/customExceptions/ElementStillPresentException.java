package com.endava.helpers.util.customExceptions;

import org.openqa.selenium.WebElement;

public class ElementStillPresentException extends Exception{
    public ElementStillPresentException(WebElement element){
        super("WebElement [" + element.getText() + "] is still present.");
    }
}
