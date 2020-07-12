package com.endava.steps;

import com.endava.pageObjects.Page;
import com.endava.pageObjects.modules.Header;
import com.endava.pageObjects.modules.ProductList;
import com.endava.pageObjects.modules.ProductListItem;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StepContext {

    private static final Logger log = LoggerFactory.getLogger(StepContext.class);
    private static WebDriver driver;
    private static Page currentPage;
    private static WebElement element;
    private static Header headerModule;
    private static ProductList productListModule;
    private static String productName;
    private static ProductListItem productListItem;

    public static void reset() {
        driver = null;
        currentPage = null;
        element = null;
        headerModule = null;
        productListModule = null;
        productName = null;
        productListItem = null;
    }

    public static Page getCurrentPage() {
        return currentPage;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static String getProductName() {
        return productName;
    }

    public static WebElement getElement(@NotNull String elementName) {
        if (currentPage != null) {
            try {
                element = currentPage.getElementByName(elementName);
            } catch (InvalidArgumentException e) {
                element = currentPage.getHeaderElementByName(elementName);
            }
            return element;
        } else
            throw new NullPointerException("Current page must not be null. Please set Current Page context before fetching elements.");
    }

    public static Header getHeaderModule() {
        if (headerModule != null) {
            return headerModule;
        } else
            throw new NullPointerException("Header module must not be null. Please make sure you have initialized module properly.");
    }

    public static ProductList getProductListModule() {
        if (headerModule != null) {
            return productListModule;
        } else
            throw new NullPointerException("Header module must not be null. Please make sure you have initialized module properly.");
    }

    public static ProductListItem getProductListItem() {
        return productListItem;
    }

    public static void setDriver(@NotNull WebDriver driver) {
        StepContext.driver = driver;
        log.debug("Webdriver " + driver.toString() + " has been passed to Step Context");
    }

    public static void setCurrentPage(@NotNull Page page) {
        StepContext.currentPage = page;
        log.debug("Current page " + page.toString() + " has been passed to Step Context");
    }

    public static void setProductName(String productName) {
        StepContext.productName = productName;
    }

    public static void setModule(@NotNull Header headerModule) {
        StepContext.headerModule = headerModule;
    }

    public static void setModule(@NotNull ProductList productListModule) {
        StepContext.productListModule = productListModule;
    }

    public static void setProductListItem(@NotNull ProductListItem productListItem) {
        StepContext.productListItem = productListItem;
    }
}
