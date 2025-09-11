package com.mobile_testing.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class FormsScreen extends SectionScreen {

    @AndroidFindBy(uiAutomator = "text(\"Form components\")")
    WebElement lblTitle;

    public FormsScreen(AppiumDriver driver) {
        super(driver);
    }

    public boolean isScreenDisplayed() {
        return lblTitle.isDisplayed();
    }
}
