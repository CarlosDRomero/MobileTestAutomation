package com.mobile_testing.utils;

/**
 * A class that defines static methods for generating random strings to create random valid emails
 */
public class RandomEmailGenerator {
    // The following constants define the limits for the email random alphabetical and numerical part
    private static final int MIN_AZ_LENGTH = 6;
    private static final int MAX_AZ_LENGTH = 12;
    private static final int MIN_NUM_LENGTH = 3;
    private static final int MAX_NUM_LENGTH = 5;
    /**
     * Base method for generating random strings from random characters sets
     * @param chars: A {@code String} containing the set of characters allowed for randomization
     * @param length: The length of the expected random string
     * @return The random generated string
     */
    private static String randomStringFromCharSet(String chars, int length) {
        StringBuilder generatedString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            generatedString.append(chars.charAt((int) (Math.random() * chars.length())));

        }
        return generatedString.toString();
    }

    /**
     * Generates a random alphabetical only string
     * @param length: The length of the expected random string
     * @return The random string containing A-Z characters
     */
    private static String randomAZString(int length) {
        return randomStringFromCharSet("abcdefghijklmnopqrstuvwxyz", length);
    }
    /**
     * Generates a random numerical only string
     * @param length: The length of the expected random string
     * @return The random string containing 0-9 characters
     */
    private static String randomNumbersString(int length) {
        return randomStringFromCharSet("0123456789", length);
    }

    /**
     * Generates a random number between the {@code min} and {@code max} values
     * @param min: The minimum number in the range
     * @param max: The maximum number in the range
     * @return A random integer in the range
     */
    private static int getRandomLength(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static String generateRandomEmail() {

        int alphabeticalLength = getRandomLength(MIN_AZ_LENGTH, MAX_AZ_LENGTH);
        int numericalLength = getRandomLength(MIN_NUM_LENGTH, MAX_NUM_LENGTH);

        return randomAZString(alphabeticalLength) + randomNumbersString(numericalLength) + "@correo.com";
    }

}
