package com.endava.pageObjects;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.modules.Header;
import com.endava.helpers.util.actionsUtil.ProductList;
import com.endava.pageObjects.modules.ProductListItem;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class CartPage extends Page {
    public static final Logger log = LoggerFactory.getLogger(CartPage.class);

    @FindBy(how = How.XPATH, using = "//*[@id=\"cart_title\"]")
    private WebElement cartTitleElement;

    @FindBy(how = How.XPATH, using = "//*[@id=\"product_7_34_0_275775\"]/td[2]/p/a")
    WebElement productItemName;

    private WebElement element;
    private Header header;
    private ProductList productList;
    private ProductListItem productListItem;

    public CartPage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getWebDriver());
        this.productList = new ProductList(browser.getWebDriver());
    }

    @Override
    public WebElement getElementByName(String elementName) {
        switch (elementName){
            case "cartTitleElement":
                element = getCartTitleElement();
                break;
        } return element;
    }

    @Override
    public WebElement getHeaderElementByName(String elementName) {
        return header.getHeaderElementByName(elementName);
    }

    public WebElement getCartTitleElement(){
        return cartTitleElement;
    }

    @Override
    public String toString() {
        log.debug("Body Page Object toString() method invoked");

        for (Field f : this.getClass().getFields()) {
            try {
                log.debug(f.getGenericType() + " " + f.getName() + " = " + f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "Cart Page Object created";
    }

}
