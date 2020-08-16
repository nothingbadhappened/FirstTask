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

    public Header getHeader() {
        return header;
    }

    @Override
    public String toString() {
        return "[PRODUCT PAGE OBJECT]";
    }
}
