package com.pageObjects;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageFactory {

    private static final Logger log = LoggerFactory.getLogger(PageFactory.class);
    private String page;
    private WebDriver driver;

    public Page getPageObject(@NotNull String page, WebDriver driver) {

        Page pageObject;
        this.driver = driver;
        this.page = page;

        log.debug("Generating page: " + page);

        switch (page) {
            case "body":
                pageObject = new Body(driver);
                log.debug("Body page object has been successfully created!");
                break;
            case "header":
                pageObject = new Header(driver);
                log.debug("Header page object has been successfully created!");
                break;
            default:
                log.error("Could not create page: " + page);
                throw new IllegalStateException("Unexpected value: " + page);
        }

        return pageObject;

    }

    public Page getPageObject(@NotNull Class<? extends Page> clazz, WebDriver driver) {
        Page pageObject;
        this.driver = driver;
        this.page = clazz.getSimpleName();

        log.debug("Generating page: " + clazz.getSimpleName());
        if (Body.class.equals(clazz)) {
            pageObject = new Body(driver);
            log.debug("Body page object has been successfully created!");
        } else if (Header.class.equals(clazz)) {
            pageObject = new Header(driver);
            log.debug("Header page object has been successfully created!");
        } else {
            log.error("Could not create page: " + page);
            throw new IllegalStateException("Unexpected value: " + page);
        }

        return pageObject;

    }

}



