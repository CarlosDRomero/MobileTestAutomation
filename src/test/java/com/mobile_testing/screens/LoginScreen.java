package com.mobile_testing.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginScreen extends SectionScreen{

    @AndroidFindBy(uiAutomator = "text(\"Login / Sign up Form\")")
    WebElement lblTitle;

    public LoginScreen(AppiumDriver driver) {
        super(driver);
    }

    public boolean isScreenDisplayed() {
        return lblTitle.isDisplayed();
    }
}
