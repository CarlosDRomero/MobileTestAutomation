package com.mobile_testing.utils;

// I decided to use java.nio to generate the APK path fo any OS
import java.io.IOException;
import java.nio.file.*;

/**
* This class is a utility for resolving the APK path automatically in the "test_app" folder
* */
public class PathGenerator {
    /**
    * Finds the absolute path to the first {@code *.apk} file found in the "test_app" folder
    * Also throws a RunTimeException if there is no any {@code *.apk} file inside the folder.
     *
     * @return A String containing an absolute path to the first found apk file
    * */
    public static String getApkPath(){
        Path dir = Paths.get("test_app");
        // This is a try-with-resources, which allows to get all the .apk files inside the "test_app" folder.
        try (DirectoryStream<Path> found = Files
                .newDirectoryStream(dir, "*.apk")) {
            Path apkPath = found.iterator().next();
            System.out.println(apkPath.toAbsolutePath().toString());
            return apkPath.toAbsolutePath().toString();
        }catch (IOException e) {
            throw  new RuntimeException("APK file not found, please, move the apk file to the folder: \"test_app\"");
        }
    }
}
