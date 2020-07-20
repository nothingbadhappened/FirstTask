package com.endava.actions.common;

import com.endava.helpers.util.actionsUtil.ObjectManipulator;
import com.endava.helpers.util.actionsUtil.SearchUtil;
import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.HomePage;
import com.endava.pageObjects.SearchPage;
import com.endava.pageObjects.modules.Header;
import com.endava.pageObjects.modules.ProductListItem;
import com.endava.steps.StepContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.NoSuchElementException;

@Component
public class SearchProduct {

    private static final Logger log = LoggerFactory.getLogger(SearchProduct.class);

    @Autowired
    private ObjectManipulator executor;

    @Autowired
    private Browser browser;

    private HomePage homePage;
    private Header header;

    private SearchPage searchPage;
    private SearchUtil productList;
    private ProductListItem productListItem;
    private boolean isProductFound = false;

    @PostConstruct
    private void init() {
        this.homePage = new HomePage(browser);
        this.header = homePage.getHeader();
    }

    public void execute(String productName) {

        log.debug("Updating Step Context: Current page is Search Page");
        StepContext.setCurrentPage(homePage);

        log.debug("Updating Step Context: Current product name: " + productName);
        StepContext.setProductNameElement(productName);

        log.debug("Entering product name to search for: " + productName);
        executor.sendKeys(header.getHeaderSearchBox(), productName);

        log.debug("Clicking Search button");
        executor.click(header.getHeaderSearchButton());

        searchPage = new SearchPage(browser);
        productList = new SearchUtil(browser.getWebDriver());

        log.debug("Updating Step Context: Current page is Search Page");
        StepContext.setCurrentPage(searchPage);

        log.debug("Looking for the required product");

        try {
            productListItem = productList.getProductListItemByName(productName);
        } catch (NoSuchElementException e) {
            log.debug("Could not find product: " + productName, e.getMessage());
            e.getStackTrace();
        }

        isProductFound = productList.getIsProductFound();

        log.debug("Updating Step Context: Passing header module data");
        StepContext.setModule(header);


        if (isProductFound) {
            log.debug("Updating Step Context: Passing product list item data");
            StepContext.setProductListItem(productListItem);
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
