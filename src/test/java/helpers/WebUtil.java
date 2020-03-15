package helpers;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;

public class WebUtil {
    public static boolean isElementTextCorrect(WebDriver driver, @NotNull WebElement element, String text) throws Exception {
        Log.debug("VERIFY ELEMENT TEXT START");
        Log.debug("ELEMENT: getText() = " + element.getText());
        if (element.getText().equals(text)) {
            Log.debug("VERIFY ELEMENT TEXT: Verified - SUCCESS");
            return true;
        } else {
            Log.debug("VERIFY ELEMENT TEXT: Verified - FAIL");
            Log.debug("VERIFY ELEMENT TEXT END");
            return false;
        }
    }


}
