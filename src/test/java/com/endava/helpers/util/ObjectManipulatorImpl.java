package com.endava.helpers.util;


import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectManipulatorImpl implements ObjectManipulator {

    private final Browser browser;

    @Autowired
    private ObjectManipulatorImpl(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void click(@NotNull WebElement element) {
        browser.waitUntilElementIsVisible(element);
        element.click();
    }

    @Override
    public void sendKeys(@NotNull WebElement field, String keys) {
        browser.waitUntilElementIsVisible(field);
        field.clear();
        field.sendKeys(keys);
    }

    @Override
    public void sendKeysWithoutClearing(@NotNull WebElement field, String keys) {
        browser.waitUntilElementIsVisible(field);
        field.sendKeys(keys);
    }
}
