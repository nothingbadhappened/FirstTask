package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseClass {

    public static WebDriver driver;

    public BaseClass(WebDriver driver){
        BaseClass.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);
       }
}
