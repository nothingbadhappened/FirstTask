package com.endava.helpers.util.actionsUtil;

import com.endava.pageObjects.modules.ProductListItem;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public interface ObjectManipulator {

    void click(@NotNull WebElement element);

    void click(@NotNull ProductListItem productListItem) throws InterruptedException;

    void sendKeys(@NotNull WebElement field, String keys);

}
