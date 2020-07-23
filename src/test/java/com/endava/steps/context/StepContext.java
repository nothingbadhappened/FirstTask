package com.endava.steps.context;

import com.endava.pageObjects.Page;
import com.endava.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class StepContext {

    private static final Logger log = LoggerFactory.getLogger(StepContext.class);
    private static Map<ContextUserKeys, User> userContextMap = new HashMap<>();
    private static Map<ContextPageKeys, Page> pageContextMap = new HashMap<>();

    public static Page getCurrentPage() {
        return pageContextMap.get(ContextPageKeys.CURRENT_PAGE);
    }

    public static void setCurrentPage(Page page) {
        pageContextMap.put(ContextPageKeys.CURRENT_PAGE, page);
        log.debug("Current page " + page.toString() + " has been passed to Step Context");
    }

    public static void reset() {
        pageContextMap.clear();
    }

    public User getUser() {
        return userContextMap.get(ContextUserKeys.CURRENT_USER);
    }

    public void setUser(User user) {
        userContextMap.put(ContextUserKeys.CURRENT_USER, user);
    }

}
