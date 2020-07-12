package com.endava.actions.common;

import com.endava.helpers.util.Browser;
import com.endava.helpers.util.ObjectManipulator;
import com.endava.pageObjects.HomePage;
import com.endava.pageObjects.SearchPage;
import com.endava.pageObjects.modules.Header;
import com.endava.pageObjects.modules.ProductList;
import com.endava.steps.StepContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
    private ProductList productList;
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
        StepContext.setProductName(productName);

        log.debug("Entering product name to search for: " + productName);
        executor.sendKeys(header.getHeaderSearchBox(), productName);

        log.debug("Clicking Search button");
        executor.click(header.getHeaderSearchButton());

        searchPage = new SearchPage(browser);
        productList = searchPage.getProductList();

        log.debug("Updating Step Context: Current page is Search Page");
        StepContext.setCurrentPage(searchPage);

        log.debug("Looking for the required product");

        productList.locateProductItemElementsByProductName(productName);

        isProductFound = productList.getIsProductFound();

        log.debug("Updating Step Context: Passing header module data");
        StepContext.setModule(header);

        log.debug("Updating Step Context: Passing product list module data");
        StepContext.setModule(productList);

        log.debug("Updating Step Context: Passing found product list item data");
        StepContext.setProductListItem(productList.getProductListItem());
    }

    public boolean getIsProductFound() {
        return isProductFound;
    }

    public void addToCart(String productName) {
        log.debug("Searching for an item to add to cart: " + productName);
        execute(productName);

        if (isProductFound) {
            log.debug("Item has been found. Clicking ADD TO CART button...");
            executor.click(productList.getProductItemAddToCartBtn());
        }
    }

    public String getSearchFailedMessage(String productName) {
        execute(productName);
        if (!isProductFound) {
            return productList.getFailedSearchMessageElement().getText();
        } else throw new IllegalStateException("There is no Failed Search error message present.");
    }

}
