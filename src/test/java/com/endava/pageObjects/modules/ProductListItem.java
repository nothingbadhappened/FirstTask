package com.endava.pageObjects.modules;

import org.openqa.selenium.WebElement;

public class ProductListItem {

    //Product list item POJO
    private WebElement productItemName;
    private WebElement productItemPrice;
    private WebElement productItemDiscount;
    private WebElement productItemAddToCartBtn;

    public ProductListItem() {

    }

    public ProductListItem(WebElement productItemName,
                           WebElement productItemPrice,
                           WebElement productItemDiscount,
                           WebElement productItemAddToCartBtn) {
        this.productItemName = productItemName;
        this.productItemPrice = productItemPrice;
        this.productItemDiscount = productItemDiscount;
        this.productItemAddToCartBtn = productItemAddToCartBtn;
    }

    public ProductListItem(WebElement productItemName,
                           WebElement productItemPrice,
                           WebElement productItemAddToCartBtn) {
        this.productItemName = productItemName;
        this.productItemPrice = productItemPrice;
        this.productItemAddToCartBtn = productItemAddToCartBtn;
    }

    public WebElement getProductItemName() {
        return productItemName;
    }

    public WebElement getProductItemPrice() {
        return productItemPrice;
    }

    public WebElement getProductItemDiscount() {
        return productItemDiscount;
    }

    public WebElement getProductItemAddToCartBtn() {
        return productItemAddToCartBtn;
    }

}