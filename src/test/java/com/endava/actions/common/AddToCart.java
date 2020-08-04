package com.endava.actions.common;

import com.endava.helpers.util.actionsUtil.ObjectManipulator;
import com.endava.pageObjects.CartPage;
import com.endava.pageObjects.ProductPage;
import com.endava.pageObjects.SearchPage;
import com.endava.pageObjects.modules.ProductListItem;
import com.endava.steps.context.ContextKeys;
import com.endava.steps.context.StepContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddToCart {
    private final Logger log = LoggerFactory.getLogger(SignIn.class);

    @Autowired
    private ObjectManipulator executor;

    @Autowired
    private StepContext context;

    public void addSingleItemFromProductPage(ProductListItem productListItem) {
        executor.click(productListItem.getProductItemNameElement());
        ProductPage productPage = (ProductPage) context.getContext(ContextKeys.PRODUCT_PAGE);
        log.info("Updating Step Context: Current page is Product Page");
        context.setContext(ContextKeys.CURRENT_PAGE, productPage);

        executor.click(productPage.getAddToCartButtonProductPage());
        executor.click(productPage.getProceedToCheckoutBtn());
    }

    public void addSingleItemFromSearchPage(ProductListItem productListItem) throws InterruptedException {
        SearchPage searchPage = (SearchPage) context.getContext(ContextKeys.SEARCH_PAGE);
        log.info("Updating Step Context: Current page is Search Page");
        context.setContext(ContextKeys.CURRENT_PAGE, searchPage);

        executor.click(productListItem);
        executor.click(searchPage.getProceedToCheckoutBtn());

        log.info("Updating Step Context: Current page is Cart Page");
        context.setContext(ContextKeys.CURRENT_PAGE, context.getContext(ContextKeys.CART_PAGE));
    }

    public void addSingleItemFromSearch(ProductListItem productListItem) throws InterruptedException {
        executor.click(productListItem);
    }

}
