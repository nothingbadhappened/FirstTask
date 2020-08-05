package com.endava.helpers.util.actionsUtil;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Assert {

    private static final Logger log = LoggerFactory.getLogger(Assert.class);

    public static void assertEquals(String actual, String expected) {
        try {
            org.testng.Assert.assertEquals(actual, expected);
            log.info("~~~ ASSERTION: PASSED ~~~");
        } catch (AssertionError e) {
            log.info("~~~ ASSERTION: FAILED [{}] ~~~\n{}", e.getMessage(), e.getStackTrace());
            throw e;
        }
    }

    public static void assertTrue(Boolean value) {
        try {
            org.testng.Assert.assertTrue(value);
            log.info("~~~ ASSERTION: PASSED ~~~");
        } catch (AssertionError e) {
            log.info("~~~ ASSERTION: FAILED [{}] ~~~\n{}", e.getMessage(), e.getStackTrace());
            throw e;
        }
    }

    public static void assertNoSuchElement(WebElement element) {
        try {
            org.testng.Assert.assertFalse(element.isDisplayed());
            log.info("~~~ ASSERTION: PASSED ~~~");
        } catch ( Exception | AssertionError e) {
            if (e.getClass().equals(AssertionError.class)) {
                log.info("~~~ ASSERTION: FAILED [{}] ~~~\n{}", e.getMessage(), e.getStackTrace());
                throw e;
            } else if (e.getClass().equals(NoSuchElementException.class)) {
                log.info("~~~ ASSERTION: PASSED ~~~");
            } else {
                log.info("~~~ ASSERTION: FAILED [{}] ~~~\n{}", e.getMessage(), e.getStackTrace());
                throw e;
            }
        }
    }
}
