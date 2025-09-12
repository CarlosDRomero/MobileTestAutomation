package com.mobile_testing.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

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
    public boolean isItemVisible(int index) {
        return index >= 0 && index < carouselItems.size() && carouselItems.get(index).isDisplayed();
    }
}
