package com.endava.pageObjects.modules;

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

public class ProductList {

    private static final Logger log = LoggerFactory.getLogger(ProductList.class);
    private static WebDriver driver;
    private WebElement element;
    private boolean isProductFound = false;

    private WebElement productItemName;
    private WebElement productItemPrice;
    private WebElement productItemDiscount;
    private WebElement productItemAddToCartBtn;
    private WebElement failedSearchMessageElement;

    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/ul/li")
    private List<WebElement> productList;

    public ProductList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProductList() {
        return productList;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public boolean getIsProductFound() {
        return isProductFound;
    }

    public void setIsProductFound(boolean isProductFound) {
        this.isProductFound = isProductFound;
    }

    public WebElement getProductItemName() {
        if (isProductFound) return productItemName;
        else
            throw new IllegalStateException("Product has not been specified or found. Invoke locateProductItemByName(name) first!");
    }

    public WebElement getProductItemPrice() {
        if (isProductFound) return productItemPrice;
        else
            throw new IllegalStateException("Product has not been specified or found. Invoke locateProductItemByName(name) first!");
    }

    public WebElement getProductItemDiscount() {
        if (isProductFound) return productItemDiscount;
        else
            throw new IllegalStateException("Product has not been specified or found. Invoke locateProductItemByName(name) first!");
    }

    public WebElement getProductItemAddToCartBtn() {
        if (isProductFound) return productItemAddToCartBtn;
        else
            throw new IllegalStateException("Product has not been specified or found. Invoke locateProductItemByName(name) first!");
    }

    public WebElement getFailedSearchMessageElement() {
        setFailedSearchMessageElement();
        return failedSearchMessageElement;
    }

    public WebElement getProductListItemFieldByName(String name) {
        switch (name) {
            case "productItemName":
                element = getProductItemName();
                break;
            case "productItemPrice":
                element = getProductItemPrice();
                break;
            case "productItemDiscount":
                element = getProductItemDiscount();
                break;
            case "productItemAddToCartBtn":
                element = getProductItemAddToCartBtn();
                break;
            case "failedSearchMessageElement":
                element = getFailedSearchMessageElement();
                break;
            default:
                throw new IllegalArgumentException("Unexpected product name: " + name);
        }

        return element;
    }

    public ProductListItem getProductListItem() {
        if (isProductFound) {
            if (productItemDiscount != null) {
                return new ProductListItem(productItemName, productItemPrice, productItemDiscount, productItemAddToCartBtn);
            }
            return new ProductListItem(productItemName, productItemPrice, productItemAddToCartBtn);
        } else throw new IllegalStateException("Nothing to return, there are no items found");
    }

    public void locateProductItemElementsByProductName(String productName) {
        WebElement currentElement;

        for (int i = 0; i < productList.size(); i++) {

            // == List item XPATH locators ==
            int productListItemNbr = i + 1;
            String productListItemNameXPATH = "//*[@id=\"center_column\"]/ul/li[" + productListItemNbr + "]/div/div[2]/h5/a";
            currentElement = driver.findElement(By.xpath(productListItemNameXPATH));

            if (currentElement.getText().equalsIgnoreCase(productName)) {

                setIsProductFound(true);
                log.debug("Item " + productName + " has been found. Setting up web elements...");

                String productListItemPriceXPATH = "//*[@id=\"center_column\"]/ul/li[" + productListItemNbr + "]/div/div[1]/div/div[2]/span[1]";
                String productListItemDiscountXPATH = "//*[@id=\"center_column\"]/ul/li[" + productListItemNbr + "]/div/div[1]/div/div[2]/span[3]";
                String productListItemAddToCartBtnXPATH = "//*[@id=\"center_column\"]/ul/li[" + productListItemNbr + "]/div/div[2]/div[2]/a[1]/span";

                productItemName = currentElement;
                productItemPrice = driver.findElement(By.xpath(productListItemPriceXPATH));
                productItemAddToCartBtn = driver.findElement(By.xpath(productListItemAddToCartBtnXPATH));

                try {
                    productItemDiscount = driver.findElement(By.xpath(productListItemDiscountXPATH));
                } catch (Exception e) {
                    log.info("Cannot find Discount element: There is no discount available for this item.", e.getMessage());
                    log.debug("Failed to locate Discount element: \n", e.getMessage());
                    productItemDiscount = null;
                } finally {
                    if (productItemDiscount != null) {
                        log.debug("Setup complete, the following elements are now available:\n"
                                + "Product item name location : " + productItemName.toString() + "\n"
                                + "Product item price location : " + productItemPrice.toString() + "\n"
                                + "Product item discount location:  " + getProductItemDiscount().toString() + "\n"
                                + "Product item add to cart button location: " + getProductItemAddToCartBtn().toString() + "\n");
                    } else {
                        log.debug("Setup complete, the following elements are now available:\n"
                                + "Product item name location : " + productItemName.toString() + "\n"
                                + "Product item price location : " + productItemPrice.toString() + "\n"
                                + "Product item add to cart button location: " + getProductItemAddToCartBtn().toString() + "\n");
                    }

                    i = productList.size();
                }
            }

        }

    }

    private void setFailedSearchMessageElement(){
        if(!isProductFound) {
            failedSearchMessageElement = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p"));
            log.info("Could not find any results...");
        }
    }

    @Override
    public String toString() {
        log.debug("Product List Item Page Object toString() method invoked");

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
