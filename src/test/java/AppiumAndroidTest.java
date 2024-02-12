import com.google.common.io.Files;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Feature;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Feature("asdasdasd")
public class AppiumAndroidTest {
    public AndroidDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public void Android_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("appium:platformVersion", "11");
        capabilities.setCapability("appium:deviceName", "emulator-5554");
//        capabilities.setCapability("appium:deviceName", "49e9905f");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:app", "/home/yapmap.apk");
        driver = new AndroidDriver(new URL("http://172.17.0.2:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By signInBtn = By.id("sign_in_button");
    By activityWelcomeBtn = By.id("activity_welcome_button");
    By emailEditText = By.id("email_edit_text");

    @Test(description = "qweqwe")
    public void firstTest() throws IOException {
//        UiAutomator2Options options = new UiAutomator2Options()
//                .setUdid("123456")
//                .setApp("/home/myapp.apk");
//        driver.lockDevice();
//        if (driver.isDeviceLocked()){
//            driver.unlockDevice();
//        }
//        try {
//            waitElement(activityWelcomeBtn);
//            getScreenshot();
//            getElement(activityWelcomeBtn).click();
//        } catch (Exception ignored) {
//        }
//        waitElement(signInBtn);
//        getScreenshot();
//        getElement(signInBtn).click();
//        getElement(emailEditText).sendKeys("qeqeqeq");
        getScreenshot();
        driver.lockDevice();
        getScreenshot();
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public void waitElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
    }
//
//    @Attachment(type = "image/png")
//    public static byte[] getScreenshot(WebDriver driver) {
//        try {
//            File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            return Files.toByteArray(screen);
//        } catch (IOException e) {
//            return null;
//        }
//    }

    @Attachment(type = "image/png")
    public void getScreenshot() throws IOException {
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String file_name = "screenshot.png";
        FileUtils.copyFile(f, new File(file_name));
        Allure.attachment("screen", FileUtils.openInputStream(f));
    }

    @AfterClass
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}