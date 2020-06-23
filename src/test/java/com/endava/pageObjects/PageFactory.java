package com.endava.pageObjects;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageFactory {

    private static final Logger log = LoggerFactory.getLogger(PageFactory.class);

    public static Page getPageObject(@NotNull String page) {
        Page pageObject;
        log.debug("Generating page: " + page);
        switch (page.toLowerCase()) {
            case "body":
                pageObject = new Body();
                log.debug("Body page object has been successfully created! " + pageObject.toString());
                break;
            case "header":
                pageObject = new Header();
                log.debug("Header page object has been successfully created! " + pageObject.toString());
                break;
            default:
                log.error("Could not create page: " + page);
                throw new IllegalStateException("Unexpected value: " + page);
        }
        return pageObject;
    }

    public static Page getPageObject(@NotNull Class<? extends Page> clazz) {
        Page pageObject;
        String page = clazz.getSimpleName();

        log.debug("Generating page: " + clazz.getSimpleName());
        if (Body.class.equals(clazz)) {
            pageObject = new Body();
            log.debug("Body page object has been successfully created! " + pageObject.toString());
        } else if (Header.class.equals(clazz)) {
            pageObject = new Header();
            log.debug("Header page object has been successfully created! " + pageObject.toString());
        } else {
            log.error("Could not create page: " + page);
            throw new IllegalStateException("Unexpected value: " + page);
        }
        return pageObject;
    }
}



