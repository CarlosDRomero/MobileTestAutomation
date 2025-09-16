package com.mobile_testing.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class DragScreen extends SectionScreen {

    @AndroidFindBy(uiAutomator = "text(\"Drag and Drop\")")
    WebElement lblTitle;
    // Drag elements
    // Row 1 - Drop
    @AndroidFindBy(uiAutomator = "description(\"drop-l1\")")
    WebElement dropL1;
    @AndroidFindBy(uiAutomator = "description(\"drop-c1\")")
    WebElement dropC1;
    @AndroidFindBy(uiAutomator = "description(\"drop-r1\")")
    WebElement dropR1;

    // Row 2 - Drop
    @AndroidFindBy(uiAutomator = "description(\"drop-l2\")")
    WebElement dropL2;
    @AndroidFindBy(uiAutomator = "description(\"drop-c2\")")
    WebElement dropC2;
    @AndroidFindBy(uiAutomator = "description(\"drop-r2\")")
    WebElement dropR2;

    // Row 3 - Drop
    @AndroidFindBy(uiAutomator = "description(\"drop-l3\")")
    WebElement dropL3;
    @AndroidFindBy(uiAutomator = "description(\"drop-c3\")")
    WebElement dropC3;
    @AndroidFindBy(uiAutomator = "description(\"drop-r3\")")
    WebElement dropR3;

    // Row 1 - Drag
    @AndroidFindBy(uiAutomator = "description(\"drag-l1\")")
    WebElement dragL1;
    @AndroidFindBy(uiAutomator = "description(\"drag-c1\")")
    WebElement dragC1;
    @AndroidFindBy(uiAutomator = "description(\"drag-r1\")")
    WebElement dragR1;

    // Row 2 - Drag
    @AndroidFindBy(uiAutomator = "description(\"drag-l2\")")
    WebElement dragL2;
    @AndroidFindBy(uiAutomator = "description(\"drag-c2\")")
    WebElement dragC2;
    @AndroidFindBy(uiAutomator = "description(\"drag-r2\")")
    WebElement dragR2;

    // Row 3 - Drag
    @AndroidFindBy(uiAutomator = "description(\"drag-l3\")")
    WebElement dragL3;
    @AndroidFindBy(uiAutomator = "description(\"drag-c3\")")
    WebElement dragC3;
    @AndroidFindBy(uiAutomator = "description(\"drag-r3\")")
    WebElement dragR3;

    List<WebElement> dragList;
    List<WebElement> dropList;

    @AndroidFindBy(uiAutomator = "text(\"Congratulations\")")
    WebElement lblCongratulations;

    public DragScreen(AppiumDriver driver) {
        super(driver);
        dragList = Arrays.asList(dragL1, dragC1, dragR1, dragL2, dragC2, dragR2,  dragL3, dragC3, dragR3);
        dropList = Arrays.asList(dropL1, dropC1, dropR1,  dropL2, dropC2, dropR2, dropL3, dropC3, dropR3);
    }

    public boolean isScreenDisplayed() {
        return lblTitle.isDisplayed();
    }

    /**
     * Checks if the drag and drop elements pair is invisible, which means that it has been dragged to the correct slot
     * @param dragElementIndex: Is the index of the drag element in the list
     * @param dropElementIndex: Is the index of the drop element in the list
     * @return {@code true} if the elements are invisible, else it returns {@code false}
     */
    public boolean isSuccessDragAndDrop(int dragElementIndex, int dropElementIndex) {
        disableImplicitWait();
        WebElement dragElement = dragList.get(dragElementIndex), dropElement  = dropList.get(dropElementIndex);
        try {
            return explicitlyWait(Duration.ofMillis(500),
                    ExpectedConditions.and(
                            ExpectedConditions.invisibilityOf(dragElement),
                            ExpectedConditions.invisibilityOf(dropElement)
                    ));
        }catch (NoSuchElementException e) {
            return true;
        } catch (TimeoutException e) {
            return false;
        }finally {
            resetImplicitWaitTimeout();
        }
    }

    /**
     * Drags the correct drag element to its respective drop element, using the same index for both
     * @param index: Is the index of the drag element and the drop element in the lists
     * @return The {@code DragScreen}, because it remains on the same screen
     */
    public DragScreen dragElementByIndex(int index) {
        return dragElementByIndex(index, index);
    }
    /**
     * Drags the correct drag element to its respective drop element, using the same index for both
     * @param dragIndex: Is the index of the drag element in the list
     * @param dropIndex: Is the index of the drop element in the list
     * @return The {@code DragScreen}, because it remains on the same screen
     */
    public DragScreen dragElementByIndex(int dragIndex, int dropIndex) {
        swipeFromElement(dragList.get(dragIndex), dropList.get(dropIndex));
        return this;
    }

    /**
     * Drags and drops the all the drag elements to their respective drop areas
     * @return The {@code DragScreen}, because it remains on the same screen
     */
    public DragScreen solveDragAndDrop() {
        for (int i = 0; i< dragList.size(); i++) {
            dragElementByIndex(i);
        }
        return this;
    }

    /**
     * Checks if the congratulations label is displayed, to know if the puzzle has been successfully solved
     * @return {@code true} if the label is displayed, otherwise it returns {@code false}
     */
    public boolean isPuzzleCompleted() {
        return lblCongratulations.isDisplayed();
    }
}
