package com.endava.helpers.util;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public class Validator {

    public static Boolean isTextMatching(@NotNull WebElement element, String text) {
        if (element.getText().equals(text)) {
            return true;
        }
        return false;
    }
}
