package config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class Config {

    protected AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Pixel_3a_API_30")
                .setAutomationName("UiAutomator2")
                .setNoReset(true);

        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void launchApp(String appPackage) {
        if (driver != null) {
            driver.activateApp(appPackage);
        }
    }
}
