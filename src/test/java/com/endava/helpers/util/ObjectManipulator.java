package com.endava.helpers.util;

import com.endava.pageObjects.modules.ProductListItem;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface ObjectManipulator {

    void click(@NotNull WebElement element);

    void click(@NotNull List<WebElement> dropdown, String option);

    void click(@NotNull ProductListItem productListItem) throws InterruptedException;

    void sendKeys(@NotNull WebElement field, String keys);

    void sendKeysWithoutClearing(@NotNull WebElement field, String keys);

}
