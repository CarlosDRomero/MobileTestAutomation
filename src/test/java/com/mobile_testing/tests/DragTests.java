package com.mobile_testing.tests;

import com.mobile_testing.screens.DragScreen;
import com.mobile_testing.screens.HomeScreen;
import com.mobile_testing.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class DragTests extends BaseTest {
    DragScreen dragScreen;
    /**
     * @return an Array of drag and drop indexes that does not match
     */
    @DataProvider(name = "BadDragAndDrop")
    public Object[][] getBadDragAndDrop() {
        return new Object[][]{
            {Arrays.asList(new int[]{0, 2}, new int[]{8, 3}, new int[]{4, 3}, new int[]{3, 1})}
        };
    }
    /**
     * Navigates to the drag screen as a precondition for the following tests
     */
    @BeforeMethod
    public void navigateToDrag() {
        HomeScreen homeScreen = new HomeScreen(driver);
        dragScreen = homeScreen.navigateToDragScreen();
    }

    /**
     * This test drags all the puzzle pieces to their correct slots to check if a congratulations message is displayed after completing the puzzle
     */
    @Test(testName = "The user gets a congratulations message if the puzzle is completed")
    public void solvePuzzle() {
        dragScreen.solveDragAndDrop();
        Assert.assertTrue(dragScreen.isPuzzleCompleted());
    }
    /**
     * This test solves the puzzle and then tries to tap the retry button to check if the puzzle is really resetting
     */
    @Test(testName = "Once the user have completed the puzzle, they can reset it")
    public void puzzleCanBeReset() {
        dragScreen.solveDragAndDrop();
        dragScreen.tapRetrybutton();
        Assert.assertTrue(dragScreen.isDropAreaEmpty());

    }

    /**
     * This tests verifies that when an element
     * @param indexPairs: A list of drag and drop indexes pairs to test
     */
    @Test(
            testName = "The drag and drop elements does not disappear if the drag is no correct",
            dataProvider = "BadDragAndDrop"
    )
    public void failedDragAndDrop(List<int[]> indexPairs) {
        for (int[] indexPair : indexPairs) {
            int dragIndex = indexPair[0];
            int dropIndex = indexPair[1];
            dragScreen.dragElementByIndex(dragIndex, dropIndex);
            Assert.assertFalse(dragScreen.isSuccessDragAndDrop(dragIndex, dropIndex));
        }

    }

}
