package com.helpers.util;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pageObjects.*;

import static com.step_definitions.Hooks.driver;

@Component
public class ObjectManipulatorImpl implements ObjectManipulator {

    @Autowired
    Body body;

    @Autowired
    Header header;

    private final WebDriverWait wait = new WebDriverWait(driver,  5, 100);

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

}
