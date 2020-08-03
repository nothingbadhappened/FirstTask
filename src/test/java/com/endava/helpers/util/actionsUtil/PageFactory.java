package com.endava.helpers.util.actionsUtil;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.*;
import com.endava.steps.context.ContextKeys;
import com.endava.steps.context.StepContext;
import org.openqa.selenium.InvalidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageFactory {

    private static final Logger log = LoggerFactory.getLogger(PageFactory.class);

    @Autowired
    StepContext context;

    @Autowired
    Browser browser;

    public Page getPage(ContextKeys pageName) {

        switch (pageName) {
            case CART_PAGE:
                return new CartPage(browser);
            case HOME_PAGE:
                return new HomePage(browser);
            case LOGIN_PAGE:
                return new LoginPage(browser);
            case MY_ACCOUNT_PAGE:
                return new MyAccountPage(browser);
            case PRODUCT_PAGE:
                return new ProductPage(browser);
            case SEARCH_PAGE:
                return new SearchPage(browser);
            default:
                log.debug("Could not create requested page [{}]: bad argument", pageName);
                throw new InvalidArgumentException("Could not create requested page [" + pageName + "]: bad argument");
        }
    }
}