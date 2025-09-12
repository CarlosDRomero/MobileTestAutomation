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
    public Object[][] randomEmailData() {
        return new Object[][] {
                {RandomEmailGenerator.generateRandomEmail(), "12345678"},
        };
    }
    @DataProvider(name = "badDataProvider")
    public Object[][] createData() {
        return new Object[][] {
                {"correont", "1234557", "123457"}
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
     * This test allows to check if the form validations are correct and are displaying error respectively.
     */
    @Test(
            testName = "The form shows error messages when the fields are not valid",
            dataProvider = "badDataProvider"
    )
    public void formErrors(String email, String password, String repeatPassword) {
        loginScreen.signUp(email, password, repeatPassword);
        softAssert.assertTrue(loginScreen.isEmailError());
        softAssert.assertTrue(loginScreen.isPasswordError());
        softAssert.assertTrue(loginScreen.isRepeatPasswordError());
        softAssert.assertAll();
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
