package com.endava.pageObjects;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.modules.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductPage extends Page {
    private final Header header;

    @FindBy(how = How.XPATH, using = "//button[@name='Submit']")
    private WebElement addToCartButtonProductPage;

    @FindBy(how = How.XPATH, using = "//div[@class='button-container']/a[@title='Proceed to checkout']")
    private WebElement proceedToCheckoutBtn;

    public ProductPage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getDriver());
    }

    public WebElement getAddToCartButtonProductPage() {
        return addToCartButtonProductPage;
    }

    public WebElement getProceedToCheckoutBtn() {
        return proceedToCheckoutBtn;
    }

    @Override
    public WebElement getElementByName(String elementName) {
        WebElement element;
        switch (elementName) {
            case "addToCartButtonProductPage":
                element = addToCartButtonProductPage;
                break;
            case "proceedToCheckoutBtn":
                element = proceedToCheckoutBtn;
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
        return "[PRODUCT PAGE OBJECT]";
    }
}
