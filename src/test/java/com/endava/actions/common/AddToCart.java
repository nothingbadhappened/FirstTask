package com.endava.actions.common;

import com.endava.helpers.util.actionsUtil.ObjectManipulator;
import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.ProductPage;
import com.endava.pageObjects.SearchPage;
import com.endava.pageObjects.modules.ProductListItem;
import com.endava.steps.context.StepContext;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddToCart {
    private final Logger log = LoggerFactory.getLogger(SignInAction.class);

    @Autowired
    private Browser browser;

    @Autowired
    private ObjectManipulator executor;

    public void addSingleItemFromProductPage(ProductListItem productListItem) {
        executor.click(productListItem.getProductItemNameElement());
        ProductPage productPage = new ProductPage(browser);
        log.info("Updating Step Context: Current page is Product Page");
        StepContext.setCurrentPage(productPage);

        executor.click(productPage.getAddToCartButtonProductPage());
        executor.click(productPage.getProceedToCheckoutBtn());
    }

    public void addSingleItemFromSearchPage(ProductListItem productListItem) {
        SearchPage searchPage = new SearchPage(browser);
        executor.click(productListItem.getProductItemNameElement());

        log.info("Updating Step Context: Current page is Product Page");
        StepContext.setCurrentPage(searchPage);

        executor.click(searchPage.getProceedToCheckoutBtn());
    }

    public void addSingleItemFromSearchPage(WebElement addToCartBtn) {
        executor.click(addToCartBtn);
    }

    public void addSingleItemFromSearch(ProductListItem productListItem) throws InterruptedException {
        executor.click(productListItem);
    }

}
