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

    /**
     * Creates a horizontal or a vertical vector with a certain value
     * @param direction: An enum value that could be VERTICAL or HORIZONTAL
     * @param length: The distance of the vector component used depending on the direction
     * @return A vector with a 0 and {@code length} as X or Y value depending on the direction
     */
    public Vector getDirectionVector(SwipeDirection direction, float length) {
        switch (direction){
            case VERTICAL:
                return new Vector(0,length);
            case HORIZONTAL:
                return new Vector(length,0);
        }
        return null;
    }

    /**
     * Creates a horizontal or a vertical vector with a certain value
     * @param direction: An enum value that could be VERTICAL or HORIZONTAL
     * @param percentageLength: The percentage distance of the vector component used depending on the direction
     * @return A vector with a 0 and {@code percentageLength} (converted to pixels) as X or Y value depending on the direction
     */
    public Vector getPixelDirectionVector(SwipeDirection direction, float percentageLength){
        Vector directionVector = getDirectionVector(direction,percentageLength);
        return new Vector(directionVector.x * screenWidth,directionVector.y * screenHeight);
    }
    public Vector getElementCenter(WebElement element){
        int X = (element.getSize().width / 2) + element.getRect().getX();
        int Y = (element.getSize().height / 2) + element.getRect().getY();

        return  new Vector(X, Y);
    }

    /**
     * Receives percentage X and Y values and converts to absolute (pixels) values, using screen real width and height
     * @param xPercentage: The screen percentage in X axis
     * @param yPercentage: The screen percentage in Y axis
     * @return A vector with the pixel values equivalent to the percentages
     */
    public Vector getPixelsVector(float xPercentage, float yPercentage) {
        return new Vector(xPercentage * screenWidth,yPercentage * screenHeight);
    }
     /**
     * Receives percentage X and Y values and converts to absolute (pixels) values, using screen real width and height
     * @param percentageVector: A vector containing the X and Y percentages values
     * @return A vector with the pixel values equivalent to the percentages
     */
    public Vector getPixelsVector(Vector percentageVector) {
        return getPixelsVector(percentageVector.x, percentageVector.y);
    }

}
