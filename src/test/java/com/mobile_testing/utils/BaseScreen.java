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
    private static final int IMPLICIT_WAIT_SECONDS = 20;
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

    /**
     * Changes the implicit wait duration
     * @param duration: {@code Duration} object which allows to set values with flexibility
     */
    public void setImplicitWaitTimeout(Duration duration) {
        driver.manage().timeouts().implicitlyWait(duration);
    }

    /**
     * Sets the implicit wait duration to 0 to disable it
     */
    public void disableImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
    }
    /**
     * Sets the implicit wait duration to its default value
     */
    public void resetImplicitWaitTimeout() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
    }

    /**
     * Swipes a certain distance from the center of an element (Internally using {@code swipeFromPoint} method)
     * @param element: The element to get the center
     * @param directionVector: The vector that sets the direction of the swipe from that center, this must be given in pixels (use {@code gestureController} to convert values)
)
     */
    public void swipeFromElement(WebElement element, Vector directionVector) {
        // Getting the start point of the swipe
        Vector startPoint = gestureController.getElementCenter(element);
        swipeFromPoint(startPoint, directionVector);

    }

    /**
     * Swipes from the center of an element to the center of the other
     * @param startElement: Is the starting element of the swipe
     * @param endElement: Is the element where the swipe ends
     */
    public void swipeFromElement(WebElement startElement, WebElement endElement) {
        // Getting the start and end point of the swipe as the center of the elements
        Vector startPoint = gestureController.getElementCenter(startElement);
        Vector endPoint = gestureController.getElementCenter(endElement);
        swipe(startPoint, endPoint, Duration.ofMillis(100));

    }
    /**
     * Swipes a certain distance from a starting point (Internally using the {@code swipe} method)
     * @param startPoint: The vector representing the starting point of the swipe, this must be given in pixels (use {@code gestureController} to convert values)
     * @param directionVector: The vector that sets the direction of the swipe from that center, this must be given in pixels (use {@code gestureController} to convert values)
     */
    public void swipeFromPoint(Vector startPoint, Vector directionVector) {
        // Getting the start and end point of the swipe

        Vector endPoint = startPoint.add(directionVector);
        swipe(startPoint, endPoint, Duration.ofMillis(100));
    }
    /**
     * Swipes from a starting point to an ending point
     * @param startPoint: The vector representing the starting point of the swipe, this must be given in pixels (use {@code gestureController} to convert values)
     * @param endPoint: The vector representing the ending point of the swipe, this must be given in pixels (use {@code gestureController} to convert values)
     */
    public void swipe(Vector startPoint, Vector endPoint, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence sequence = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startPoint.getIntX(), startPoint.getIntY()))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), endPoint.getIntX(), endPoint.getIntY()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(sequence));
    }
}
