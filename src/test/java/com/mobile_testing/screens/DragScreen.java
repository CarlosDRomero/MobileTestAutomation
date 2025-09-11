package com.mobile_testing.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class DragScreen extends SectionScreen {

    @AndroidFindBy(uiAutomator = "text(\"Drag and Drop\")")
    WebElement lblTitle;

    public DragScreen(AppiumDriver driver) {
        super(driver);
    }

    public boolean isScreenDisplayed() {
        return lblTitle.isDisplayed();
    }
}
