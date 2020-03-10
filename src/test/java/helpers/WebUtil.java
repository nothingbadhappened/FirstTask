package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebUtil {
    public static boolean isElementTextCorrect(WebDriver driver, WebElement element, String text){

        if(element.getText().equals(text)){
            return true;
        } else return false;
    }
}
