package com.mobile_testing.screens;

import com.mobile_testing.utils.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

/**
 * This class represents the common elements between all the sections, which are the section buttons at the bottom
 */
public abstract class SectionScreen extends BaseScreen {
    @AndroidFindBy(uiAutomator = "description(\"Home\")")
    protected WebElement btnHomeSection;

    @AndroidFindBy(uiAutomator = "description(\"Webview\")")
    protected WebElement btnWebView;

    @AndroidFindBy(uiAutomator = "description(\"Login\")")
    protected WebElement btnLogin;

    @AndroidFindBy(uiAutomator = "description(\"Forms\")")
    protected WebElement btnForms;

    @AndroidFindBy(uiAutomator = "description(\"Swipe\")")
    protected WebElement btnSwipe;

    @AndroidFindBy(uiAutomator = "description(\"Drag\")")
    protected WebElement btnDrag;


    public SectionScreen(AppiumDriver driver) {
        super(driver);
    }

    public HomeScreen navigateToHomeSection() {
        btnHomeSection.click();
        return new HomeScreen(driver);
    }

    public WebviewScreen navigateToWebViewSection() {
        btnWebView.click();
        return new WebviewScreen(driver);
    }

    public LoginScreen navigateToLoginSection() {
        btnLogin.click();
        return new LoginScreen(driver);
    }

    public FormsScreen  navigateToFormsSection() {
        btnForms.click();
        return new FormsScreen(driver);
    }

    public SwipeScreen navigateToSwipeSection() {
        btnSwipe.click();
        return new SwipeScreen(driver);
    }

    public DragScreen navigateToDragScreen() {
        btnDrag.click();
        return new DragScreen(driver);
    }
}
