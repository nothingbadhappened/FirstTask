package com.endava.helpers.util.actionsUtil;

import com.endava.helpers.util.customExceptions.ElementStillPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Assert {

    private static final Logger log = LoggerFactory.getLogger(Assert.class);

    public static void assertEquals(String actual, String expected) {
        try {
            org.testng.Assert.assertEquals(actual, expected);
            log.info("~~~ STEP: PASSED ~~~");
        } catch (AssertionError e) {
            log.info("~~~ STEP: FAILED [{}] ~~~\n{}", e.getMessage(), e.getStackTrace());
            throw e;
        }
    }

    public static void assertTrue(Boolean value) {
        try {
            org.testng.Assert.assertTrue(value);
            log.info("~~~ STEP: PASSED ~~~");
        } catch (AssertionError e) {
            log.info("~~~ STEP: FAILED [{}] ~~~\n{}", e.getMessage(), e.getStackTrace());
            throw e;
        }
    }

    public static void assertNoSuchElement(WebElement element) throws ElementStillPresentException {
        try {
            log.error("~~~ STEP: FAILED ~~~\n[{}] element is still present", element.getText());
            ElementStillPresentException e = new ElementStillPresentException(element);
            org.testng.Assert.fail(e.toString());
            throw e;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            log.info("~~~ STEP: PASSED ~~~");
        }
    }
}
