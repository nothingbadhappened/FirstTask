package com.endava.helpers.util.actionsUtil;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.modules.ProductListItem;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public void click(@NotNull ProductListItem productListItem) {
        Actions action = new Actions(browser.getWebDriver());

        action.moveToElement(productListItem.getProductItemName())
                .moveToElement(productListItem.getProductItemPrice())
                .moveToElement(productListItem.getProductItemAddToCartBtn())
                .click()
                .build()
                .perform();

        log.debug("Clicked element: {}", productListItem.getProductItemAddToCartBtn());
    }

    @Override
    public void click(@NotNull List<WebElement> dropdown, String optionName) {
        WebElement currentOption;

        log.debug("Looking for the option " + optionName + " in the dropdown " + dropdown.toString());
        for (int i = 0; i < dropdown.size(); i++) {

            currentOption = dropdown.get(i);
            browser.waitUntilElementIsVisible(currentOption);

            if (currentOption.getText().equalsIgnoreCase(optionName)) {
                i = dropdown.size();
                currentOption.click();
                log.info("Option has been found and clicked: {}", currentOption);
            }
        }
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

}
