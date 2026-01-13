package tests;

import config.Config;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VkVideoTest extends Config {

    @Test
    public void testVideoPlayback() {
        launchApp("com.vk.vkvideo");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement authorizationWidgetSkipBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.Button[@resource-id=\"com.vk.vkvideo:id/fast_login_tertiary_btn\"]\n")
                    )
            );

            if (authorizationWidgetSkipBtn != null) {
                System.out.println("Authorization widget's 'Skip' button found.");
                authorizationWidgetSkipBtn.click();
                System.out.println("Authorization widget's 'Skip' button clicked in order to skip authorization.");
            } else {
                throw new NoSuchElementException("authorization widget with button 'Skip' not found.");
            }

            WebElement firstVideoPreviewBlock = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("(//android.widget.FrameLayout[@resource-id=\"com.vk.vkvideo:id/content\"])[1]/android.widget.LinearLayout")
                    )
            );

            if (firstVideoPreviewBlock != null) {
                System.out.println("First video preview block found.");
                firstVideoPreviewBlock.click();
                System.out.println("First video preview block clicked in order to open the first video.");
            } else {
                throw new NoSuchElementException("first video preview block not found.");
            }

            WebElement videoContainer = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("com.vk.vkvideo:id/playerContainer")
                    )
            );

            if (videoContainer != null) {
                System.out.println("Video container found.");
                videoContainer.click();
                System.out.println("Video container clicked in order to make pause button visible.");
            } else {
                throw new NoSuchElementException("video container not found.");
            }

            WebElement pauseBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Pause")
                    )
            );
            if (pauseBtn != null) {
                System.out.println("Pause button found.");
                assertTrue(pauseBtn.isDisplayed());
                System.out.println("Video is playing!");
                pauseBtn.click();
                System.out.println("Pause button clicked in order to stop video playing.");
            } else {
                throw new NoSuchElementException("pause button not found.");
            }

            WebElement playBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Play")
                    )
            );
            if (playBtn != null) {
                System.out.println("Play button found.");
                assertTrue(playBtn.isDisplayed());
                System.out.println("Video isn't playing!");
            }
        } catch (NoSuchElementException e) {
            System.err.printf("WARNING: %s", e.getMessage());
        } catch (Exception e) {
            System.err.printf("UNKNOWN ERROR: %s", e.getMessage());
        }
    }
}
