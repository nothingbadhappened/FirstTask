package com.endava.pageObjects;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.modules.Header;
import com.endava.pageObjects.modules.ProductList;
import com.endava.pageObjects.modules.ProductListItem;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class SearchPage extends Page {

    private Header header;
    private ProductList productList;
    private ProductListItem productListItem;

    public SearchPage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getWebDriver());
        this.productList = new ProductList(browser.getWebDriver());
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"grid\"]/a/i")
    private WebElement gridModeBtn;

    @FindBy(how = How.XPATH, using = "//*[@id=\"list\"]/a/i")
    private WebElement listModeBtn;

    @FindBy(how = How.ID, using = "selectProductSort")
    private List<WebElement> sortByDropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/p")
    private WebElement failedSearchMessageElement;

    @FindBy(how = How.XPATH, using = "//div[@class='layer_cart_cart col-xs-12 col-md-6']//div[@class='button-container']")
    private WebElement proceedToCheckoutBtn;



    public WebElement getGridModeBtn() {
        return gridModeBtn;
    }

    public WebElement getListModeBtn() {
        return listModeBtn;
    }

    public WebElement getFailedSearchMessageElement() {
        return failedSearchMessageElement;
    }

    public WebElement getProceedToCheckoutBtn() {
        return proceedToCheckoutBtn;
    }

    public List<WebElement> getSortByDropdown() {
        return sortByDropdown;
    }

    public List<ProductListItem> getProductList(){
        return productList.getProductList();
    }

    public ProductListItem getProductListItem() {
        return productListItem;
    }

    public void setProductListItem(ProductListItem productListItem) {
        this.productListItem = productListItem;
    }

    @Override
    public WebElement getHeaderElementByName(String elementName) {
        return header.getHeaderElementByName(elementName);
    }

    @Override
    public WebElement getElementByName(String elementName) {
        WebElement element;
        switch (elementName) {
            case "gridModeBtn":
                element = getGridModeBtn();
                break;
            case "listModeBtn":
                element = getListModeBtn();
                break;
            case "failedSearchMessageElement":
                element = failedSearchMessageElement;
                break;
            case "proceedToCheckoutBtn":
                element = proceedToCheckoutBtn;
                break;
            // This is specific to product list item only
            case "productItemNameElement":
                element = productListItem.getProductItemNameElement();
                break;
            case "productItemPriceElement":
                element = productListItem.getProductItemPriceElement();
                break;
            case "productItemDiscountElement":
                element = productListItem.getProductItemDiscountElement();
                break;
            case "productItemAddToCartBtn":
                element = productListItem.getProductItemAddToCartBtn();
                break;
            default:
                throw new InvalidArgumentException("Invalid Search Page element: " + elementName);
        }
        return element;
    }

    @Override
    public String toString() {
        return "[SEARCH PAGE OBJECT]";
    }
}
