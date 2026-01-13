package tests;

import config.BaseTest;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VpnTest extends BaseTest {

    @Test
    public void testVPNConnection() {
        launchApp("com.helalik.italy.vpn");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement connectionRequest = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("/hierarchy/android.widget.FrameLayout")
                    )
            );

            if (connectionRequest != null) {
                System.out.println("Connection request widget found.");
                WebElement okBtn = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]\n")
                        )
                );
                if (okBtn != null) {
                    System.out.println("Connection request widget's 'OK' button found.");
                    okBtn.click();
                    System.out.println("Connection request widget's 'OK' button clicked in order to continue.");
                }
            }
        } catch (TimeoutException e) {
            System.out.println("Connection request widget not found.");
        }

        try {
            WebElement testAd = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text=\"Test Ad\"]")
                    )
            );
            if (testAd != null) {
                System.out.println("Test Ad window found.");
                WebElement dismissBtn = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("\t\n" +
                                        "//android.view.View[@resource-id=\"dismiss-button\"]\n")
                        )
                );
                if (dismissBtn != null) {
                    System.out.println("Test Ad window 'close' button found.");
                    dismissBtn.click();
                    System.out.println("Test Ad window 'close' button clicked in order to dismiss advertisement.");
                }
            }
        } catch (TimeoutException e) {
            System.out.println("Test ad widget not found.");
        }

        try{
            WebElement connectionMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@resource-id=\"com.helalik.italy.vpn:id/TextView4\"]")
                    )
            );
            if (connectionMessage != null) {
                System.out.printf("VPN connection message: %s", connectionMessage.getText());
            }
        } catch (Exception e) {
            System.err.printf("ERROR: %s", e.getMessage());
        }
    }
}
