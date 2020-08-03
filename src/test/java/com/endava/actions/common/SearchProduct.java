package com.endava.actions.common;

import com.endava.helpers.util.actionsUtil.ObjectManipulator;
import com.endava.helpers.util.actionsUtil.ProductListUtil;
import com.endava.pageObjects.HomePage;
import com.endava.pageObjects.SearchPage;
import com.endava.pageObjects.modules.ProductListItem;
import com.endava.steps.context.ContextKeys;
import com.endava.steps.context.StepContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class SearchProduct {

    private static final Logger log = LoggerFactory.getLogger(SearchProduct.class);

    @Autowired
    private ObjectManipulator executor;

    @Autowired
    private StepContext context;

    private ProductListItem productListItem;
    private boolean isProductFound = false;


    public void execute(String productName) {

        log.debug("Updating Step Context: Current page is Home Page");
        HomePage homePage = (HomePage) context.getContext(ContextKeys.HOME_PAGE);
        context.setContext(ContextKeys.CURRENT_PAGE, homePage);

        log.debug("Entering product name to search for: " + productName);
        executor.sendKeys(homePage.getHeader().getHeaderSearchBox(), productName);

        log.debug("Clicking Search button");
        executor.click(homePage.getHeader().getHeaderSearchButton());

        SearchPage searchPage = (SearchPage) context.getContext(ContextKeys.SEARCH_PAGE);

        log.debug("Updating Step Context: Current page is Search Page");
        context.setContext(ContextKeys.CURRENT_PAGE, searchPage);

        ProductListUtil productListUtil = new ProductListUtil(searchPage.getProductList());

        log.debug("Looking for the required product");

        try {
            productListItem = productListUtil.getProductListItemByName(productName);
            searchPage.setProductListItem(productListItem);
        } catch (NoSuchElementException e) {
            log.debug("Could not find product: " + productName, e.getMessage());
            e.getStackTrace();
        }

        isProductFound = productListUtil.getIsProductFound();

        // Store the found product list item object within search page
        if (isProductFound) {
            searchPage.setProductListItem(productListItem);
        }
    }

    public boolean getIsProductFound() {
        return isProductFound;
    }

    public void addToCart(String productName) {
        log.debug("Searching for an item to add to cart: " + productName);
        execute(productName);

        if (isProductFound) {
            log.debug("Item has been found. Clicking ADD TO CART button...");
            executor.click(productListItem.getProductItemAddToCartBtn());
        }
    }
}
