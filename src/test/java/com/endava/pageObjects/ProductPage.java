package com.endava.pageObjects;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.modules.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductPage extends Page {
    private Header header;
    private WebElement element;

    //@FindBy(how = How.ID, using = "add_to_cart")
    @FindBy(how = How.XPATH, using = "//button[@name='Submit']")
    private WebElement addToCartButtonProductPage;

    public ProductPage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getWebDriver());
    }

    public WebElement getAddToCartButtonProductPage() {
        return addToCartButtonProductPage;
    }

    @Override
    public WebElement getElementByName(String elementName) {
        WebElement element;
        switch (elementName) {
            case "addToCartButtonProductPage":
                element = addToCartButtonProductPage;
                break;
            default:
                throw new IllegalArgumentException("Unexpected element name: " + elementName);
        }
        return element;
    }

    @Override
    public WebElement getHeaderElementByName(String elementName) {
        return null;
    }

    @Override
    public String toString() {
        return "This is Product Page Object";
    }
}
