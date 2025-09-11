package com.mobile_testing.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomeScreen extends SectionScreen {

    @AndroidFindBy(uiAutomator = "text(\"WEBDRIVER\")")
    WebElement lblTitle;

    public HomeScreen(AppiumDriver driver) {
        super(driver);
    }
    public boolean isScreenDisplayed() {
        return lblTitle.isDisplayed();
    }

}
