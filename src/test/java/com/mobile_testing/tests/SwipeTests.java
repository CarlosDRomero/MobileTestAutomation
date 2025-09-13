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
    @Test
    public void SwipeTest() {
        int lastIndex = 5;
        for (int i = 0; i <= lastIndex; i++) {
            swipeScreen.swipeCarouselElement();
            Assert.assertTrue(swipeScreen.isCarouselItemInvisible(i));
        }
    }
}
