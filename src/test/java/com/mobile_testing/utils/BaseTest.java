package com.mobile_testing.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class BaseTest {

    protected AppiumDriver driver;

    /**
     * Initializes the AppiumDriver using the {@code createDriver()} method
     */
    @BeforeMethod
    public void setUp() {
        driver = createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    /**
     * Sets the AppiumDriver up, setting the capabilities, device, app path and more
     * @return The AppiumDriver with all the options set.
     */
    public AppiumDriver createDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("android")
                .setApp(PathGenerator.getApkPath())
                .setDeviceName("emulator-5554");
        try{
            return new AppiumDriver(new URL("http://127.0.0.1:4723/"),options);
        }catch(MalformedURLException e){
            throw new RuntimeException(e);
        }
    }
}
