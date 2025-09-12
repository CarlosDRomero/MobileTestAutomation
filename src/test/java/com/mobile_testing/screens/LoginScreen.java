package com.mobile_testing.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginScreen extends SectionScreen{

    @AndroidFindBy(uiAutomator = "text(\"Login / Sign up Form\")")
    WebElement lblTitle;
    // Login form buttons
    @AndroidFindBy(uiAutomator = "description(\"button-login-container\")")
    WebElement btnLoginTab;
    @AndroidFindBy(uiAutomator = "description(\"button-LOGIN\")")
    WebElement btnLogin;
    //SignUp form buttons
    @AndroidFindBy(uiAutomator = "description(\"button-sign-up-container\")")
    WebElement btnSignUpTab;
    @AndroidFindBy(uiAutomator = "description(\"button-SIGN UP\")")
    WebElement btnSignUp;
    // Forms text fields
    @AndroidFindBy(uiAutomator = "description(\"input-email\")")
    WebElement txtEmail;
    @AndroidFindBy(uiAutomator = "description(\"input-password\")")
    WebElement txtPassword;
    @AndroidFindBy(uiAutomator = "description(\"input-repeat-password\")")
    WebElement txtRepeatPassword;
    // Form errors
    @AndroidFindBy(uiAutomator = "textContains(\"enter a valid email\")")
    WebElement lblEmailError;
    @AndroidFindBy(uiAutomator = "textContains(\"enter at least\")")
    WebElement lblPasswordError;
    @AndroidFindBy(uiAutomator = "textContains(\"enter the same password\")")
    WebElement lblRepeatPasswordError;
    // Success panel elements
    @AndroidFindBy(uiAutomator = "textContains(\"successfully signed up\")")
    WebElement lblSuccessSignUp;
    @AndroidFindBy(uiAutomator = "textContains(\"logged in\")")
    WebElement lblSuccessLoggedIn;
    @AndroidFindBy(uiAutomator = "text(\"OK\")")
    WebElement btnPanelOk;


    public LoginScreen(AppiumDriver driver) {
        super(driver);
    }

    public boolean isScreenDisplayed() {
        return lblTitle.isDisplayed();
    }

    /**
     * Taps the Login button to show the login form
     * @return The {@code LoginScreen}, because it remains on the same screen
     */
    public LoginScreen tapLoginTab() {
        btnLoginTab.click();
        return this;
    }
    /**
     * Taps the Sign-Up button to show the sign-up form
     * @return The {@code LoginScreen}, because it remains on the same screen
     */
    public LoginScreen tapSignUpTab() {
        btnSignUpTab.click();
        return this;
    }
    /**
     * Taps the Sign-Up button to submit the sign-up form
     * @return The {@code LoginScreen}, because it remains on the same screen
     */
    public LoginScreen tapSignUpButton() {
        btnSignUp.click();
        return this;
    }
    /**
     * Taps the Login button to submit the login form
     * @return The {@code LoginScreen}, because it remains on the same screen
     */
    public LoginScreen tapLoginButon() {
        btnLogin.click();
        return this;
    }

    /**
     * Checks if the email error label is displayed
     * @return {@code true} if the message is displayed, otherwise returns {@code false}
     */
    public boolean isEmailError() {
        return  lblEmailError.isDisplayed();
    }
    /**
     * Checks if the password error label is displayed
     * @return {@code true} if the message is displayed, otherwise returns {@code false}
     */
    public boolean isPasswordError() {
        return  lblPasswordError.isDisplayed();
    }
    /**
     * Checks if the repeat password error label is displayed
     * @return {@code true} if the message is displayed, otherwise returns {@code false}
     */
    public boolean isRepeatPasswordError() {
        return  lblRepeatPasswordError.isDisplayed();
    }
    /**
     * Types the email in the text field
     * @param email: The email value to be typed
     * @return The {@code LoginScreen}, because it remains on the same screen
     */
    public LoginScreen completeEmail(String email) {
        txtEmail.sendKeys(email);
        return this;
    }
    /**
     * Types the password in the text field
     * @param password: The password value to be typed
     * @return The {@code LoginScreen}, because it remains on the same screen
     */
    public LoginScreen completePassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }
    /**
     * Types the password repetition in the text field
     * @param repeatPassword: The repeat password value to be typed
     * @return The {@code LoginScreen}, because it remains on the same screen
     */
    public LoginScreen completeRepeatPassword(String repeatPassword) {
        txtRepeatPassword.sendKeys(repeatPassword);
        return this;
    }
    /**
     * Checks if the "successfully signed up" message is displayed
     * @return {@code true} if the message is displayed, otherwise returns {@code false}
     */
    public boolean isSuccessfulSignUp() {
        return lblSuccessSignUp.isDisplayed();
    }
    /**
     * Checks if the "successfully logged in" message is displayed
     * @return {@code true} if the message is displayed, otherwise returns {@code false}
     */
    public boolean isSuccessfulLogIn() {
        return lblSuccessLoggedIn.isDisplayed();
    }
    /**
     * Taps the 'OK' button on the success message panel, which closes the panel
     * @return The {@code LoginScreen}, because it remains on the same screen
     */
    public LoginScreen closePanel() {
        btnPanelOk.click();
        return this;
    }
    /**
     * Runs the complete sign-up flow, from selecting the form to press the sign-up button
     * @return The {@code LoginScreen}, because it remains on the same screen
     */
    public LoginScreen signUp(String email, String password, String repeatPassword) {
        tapSignUpTab();
        completeEmail(email);
        completePassword(password);
        completeRepeatPassword(repeatPassword);
        tapSignUpButton();
        return this;
    }
    /**
     * Runs the complete login flow, from selecting the form to press the login button
     * @return The {@code LoginScreen}, because it remains on the same screen
     */
    public LoginScreen login(String email, String password) {
        tapLoginTab();
        completeEmail(email);
        completePassword(password);
        tapLoginButon();
        return this;
    }
}
