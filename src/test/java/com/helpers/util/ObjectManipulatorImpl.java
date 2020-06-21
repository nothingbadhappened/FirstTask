package com.helpers.util;


import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectManipulatorImpl implements ObjectManipulator {

    private WebDriverWait wait;

    @Autowired
    private ObjectManipulatorImpl(WebDriverWait wait) {
        this.wait = wait;
    }

    @Override
    public void click(@NotNull WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    @Override
    public void sendKeys(@NotNull WebElement field, String keys) {
        wait.until(ExpectedConditions.visibilityOf(field));
        field.clear();
        field.sendKeys(keys);
    }

    @Override
    public void sendKeysWithoutClearing(@NotNull WebElement field, String keys) {
        wait.until(ExpectedConditions.visibilityOf(field));
        field.sendKeys(keys);
    }

}
