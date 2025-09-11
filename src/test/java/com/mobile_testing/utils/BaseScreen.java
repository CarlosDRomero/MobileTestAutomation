package com.mobile_testing.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class BaseScreen {

    protected AppiumDriver driver;

    public BaseScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Must be implemented to allow to check if the screen is really displayed, using one or more elements depending on the specific implementation
     * @return {@code true} if the screen is displayed, otherwise returns {@code false}
     */
    public abstract boolean isScreenDisplayed();
}
