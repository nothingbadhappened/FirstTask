package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

//TEST PROPERTIES LOAD

public class TestLocal {
    public static void propsTest() throws Exception {

        System.out.println("================ Props start: ================\n");
        PropertiesUtil.loadConfiguration();
        System.out.println("\n================ Props  end. ================");

        WebDriver driver = null;
        System.out.println("Driver in config file is: " + PropertiesUtil.getDriver());

        //Test Switch
        System.out.println("SWITCH IS STARTING");
        Thread.sleep(2000);

        switch (PropertiesUtil.getDriver()) {
            case "chrome":
                System.out.println("SWITCH: Driver is setting to Chrome");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.out.println("SWITCH: Driver is setting to Firefox");
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                System.out.println("SWITCH: Driver is setting to Safari");
                break;
            case "":
                throw new NoSuchFieldException();
        }


        Thread.sleep(1000);
        assert driver != null;
        driver.close();

    }
}
