package com.mobile_testing.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class WebviewScreen extends SectionScreen{
    @AndroidFindBy(className = "android.webkit.WebView")
    WebElement webView;

    public WebviewScreen(AppiumDriver driver) {
        super(driver);
    }
    public boolean isScreenDisplayed() {
        return webView.isDisplayed();
    }
}
