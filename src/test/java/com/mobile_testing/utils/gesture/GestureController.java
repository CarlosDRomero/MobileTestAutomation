package com.mobile_testing.utils.gesture;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Defines useful methods for gesture calculations
 */
public class GestureController {

    private final int screenHeight;
    private final int screenWidth;

    public GestureController (WebDriver driver) {
        screenWidth = driver.manage().window().getSize().getWidth();
        screenHeight = driver.manage().window().getSize().getHeight();
    }
    public Vector getDirectionVector(SwipeDirection direction, int length) {
        switch (direction){
            case VERTICAL:
                return new Vector(0,length);
            case HORIZONTAL:
                return new Vector(length,0);
        }
        return null;
    }
    public Vector getDirectionVector(SwipeDirection direction, float percentageLength){
        return getDirectionVector(direction,
            (int)(percentageLength * (direction == SwipeDirection.HORIZONTAL? screenWidth : screenHeight)));
    }
    public Vector getElementCenter(WebElement element){
        int X = (element.getSize().width / 2) + element.getRect().getX();
        int Y = (element.getSize().height / 2) + element.getRect().getY();

        return  new Vector(X, Y);
    }

    public Vector percentageToPixels(Vector vector) {
        return new Vector(vector.x * screenWidth, vector.y * screenHeight);
    }

}
