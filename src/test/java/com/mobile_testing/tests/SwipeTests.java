package com.mobile_testing.tests;

import com.mobile_testing.screens.HomeScreen;
import com.mobile_testing.screens.SwipeScreen;
import com.mobile_testing.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwipeTests extends BaseTest {
    SwipeScreen swipeScreen;

    /**
     * Navigates to the swipe screen as a precondition for the following tests
     */
    @BeforeMethod
    public void navigateToSwipeSection() {
        HomeScreen homeScreen = new HomeScreen(driver);
        swipeScreen = homeScreen.navigateToSwipeSection();
    }

    /**
     * This test verifies that the carousel is responding correctly to swipes, and the elements are really being scrolled to the end
     */
    @Test(testName = "Scrolling the carousel items changes the elements being displayed")
    public void carouselScrolling() {
        // I start by checking if the first carousel element is displayed
        Assert.assertTrue(swipeScreen.isCarouselItemVisible(0));
        // Then, I decided to use a for loop because it makes more sense to me, I just need to swipe 5 times, and checking if the element has been hidden after each swipe
        int lastIndex = 4;
        for (int i = 0; i <= lastIndex; i++) {
            swipeScreen.swipeCarouselElement();
            Assert.assertTrue(swipeScreen.isCarouselItemInvisible(i));
        }
    }

    /**
     * This test checks if the application is allowing the user to scroll to the bottom in the swipe screen, despite the elements which could take the swipe focus
     */
    @Test(testName = "User can scroll to the bottom and then be able to see a certain element")
    public void swipeToBottom() {
        swipeScreen.swipeToBottom(5);
        // Try fluent wait
        Assert.assertTrue(swipeScreen.isBottomElementDisplayed());
    }
}
