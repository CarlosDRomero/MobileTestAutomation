# Mobile Automation Activity (Appium + TestNG)

This repository contains the code for mobile test automation (Android) for a sample app located in `test_app/*.apk`.
Tests are written in **Java** using **Appium** (UiAutomator2), **TestNG** (configured via [testng.xml](testng.xml)) and a small set of in-project utilities for gestures (vectors, swipes, etc.).

## Summary

The goal is a test suite that validates navigation, authentication, and interactive UI behaviors (carousels, swipes, drag) in the app. Main utilities include:

* Automatic APK path resolution ([PathGenerator](src/test/java/com/mobile_testing/utils/PathGenerator.java)) so others can run tests locally without editing paths.
* A small vector-based gestures system (convert percentage coordinates to pixels, build reproducible `PointerInput` sequences).
* Controlled implicit wait helpers in [BaseScreen](src/test/java/com/mobile_testing/utils/BaseScreen.java) to avoid flakiness when mixing implicit and explicit waits.

Tests are orchestrated using **TestNG** through the [testng.xml](src/test/resources/testng.xml) file.

## APK automatic loading (PathGenerator)

[PathGenerator](src/test/java/com/mobile_testing/utils/PathGenerator.java) finds the absolute path to the first `*.apk` file inside the `test_app/` folder. If no APK is found, it throws a `RuntimeException` with a clear message.
### Usage
* Put **one** APK in `test_app/` and the project will automatically use it at runtime.
* On startup, the resolved APK path is printed in the console for quick verification.

This makes running the suite locally straightforward: the tester only needs to drop an apk into `test_app/`.

---

## Gesture system (Vectors & GestureController)

A brief, high-level explanation:

* **Vector**: represents coordinates and simple operations (add, scale, etc.). Used to describe movement in pixels or relative values.
* **GestureController**: converts relative values to absolute pixel coordinates (using device screen size or element bounds), computes centers of elements and helps to produce W3C `PointerInput` `Sequence` objects.
* **Why**: this explicitly shows how gestures are built. The implementation helps to create swipes, which helps with reproducibility and makes the gesture logic a little bit more maintainable for me.

Key gesture code locations:

* Base methods in [BaseScreen](src/test/java/com/mobile_testing/utils/BaseScreen.java)
* Gesture helpers in [gesture package](src/test/java/com/mobile_testing/utils/gesture/)

Example (conceptual, inside a BaseScreen subclass):

```java
// swipe from center of element up by 300 pixels
Vector up = new Vector(0, -300);
swipeFromElement(myElement, up);
```
---

## Test overview
Each test class contains Javadoc comments and descriptive `testName` annotations for clarity, but I think is a good practice to add this info to the README.
* **NavigationTests** ([NavigationTests.java](src/test/java/com/mobile_testing/tests/NavigationTests.java))

    * `tabsNavigation`: checks that each bottom menu button navigates to its corresponding screen, asserting the new screen is displayed in sequence.

* **AuthenticationTests** ([AuthenticationTests.java](src/test/java/com/mobile_testing/tests/AuthenticationTests.java))

    * `successfulSignUp`: validates that a user can sign up using valid random credentials and the sign-up flow succeeds.
    * `formErrors`: ensures the form shows validation error messages when fields are invalid (email, password, repeat password).
    * `successfulLogIn`: confirms a user can sign up, close the success panel, and then log in with the same credentials.
    * `badCredentialsLogin`: verifies that logging in with incorrect credentials fails (no successful login state).

* **SwipeTests** ([SwipeTests.java](src/test/java/com/mobile_testing/tests/SwipeTests.java))

    * `carouselScrolling`: sequential end-to-end test that swipes through carousel items and verifies each becomes invisible after swipe.
    * `swipeToBottom`: swipes until a bottom element is found.