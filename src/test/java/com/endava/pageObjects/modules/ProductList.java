package com.endava.pageObjects.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ProductList {

    private static final Logger log = LoggerFactory.getLogger(ProductList.class);
    private final WebDriver driver;

    private final List<ProductListItem> mappedList = new ArrayList<>();

    public List<ProductListItem> getProductList() {
        if (mappedList.size() == 0) {
            populateProductList();
        }
        return mappedList;
    }

    public ProductList(WebDriver driver) {
        this.driver = driver;
    }

    // This will be used to init page with available products
    private void populateProductList() {

        // Clear the list in case there were any prior usages
        mappedList.clear();

        // Populate the rawList with unmapped [li] objects
        String basePath = "//ul[@class='product_list grid row']/li/*";
        //Collection of product list items
        List<WebElement> rawList = driver.findElements(By.xpath(basePath));

        log.debug("STARTING MAPPING OF ELEMENTS: List<WebElement> rawList -> List<ProductListItem> mappedList...");

        // For every [li] object in the rawList map the ProductListItem object and put it into the mappedList
        for (WebElement currentListItem : rawList) {
            log.debug("Current ListItem in the rawList: \n" + currentListItem.toString() + "\n" + currentListItem.getText());

            // Mapping elements
            log.debug("Mapping elements...");
            WebElement productItemNameElement = currentListItem.findElement(By.xpath(".//a[@class='product-name']"));
            WebElement productItemPriceElement = currentListItem.findElement(By.xpath(".//span[@class='price product-price']"));
            WebElement productItemAddToCartBtn = currentListItem.findElement(By.xpath(".//div[@class='button-container']/a[@title='Add to cart']"));
            WebElement productItemDiscountElement;

            // Map the discount WebElement, when present
            try {
                productItemDiscountElement = currentListItem.findElement(By.xpath(".//span[@class='price-percent-reduction']"));
                ProductListItem mappedItem = new ProductListItem(productItemNameElement, productItemPriceElement, productItemDiscountElement, productItemAddToCartBtn);
                mappedList.add(new ProductListItem(productItemNameElement, productItemPriceElement, productItemDiscountElement, productItemAddToCartBtn));
                log.debug("Current ListItem in the mappedList: \n" + mappedItem.toString() + "\n");
            } catch (Exception e) {
                log.debug("There is no discount available for this product: \n{}", e.getMessage());
                ProductListItem mappedItem = new ProductListItem(productItemNameElement, productItemPriceElement, productItemAddToCartBtn);
                mappedList.add(mappedItem);
                log.debug("Current ListItem in the mappedList: \n" + mappedItem.toString() + "\n");
            }
        }
    }

    public String toString() {
        log.debug("Product List Object toString() method invoked");

        for (Field f : this.getClass().getFields()) {
            try {
                log.debug(f.getGenericType() + " " + f.getName() + " = " + f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "[PRODUCT LIST OBJECT]";
    }

}
