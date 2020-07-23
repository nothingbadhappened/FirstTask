package com.endava.pageObjects.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductList {

    public ProductList(WebDriver driver) {
        this.driver = driver;
    }

    private static final Logger log = LoggerFactory.getLogger(ProductList.class);
    private WebDriver driver;

    //Collection of product list items
    private String basePath = "//ul[@class='product_list grid row']/li";
    private List<WebElement> rawList;
    private List<ProductListItem> mappedList = new ArrayList<>();

    public List<ProductListItem> getProductList() {
        if (mappedList.size() > 0) {
            return mappedList;
        } else {
            populateProductList();
            return mappedList;
        }
    }

    //init page with available products
    private void populateProductList() {

        //Clear the list
        mappedList.clear();
        rawList = driver.findElements(By.xpath(basePath));

        log.debug("STARTING MAPPING OF ELEMENTS: List<WebElement> rawList -> List<ProductListItem> mappedList...");

        for (WebElement currentListItem : rawList) {

            log.debug("Current ListItem in the rawList: \n" + currentListItem.toString() + "\n" + currentListItem.getText());

            log.debug("Mapping elements...");
            WebElement productItemName = currentListItem.findElement(By.xpath("//a[@class='product-name']"));
            WebElement productItemPrice = currentListItem.findElement(By.xpath("//div[@class='right-block']//span[@class='price product-price']"));
            WebElement productItemAddToCartBtn = currentListItem.findElement(By.xpath("//div[@class='button-container']/a[@title='Add to cart']"));
            WebElement productItemDiscount;

            // Map the discount WebElement, if present.
            try {
                productItemDiscount = currentListItem.findElement(By.xpath("//span[@class='price-percent-reduction']"));
                mappedList.add(new ProductListItem(productItemName, productItemPrice, productItemDiscount, productItemAddToCartBtn));
            } catch (Exception e) {
                log.debug("There is no discount available for this product: \n{}", e.getMessage());
                mappedList.add(new ProductListItem(productItemName, productItemPrice, productItemAddToCartBtn));
            }

        }

    }

}
