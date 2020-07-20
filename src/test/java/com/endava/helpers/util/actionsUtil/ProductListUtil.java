package com.endava.helpers.util.actionsUtil;

import com.endava.pageObjects.modules.ProductListItem;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductListUtil {

    private static final Logger log = LoggerFactory.getLogger(ProductListUtil.class);
    private static WebDriver driver;
    private boolean isProductFound = false;

    private List<ProductListItem> productList;

    public ProductListUtil(@NotNull List<ProductListItem> productList) {
        this.productList = productList;
    }

    public boolean getIsProductFound() {
        return isProductFound;
    }

    public ProductListItem getProductListItemByName(String productName) {
        ProductListItem item = null;

        // Check if needed  item is present in the collection
        for (ProductListItem currentItem : productList) {
            if (productName.equalsIgnoreCase(currentItem.getProductItemName().getText())) {
                isProductFound = true;
                item = currentItem;
            }
        }

        // Return item if present
        if (isProductFound) {
            return item;
        } else {
            log.debug("No search results returned...");
            throw new NoSuchElementException();
        }
    }

    public ProductListItem getFirstProductListItem() {
        return productList.get(0);
    }

    public ProductListItem getLastProductListItem() {
        return productList.get(productList.size() - 1);
    }

    @Override
    public String toString() {
        log.debug("Product List Object toString() method invoked");

        for (Field f : this.getClass().getFields()) {
            try {
                log.debug(f.getGenericType() + " " + f.getName() + " = " + f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "Search Util Object created";
    }
}
