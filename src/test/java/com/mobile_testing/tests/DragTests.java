package com.mobile_testing.tests;

import com.mobile_testing.screens.DragScreen;
import com.mobile_testing.screens.HomeScreen;
import com.mobile_testing.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragTests extends BaseTest {
    DragScreen dragScreen;

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

}
