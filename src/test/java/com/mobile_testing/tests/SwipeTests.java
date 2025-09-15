package com.mobile_testing.tests;

import com.mobile_testing.screens.HomeScreen;
import com.mobile_testing.screens.SwipeScreen;
import com.mobile_testing.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwipeTests extends BaseTest {
    SwipeScreen swipeScreen;
    @BeforeMethod
    public void navigateToSwipeSection() {
        HomeScreen homeScreen = new HomeScreen(driver);
        swipeScreen = homeScreen.navigateToSwipeSection();
    }
    @Test(testName = "Scrolling the carousel items changes the elements being displayed")
    public void carouselScrolling() {
        int lastIndex = 4;
        for (int i = 0; i <= lastIndex; i++) {
            swipeScreen.swipeCarouselElement();
            Assert.assertTrue(swipeScreen.isCarouselItemInvisible(i));
        }
    }
    @Test
    public void swipeToBottom() {
        swipeScreen.swipeToBottom(5);
        // Try fluent wait
        Assert.assertTrue(swipeScreen.isBottomElementDisplayed());
    }
}
