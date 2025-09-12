package com.mobile_testing.tests;

import com.mobile_testing.screens.HomeScreen;
import com.mobile_testing.screens.LoginScreen;
import com.mobile_testing.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AuthenticationTests extends BaseTest {
    LoginScreen loginScreen;
    SoftAssert softAssert = new  SoftAssert();

    /**
     * Defines the navigation to the login screen as a precondition
     */
    @BeforeMethod
    public void navigateToLoginScreen() {
        HomeScreen homeScreen = new HomeScreen(driver);
        loginScreen = homeScreen.navigateToLoginSection();
    }

    /**
     * Tests the sign-up is successful when the fields are in the correct format
     */
    @Test(testName = "User can sign up using valid format email and password values")
    public void successfulSignUp() {
        loginScreen.signUp("correo@correo.com", "testtest", "testtest");
        Assert.assertTrue(loginScreen.isSuccessfulSignedUp());
    }
}
