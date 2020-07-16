package com.endava.helpers.util.actionsUtil;

import com.endava.pageObjects.modules.ProductListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductList {

    private static final Logger log = LoggerFactory.getLogger(ProductList.class);
    private static WebDriver driver;
    private boolean isProductFound = false;

    private WebElement productItemDiscount;

    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/ul/li")
    private List<WebElement> productList;

    public ProductList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProductList() {
        return productList;
    }

    public boolean getIsProductFound() {
        return isProductFound;
    }

    public void setIsProductFound(boolean isProductFound) {
        this.isProductFound = isProductFound;
    }

    public ProductListItem getProductListItemByName(String productName) {
        WebElement currentElement;
        ProductListItem productListItem = null;

        for (int i = 0; i < productList.size(); i++) {

            // == List item XPATH locators ==
            int productListItemNbr = i + 1;
            String productListItemNameXPATH = "//*[@id=\"center_column\"]/ul/li[" + productListItemNbr + "]/div/div[2]/h5/a";
            currentElement = driver.findElement(By.xpath(productListItemNameXPATH));

            // Searching the product user requested
            if (currentElement.getText().equalsIgnoreCase(productName)) {

                isProductFound = true;
                log.debug("Item " + productName + " has been found. Setting up web elements...");

                String productListItemPriceXPATH = "//*[@id=\"center_column\"]/ul/li[" + productListItemNbr + "]/div/div[1]/div/div[2]/span[1]";
                String productListItemDiscountXPATH = "//*[@id=\"center_column\"]/ul/li[" + productListItemNbr + "]/div/div[1]/div/div[2]/span[3]";
                String productListItemAddToCartBtnXPATH = "//*[@id=\"center_column\"]/ul/li[" + productListItemNbr + "]/div/div[2]/div[2]/a[1]/span";

                WebElement productItemPrice = driver.findElement(By.xpath(productListItemPriceXPATH));
                WebElement productItemAddToCartBtn = driver.findElement(By.xpath(productListItemAddToCartBtnXPATH));

                try {
                    productItemDiscount = driver.findElement(By.xpath(productListItemDiscountXPATH));
                } catch (Exception e) {
                    log.info("Cannot find Discount element: There is no discount available for this item.", e.getMessage());
                    log.debug("Failed to locate Discount element: \n", e.getMessage());
                    productItemDiscount = null;
                } finally {
                    if (productItemDiscount != null) {
                        productListItem = new ProductListItem(currentElement, productItemPrice, productItemDiscount, productItemAddToCartBtn);
                        log.debug("Setup complete, the following elements are now available:\n"
                                + "Product item name location : " + currentElement.toString() + "\n"
                                + "Product item price location : " + productItemPrice.toString() + "\n"
                                + "Product item discount location:  " + productItemDiscount.toString() + "\n"
                                + "Product item add to cart button location: " + productItemAddToCartBtn.toString() + "\n");
                    } else {
                        productListItem = new ProductListItem(currentElement, productItemPrice, productItemAddToCartBtn);
                        log.debug("Setup complete, the following elements are now available:\n"
                                + "Product item name location : " + currentElement.toString() + "\n"
                                + "Product item price location : " + productItemPrice.toString() + "\n"
                                + "Product item add to cart button location: " + productItemAddToCartBtn.toString() + "\n");
                    }

                    //Stop the loop when product is found
                    i = productList.size();
                }

            }

        }
        if (isProductFound) {
            return productListItem;
        } else {
            log.debug("No search results returned...");
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {
        log.debug("Product List Item Object toString() method invoked");

        for (Field f : this.getClass().getFields()) {
            try {
                log.debug(f.getGenericType() + " " + f.getName() + " = " + f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "Product List Item Object created";
    }
}
