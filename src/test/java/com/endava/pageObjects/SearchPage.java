package com.endava.pageObjects;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.modules.Header;
import com.endava.pageObjects.modules.ProductList;
import com.endava.pageObjects.modules.ProductListItem;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class SearchPage extends Page {

    private final Header header;
    private final ProductList productList;

    // Defaults product list item to the first element in the list. This can be overwritten.
    private ProductListItem productListItem;

    public SearchPage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getDriver());
        this.productList = new ProductList(browser.getDriver());
        if (productList.getProductList().size() > 0 && this.productListItem == null) {
            this.productListItem = productList.getProductList().get(0);
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"grid\"]/a/i")
    private WebElement gridModeBtn;

    @FindBy(how = How.XPATH, using = "//*[@id=\"list\"]/a/i")
    private WebElement listModeBtn;

    @FindBy(how = How.ID, using = "selectProductSort")
    private List<WebElement> sortByDropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/p")
    private WebElement failedSearchMessageElement;

    @FindBy(how = How.XPATH, using = "//div[@class='button-container']")
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

    public List<ProductListItem> getProductList(){
        return productList.getProductList();
    }

    public ProductListItem getProductListItem() {
        if (productListItem != null) {
            return productListItem;
        } else throw new NullPointerException("ProductListItem has not been initialised yet...");
    }

    public void setProductListItem(ProductListItem productListItem) {
        this.productListItem = productListItem;
    }

    public Header getHeader() {
        return header;
    }

    public List<WebElement> getSortByDropdown() {
        return sortByDropdown;
    }

    @Override
    public String toString() {
        return "[SEARCH PAGE OBJECT]";
    }
}
