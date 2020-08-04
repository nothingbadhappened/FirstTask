package com.endava.helpers.util.actionsUtil;

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
}
