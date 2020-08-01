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

    public Object getPage(ContextKeys pageName) {
        Object page;
        // returns existing page
        if (context.getContext(pageName) != null) {
            log.debug("Page [{}] has been already created, returning existing instance", pageName);
            page = context.getContext(pageName);
        } else {
            // returns new page instance
            log.debug("There is no existing page [{}] present, creating new instance", pageName);
            switch (pageName) {
                case CART_PAGE:
                    page = new CartPage(browser);
                    context.setContext(ContextKeys.CART_PAGE, page);
                    break;
                case HOME_PAGE:
                    page = new HomePage(browser);
                    context.setContext(ContextKeys.HOME_PAGE, page);
                    break;
                case LOGIN_PAGE:
                    page = new LoginPage(browser);
                    context.setContext(ContextKeys.LOGIN_PAGE, page);
                    break;
                case MY_ACCOUNT_PAGE:
                    page = new MyAccountPage(browser);
                    context.setContext(ContextKeys.MY_ACCOUNT_PAGE, page);
                    break;
                case PRODUCT_PAGE:
                    page = new ProductPage(browser);
                    context.setContext(ContextKeys.PRODUCT_PAGE, page);
                    break;
                case SEARCH_PAGE:
                    page = new SearchPage(browser);
                    context.setContext(ContextKeys.SEARCH_PAGE, page);
                    break;
                default:
                    log.debug("Could not create requested page [{}]: bad argument", pageName);
                    throw new InvalidArgumentException("Could not create requested page [" + pageName + "]: bad argument");
            }
        }
        return page;
    }
}
