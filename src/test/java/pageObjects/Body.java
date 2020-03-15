package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Body extends BaseClass {
    public Body(WebDriver driver){
        super(driver);
    }

    public static class LoginPage{
        @FindBy(how= How.ID, using="email")
        public static WebElement email;

        @FindBy(how=How.ID, using="passwd")
        public static WebElement password;

        @FindBy(how=How.ID, using="SubmitLogin")
        public static WebElement signin_button;

        @FindBy(how=How.XPATH, using="//*[@id=\"center_column\"]/div[1]/ol/li")
        public static WebElement login_error;

        @FindBy(how=How.ID, using="email_create")
        public static WebElement email_create;

        @FindBy(how=How.ID, using="SubmitCreate")
        public static WebElement submit_create;
    }

}
