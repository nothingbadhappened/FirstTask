package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class Header extends BaseClass {
    public Header(WebDriver driver) throws InterruptedException {
        super(driver);
    }

    @FindBy(how = How.LINK_TEXT, using="Sign in")
    public static WebElement signIn;

    @FindBy(how = How.CLASS_NAME, using = "page-heading")
    public static WebElement pageHeading;

    @FindBy(how = How.LINK_TEXT, using="Contact us")
    public static WebElement contactUs;

    @FindBy(how = How.LINK_TEXT, using="Sign out")
    public static WebElement signOut;

    @FindBy(how = How.LINK_TEXT, using="Women")
    public static WebElement menuWomen;

    @FindBy(how = How.XPATH, using="//*a[@title='Dresses']")
    public static WebElement menuDresses;

    @FindBy(how = How.XPATH, using="//*a[@title='T-shirts']")
    public static WebElement menuTshirts;

}
