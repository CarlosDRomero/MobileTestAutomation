package com.mobile_testing.tests;

import com.mobile_testing.screens.HomeScreen;
import com.mobile_testing.screens.LoginScreen;
import com.mobile_testing.utils.BaseTest;
import com.mobile_testing.utils.RandomEmailGenerator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AuthenticationTests extends BaseTest {
    LoginScreen loginScreen;
    SoftAssert softAssert = new  SoftAssert();
    @DataProvider(name = "randomEmailProvider")
    public Object[][] createData() {
        return new Object[][] {
                {RandomEmailGenerator.generateRandomEmail(), "12345678"},
        };
    }
    /**
     * Defines the navigation to the login screen as a precondition,
     */
    @BeforeMethod
    public void navigateToLoginScreen() {
        HomeScreen homeScreen = new HomeScreen(driver);
        loginScreen = homeScreen.navigateToLoginSection();
    }

    /**
     * This tests allows to check that the application is really allowing the users to do a sign-up when they use valid data.
     */
    @Test(
        testName = "User can sign up using valid format email and password values",
        dataProvider = "randomEmailProvider"
    )
    public void successfulSignUp(String email, String password) {
        loginScreen.signUp(email, password, password);
        Assert.assertTrue(loginScreen.isSuccessfulSignUp());
    }

    /**
     * This test allows to check that a user can create an account and then log in correctly
     */
    @Test(
        testName = "User can log in using the correct registered credentials",
        dataProvider = "randomEmailProvider"

    )
    public void successfulLogIn(String email, String password) {
        loginScreen.signUp(email, password, password);
        // Assuming the sign-up is successful, then a dialog panel must be shown
        loginScreen.closePanel();
        loginScreen.login(email, password);
        Assert.assertTrue(loginScreen.isSuccessfulLogIn());
    }


}
