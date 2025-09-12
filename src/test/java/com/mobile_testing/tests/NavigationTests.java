package com.mobile_testing.tests;

import com.mobile_testing.screens.*;
import com.mobile_testing.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Defines the test cases for app screens navigation
 */
public class NavigationTests extends BaseTest {
    HomeScreen homeScreen;

    @BeforeMethod
    public void loadHomeScreen() {
        homeScreen = new HomeScreen(driver);
    }
    /**
     * Checks the section buttons work properly and the new screen selected is displayed everytime.
     */
    @Test(testName = "Bottom menu navigation is working")
    public void tabsNavigation(){
        Assert.assertTrue(homeScreen.isScreenDisplayed());

        WebviewScreen webviewScreen = homeScreen.navigateToWebViewSection();
        Assert.assertTrue(webviewScreen.isScreenDisplayed());

        LoginScreen loginScreen = webviewScreen.navigateToLoginSection();
        Assert.assertTrue(loginScreen.isScreenDisplayed());

        FormsScreen formsScreen =  loginScreen.navigateToFormsSection();
        Assert.assertTrue(formsScreen.isScreenDisplayed());

        SwipeScreen swipeScreen = formsScreen.navigateToSwipeSection();
        Assert.assertTrue(swipeScreen.isScreenDisplayed());

        DragScreen dragScreen = swipeScreen.navigateToDragScreen();
        Assert.assertTrue(dragScreen.isScreenDisplayed());
    }
}
