package com.endava.helpers.util;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public interface ObjectManipulator {
    // This stands for Common Actions
    void click(@NotNull WebElement element);

    void sendKeys(@NotNull WebElement field, String keys);

    void sendKeysWithoutClearing(@NotNull WebElement field, String keys);
}
