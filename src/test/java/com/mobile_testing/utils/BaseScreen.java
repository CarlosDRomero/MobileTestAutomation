package com.mobile_testing.utils;

import com.mobile_testing.utils.gesture.GestureController;
import com.mobile_testing.utils.gesture.SwipeDirection;
import com.mobile_testing.utils.gesture.Vector;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public abstract class BaseScreen {

    protected AppiumDriver driver;

    protected final GestureController gestureController;

    public BaseScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        gestureController = new GestureController(driver);
    }

    /**
     * Must be implemented to allow to check if the screen is really displayed, using one or more elements depending on the specific implementation
     * @return {@code true} if the screen is displayed, otherwise returns {@code false}
     */
    public abstract boolean isScreenDisplayed();

    public void swipe(WebElement element, SwipeDirection direction, float percentageLength) {
        // Getting the start and end point of the swipe
        Vector startPoint = gestureController.getElementCenter(element);
        swipe(startPoint, direction, percentageLength);

    }
    public void swipe(Vector startPoint, SwipeDirection direction, float percentageLength) {
        Vector endPoint = startPoint.add(gestureController.getDirectionVector(direction, percentageLength));
        swipe(startPoint, endPoint);
    }
    public void swipe(Vector startPoint, Vector endPoint) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence sequence = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startPoint.getIntX(), startPoint.getIntY()))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endPoint.getIntX(), endPoint.getIntY()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(sequence));
    }
}
