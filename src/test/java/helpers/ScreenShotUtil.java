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
import java.nio.file.Path;

public class ScreenShotUtil {

    private static WebDriver driver;
    private static String fileName = "Screenshot_" + System.currentTimeMillis() + ".png";
    private static byte[] byteScreenshot;
    private static Screenshot screenshot;

    //Constructors
    public ScreenShotUtil(WebDriver driver) throws IOException {
        ScreenShotUtil.driver = driver;
        doScreenshot();
        captureFullScreenAsByte(screenshot);
    }

    public ScreenShotUtil(WebDriver driver, Scenario scenario) throws IOException {
        ScreenShotUtil.driver = driver;
        fileName = "Screenshot" + System.currentTimeMillis() + "_" + scenario.getName() + ".png";
        doScreenshot();
        captureFullScreenAsByte(screenshot);
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

    private Screenshot doScreenshot() throws IOException {
        return captureFullScreen(driver);
    }

    // Capture full page screenshot method
    private static Screenshot captureFullScreen(WebDriver driver) throws IOException {
        Log.info("SCREENSHOT: Capture started");
        ScreenShotUtil.screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver);

        saveScreenshotToFile(screenshot);
        return screenshot;
    }

    // Capture full image in BYTE format method (needed for Cucumber appender as it only consumes Byte)
    private static void captureFullScreenAsByte(Screenshot screenshot) throws IOException {
        Log.info("SCREENSHOT: Capture started [BYTE]");
        BufferedImage image = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver).getImage();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "png", byteArrayOutputStream);

        byteArrayOutputStream.flush();
        ScreenShotUtil.byteScreenshot = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

    }

    // Save the screenshot in desired location
    private static void saveScreenshotToFile(Screenshot screenshot) throws IOException {

        Log.info("SCREENSHOT: Writing to file " + fileName);

        //Create screenshot directory if not present
        new File("./target/screenshots/").mkdirs();

        ImageIO.write(screenshot.getImage(), "PNG",
                new File("./target/screenshots/" + fileName));

    }
}
