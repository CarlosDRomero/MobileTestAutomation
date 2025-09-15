package com.mobile_testing.screens;

import com.mobile_testing.utils.gesture.SwipeDirection;
import com.mobile_testing.utils.gesture.Vector;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class SwipeScreen extends SectionScreen {

    @AndroidFindBy(uiAutomator = "text(\"Swipe horizontal\")")
    WebElement lblTitle;

    // Carousel elements
    @AndroidFindBy(uiAutomator = "description(\"Carousel\")")
    WebElement carousel;
    @AndroidFindBy(uiAutomator = "resourceId(\"__CAROUSEL_ITEM_0_READY__\")")
    WebElement cslItem0;
    @AndroidFindBy(uiAutomator = "resourceId(\"__CAROUSEL_ITEM_1_READY__\")")
    WebElement cslItem1;
    @AndroidFindBy(uiAutomator = "resourceId(\"__CAROUSEL_ITEM_2_READY__\")")
    WebElement cslItem2;
    @AndroidFindBy(uiAutomator = "resourceId(\"__CAROUSEL_ITEM_3_READY__\")")
    WebElement cslItem3;
    @AndroidFindBy(uiAutomator = "resourceId(\"__CAROUSEL_ITEM_4_READY__\")")
    WebElement cslItem4;
    @AndroidFindBy(uiAutomator = "resourceId(\"__CAROUSEL_ITEM_5_READY__\")")
    WebElement cslItem5;

    @AndroidFindBy(uiAutomator = "textContains(\"found me\")")
    WebElement bottomElement;



    List<WebElement> carouselItems;

    public SwipeScreen(AppiumDriver driver) {
        super(driver);
        carouselItems = Arrays.asList(cslItem0,cslItem1, cslItem2, cslItem3, cslItem4, cslItem5);
    }

    public boolean isScreenDisplayed() {
        return lblTitle.isDisplayed();
    }

    /**
     * Checks if the selected item is displayed on the carousel
     * @param index: position of the item in the list of carousel items
     * @return {@code true} if the item is displayed, otherwise, returns {@code false}.
     */
    public boolean isCarouselItemVisible(int index) {
        return index >= 0 && index < carouselItems.size() && carouselItems.get(index).isDisplayed();
    }
    // TODO: Create function for ImplicitWait setup in BaseScreen
    public boolean isCarouselItemInvisible(int index) {
        try {
            WebElement carouselItem = carouselItems.get(index);

            // Set implicit wait to 0 for this specific check
            driver.manage().timeouts().implicitlyWait(Duration.ZERO);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            return wait.until(ExpectedConditions.invisibilityOf(carouselItem));
        } catch (NoSuchElementException e) {
            return true;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }
    }

    public SwipeScreen swipeCarouselElement() {
        swipe(carousel, SwipeDirection.HORIZONTAL,-.25f);

        return this;
    }

    public SwipeScreen swipeVertically() {
        swipe(gestureController.percentageToPixels(new Vector(.5f, .2f)), SwipeDirection.VERTICAL,-1f);
        return this;
    }
    public SwipeScreen swipeToBottom(int times) {
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        for (int i=0; i < times; i++) {
            swipe(gestureController.percentageToPixels(new Vector(.5f, .2f)), SwipeDirection.VERTICAL,-1f);
            if (isBottomElementDisplayed()) break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return this;
    }

    public boolean isBottomElementDisplayed() {
        return bottomElement.isDisplayed();
    }
}
