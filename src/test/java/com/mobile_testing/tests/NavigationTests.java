package com.mobile_testing.tests;

import com.mobile_testing.screens.*;
import com.mobile_testing.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTest {

    @Test
    public void tabsNavigation(){
        HomeScreen homeScreen = new HomeScreen(driver);
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
