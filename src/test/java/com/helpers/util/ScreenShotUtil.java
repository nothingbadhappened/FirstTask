package com.helpers.util;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ScreenShotUtil {

    private static final Logger log = LoggerFactory.getLogger(ScreenShotUtil.class);
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

    // Screen capture logic
    private Screenshot doScreenshot() throws IOException {
        return captureFullScreen(driver);
    }

    // Capture full page screenshot method
    private static Screenshot captureFullScreen(WebDriver driver) throws IOException {
        log.info("SCREENSHOT: Capture started");
        ScreenShotUtil.screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver);

        saveScreenshotToFile(screenshot);
        return screenshot;
    }

    // Capture full image in BYTE format method (needed for Cucumber appender as it only consumes Byte)
    private static void captureFullScreenAsByte(Screenshot screenshot) throws IOException {
        log.info("SCREENSHOT: Capture started [BYTE]");
        BufferedImage image = screenshot.getImage();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "png", byteArrayOutputStream);

        byteArrayOutputStream.flush();
        ScreenShotUtil.byteScreenshot = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

    }

    // Save the screenshot in desired location
    private static void saveScreenshotToFile(Screenshot screenshot) throws IOException {

        //Create screenshot directory if not present
        if (new File("./target/screenshots/").mkdirs()){
        log.info("SCREENSHOT: Directory not found, creating new directory: target\\/screenshots");
        }

        log.info("SCREENSHOT: Writing to file " + fileName);
        ImageIO.write(screenshot.getImage(), "PNG",
                new File("./target/screenshots/" + fileName));

    }
}
