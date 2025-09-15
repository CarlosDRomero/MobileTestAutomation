package com.mobile_testing.screens;

import com.mobile_testing.utils.gesture.SwipeDirection;
import com.mobile_testing.utils.gesture.Vector;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
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
    // I defined it as a list, because I can check:
    // if the element is present using a isEmpty condition instead of catching a NoSuchElement exception
    @AndroidFindBy(uiAutomator = "textContains(\"found me\")")
    List<WebElement> bottomElement;



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

    /**
     * Checks if a carousel element in the list is not displayed
     * @param index: Position of the carousel element in the list
     * @return {@code true} if the item is not displayed, otherwise, returns {@code false}.
     */
    public boolean isCarouselItemInvisible(int index) {
        disableImplicitWait();
        try {
            WebElement carouselItem = carouselItems.get(index);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            return wait.until(ExpectedConditions.invisibilityOf(carouselItem));
        } catch (NoSuchElementException e) {
            return true;
        } finally {
            resetImplicitWaitTimeout();
        }
    }
    /**
     * Executes a horizontal swipe to advance one carousel element
     * @return The {@code SwipeScreen}, because it remains on the same screen
     */
    public SwipeScreen swipeCarouselElement() {
        swipeFromElement(
                carousel,
                gestureController.getPixelDirectionVector(SwipeDirection.HORIZONTAL,-.25f)
        );

        return this;
    }

    /**
     * Swipes vertically to scroll down a certain distance
     * @return The {@code SwipeScreen}, because it remains on the same screen
     */
    public SwipeScreen swipeVertically() {
        swipeFromPoint(
                gestureController.getPixelsVector(.5f, .2f),
                gestureController.getPixelDirectionVector(SwipeDirection.VERTICAL,-.5f)
        );
        return this;
    }

    /**
     * Swipes n times vertically to get to the bottom of the screen
     * @param times: Is the number of times the swipe is going to be executed
     * @return The {@code SwipeScreen}, when it scrolls the stated number of times or when it reaches the bottom
     */
    public SwipeScreen swipeToBottom(int times) {
        // I'm taking advantage of the continuous swipe feature,
        // executing the swipes quickly prevents elements like the carousel from taking the scroll focus
        setImplicitWaitTimeout(Duration.ofMillis(100));
        for (int i=0; i < times; i++) {
            swipeVertically();
            if (isBottomElementDisplayed()) break;
        }
        resetImplicitWaitTimeout();
        return this;
    }

    /**
     * Checks if the list is empty, to verify if the element is present or not
     * @return {@code true} if the list is not empty, otherwise returns {@code false}
     */
    public boolean isBottomElementDisplayed() {
        return !bottomElement.isEmpty();
    }
}
