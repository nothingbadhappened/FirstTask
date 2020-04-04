package helpers;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ScreenShotUtil {

    private static WebDriver driver;
    private static String fileName = "Screenshot_" + System.currentTimeMillis() + ".png";
    private static byte[] byteScreenshot;
    private static Screenshot screenshot;

    //Constructors
    public ScreenShotUtil(WebDriver driver) throws IOException {
        ScreenShotUtil.driver = driver;
        doScreenshot();
        toByteScreenshot();
    }

    public ScreenShotUtil(WebDriver driver, Scenario scenario) throws IOException {
        ScreenShotUtil.driver = driver;
        fileName = "Screenshot" + System.currentTimeMillis() + "_" + scenario.getName() + ".png";
        doScreenshot();
        toByteScreenshot();
    }

    //Getters
    public String getScreenshotName() {
        return fileName;
    }
    public byte[] getByteScreenshotFullPage() {
        return byteScreenshot;
    }
    public Screenshot getScreenshotFullPage() {
        return screenshot;
    }

    //
    public void toByteScreenshot() throws IOException {
        ScreenShotUtil.byteScreenshot = captureFullScreenAsByte(captureFullScreen(driver));
    }

    private Screenshot doScreenshot() throws IOException {
        return captureFullScreen(driver);
    }

    // Capture full page screenshot method
    private static Screenshot captureFullScreen(WebDriver driver) throws IOException {
        Log.info("SCREENSHOT: Capture started");
        ScreenShotUtil.screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver);

        Log.info("SCREENSHOT: Saving to file \"" + fileName +"\"");
        saveScreenshotToFile(screenshot);
        return screenshot;
    }

    // Capture full image in BYTE format method (needed for Cucumber appender as it only consumes Byte)
    private static byte[] captureFullScreenAsByte(Screenshot screenshot) throws IOException {
        Log.info("SCREENSHOT: Capture started [BYTE]");
        BufferedImage image = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver).getImage();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "png", byteArrayOutputStream);

        byteArrayOutputStream.flush();
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        return imageInByte;
    }

    // Save the screenshot in desired location
    private static void saveScreenshotToFile(Screenshot screenshot) throws IOException {

        Log.info("SCREENSHOT: Writing to file " + fileName);
        ImageIO.write(screenshot.getImage(), "PNG",
                new File("./target/screenshots/" + fileName));

    }
}
