package com.endava.helpers.util;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectManipulatorImpl implements ObjectManipulator {

    private final Browser browser;

    private static final Logger log = LoggerFactory.getLogger(ObjectManipulatorImpl.class);

    @Autowired
    private ObjectManipulatorImpl(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void click(@NotNull WebElement element) {
        browser.waitUntilElementIsVisible(element);
        element.click();
        log.debug("Clicked element: {}", element);
    }

    @Override
    public void sendKeys(@NotNull WebElement field, String keys) {
        browser.waitUntilElementIsVisible(field);
        field.clear();
        field.sendKeys(keys);
        log.debug("Sent keys to element: {}", field);
    }

    @Override
    public void sendKeysWithoutClearing(@NotNull WebElement field, String keys) {
        browser.waitUntilElementIsVisible(field);
        field.sendKeys(keys);
        log.debug("Sent keys to element: {}", field);
    }

    @Override
    public Boolean isElementTextMatching(@NotNull WebElement element, String text) {
        if (element.getText().equals(text)) {
            return true;
        }
        return false;
    }

}
