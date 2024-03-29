package com.endava.pageObjects;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.modules.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class CartPage extends Page {
    public static final Logger log = LoggerFactory.getLogger(CartPage.class);

    @FindBy(how = How.ID, using = "cart_title")
    private WebElement cartTitleElement;

    @FindBy(how = How.XPATH, using = "//td[@class='cart_description']//p/a")
    private WebElement productItemName;

    private final Header header;

    public CartPage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getDriver());
    }

    public WebElement getCartTitleElement() {
        return cartTitleElement;
    }

    public WebElement getProductItemName() {
        return productItemName;
    }

    public Header getHeader() {
        return header;
    }

    @Override
    public String toString() {
        log.debug("Cart Page Object toString() method invoked");

        for (Field f : this.getClass().getFields()) {
            try {
                log.debug(f.getGenericType() + " " + f.getName() + " = " + f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "[CART PAGE OBJECT]";
    }

}
