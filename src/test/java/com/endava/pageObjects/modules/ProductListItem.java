package com.endava.pageObjects.modules;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class ProductListItem {

    //Product list item POJO
    private static final Logger log = LoggerFactory.getLogger(ProductListItem.class);
    private WebElement productItemNameElement;
    private WebElement productItemPriceElement;
    private WebElement productItemDiscountElement;
    private WebElement productItemAddToCartBtn;

    // Creates a deep copy object
    public ProductListItem(ProductListItem productListItem) {
        this.productItemNameElement = productListItem.getProductItemNameElement();
        this.productItemPriceElement = productListItem.getProductItemPriceElement();
        this.productItemAddToCartBtn = productListItem.getProductItemAddToCartBtn();
        if (productListItem.getProductItemDiscountElement() != null) {
            this.productItemDiscountElement = productListItem.getProductItemDiscountElement();
        }
    }

    // Creates an instance with discount
    public ProductListItem(WebElement productItemName,
                           WebElement productItemPriceElement,
                           WebElement productItemDiscountElement,
                           WebElement productItemAddToCartBtn) {
        this.productItemNameElement = productItemName;
        this.productItemPriceElement = productItemPriceElement;
        this.productItemDiscountElement = productItemDiscountElement;
        this.productItemAddToCartBtn = productItemAddToCartBtn;
    }

    // Creates an instance without discount
    public ProductListItem(WebElement productItemNameElement,
                           WebElement productItemPrice,
                           WebElement productItemAddToCartBtn) {
        this.productItemNameElement = productItemNameElement;
        this.productItemPriceElement = productItemPrice;
        this.productItemAddToCartBtn = productItemAddToCartBtn;
    }

    public WebElement getProductItemNameElement() {
        return productItemNameElement;
    }

    public WebElement getProductItemPriceElement() {
        return productItemPriceElement;
    }

    public WebElement getProductItemDiscountElement() {
        return productItemDiscountElement;
    }

    public WebElement getProductItemAddToCartBtn() {
        return productItemAddToCartBtn;
    }

    public String toString() {
        log.debug("Header Page Object toString() method invoked");

        for (Field f : this.getClass().getFields()) {
            try {
                log.debug(f.getGenericType() + " " + f.getName() + " = " + f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "[PRODUCT LIST ITEM OBJECT]";
    }

}