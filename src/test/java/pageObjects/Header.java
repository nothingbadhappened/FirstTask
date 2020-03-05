package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header extends BaseClass {
    public Header(WebDriver driver) throws InterruptedException {
        super(driver);
    }

    @FindBy(how = How.LINK_TEXT, using="Sign in")
    public static WebElement sign_in;

    @FindBy(how = How.LINK_TEXT, using="Contact us")
    public static WebElement contact_us;

    @FindBy(how = How.LINK_TEXT, using="Sign out")
    public static WebElement sign_out;

    @FindBy(how = How.LINK_TEXT, using="Women")
    public static WebElement menu_women;

    @FindBy(how = How.XPATH, using="//*a[@title='Dresses']")
    public static WebElement menu_dresses;

    @FindBy(how = How.XPATH, using="//*a[@title='T-shirts']")
    public static WebElement menu_tshirts;

}
