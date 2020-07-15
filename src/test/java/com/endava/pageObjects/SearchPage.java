package com.endava.pageObjects;

import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.modules.Header;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class SearchPage extends Page {

    private Header header;
    private WebElement element;

    public SearchPage(Browser browser) {
        super(browser);
        this.header = new Header(browser.getWebDriver());
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"grid\"]/a/i")
    private WebElement gridModeBtn;

    @FindBy(how = How.XPATH, using = "//*[@id=\"list\"]/a/i")
    private WebElement listModeBtn;

    @FindBy(how = How.ID, using = "selectProductSort")
    private List<WebElement> sortByDropdown;

    @Override
    public WebElement getElementByName(String elementName) {
        switch (elementName) {
            case "gridModeBtn":
                element = getGridModeBtn();
                break;
            case "listModeBtn":
                element = getListModeBtn();
                break;
            default:
                throw new InvalidArgumentException("Invalid Search Page element: " + elementName);
        }
        return element;
    }

    public WebElement getGridModeBtn() {
        return gridModeBtn;
    }

    public WebElement getListModeBtn() {
        return listModeBtn;
    }

    public List<WebElement> getSortByDropdown() {
        return sortByDropdown;
    }

    @Override
    public WebElement getHeaderElementByName(String elementName) {
        return header.getHeaderElementByName(elementName);
    }

    @Override
    public String toString() {
        return "This is the Search page object";
    }
}
