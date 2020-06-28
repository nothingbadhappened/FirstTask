package com.endava.helpers.util;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public interface ObjectManipulator {

    void click(@NotNull WebElement element);

    void sendKeys(@NotNull WebElement field, String keys);

    void sendKeysWithoutClearing(@NotNull WebElement field, String keys);

    public Boolean isElementTextMatching(@NotNull WebElement element, String text);
}
