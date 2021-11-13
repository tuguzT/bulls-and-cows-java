package io.github.tuguzt.bullsandcows;

import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Class contains utility functions for the game.
 */
public class Utils {
    private static final Random random = new Random();

    /**
     * Generates the 4-digit secret number (digits are unique in this number).
     *
     * @return 4-digit secret number with unique digits
     */
    public static int generateSecretNumber() {
        var digits = new int[4];
        digits[0] = randomFromRange(1, 10);

        for (var i = 1; i < digits.length;) {
            final var digit = randomFromRange(0, 10);
            var contains = IntStream.of(digits).anyMatch(x -> x == digit);
            if (!contains) {
                digits[i] = digit;
                i++;
            }
        }

        return fromDigits(digits);
    }

    /**
     * Returns the count of "cows" of the user provided number.
     *
     * @param secretNumber secret number that was generated earlier
     * @param userNumber number provided by the user
     * @return count of "cows"
     */
    public static int countCows(int secretNumber, int userNumber) {
        final var secretNumberDigits = toDigits(secretNumber);
        final var userNumberDigits = toDigits(userNumber);

        var cows = 0;
        for (var i = 0; i < 4; i++) {
            final var secretDigit = secretNumberDigits[i];
            final var userDigit = userNumberDigits[i];
            var contains = IntStream.of(userNumberDigits).anyMatch(x -> x == secretDigit);
            if (contains && userDigit != secretDigit) {
                cows++;
            }
        }
        return cows;
    }

    /**
     * Returns the count of "bulls" of the user provided number.
     *
     * @param secretNumber secret number that was generated earlier
     * @param userNumber number provided by the user
     * @return count of "bulls"
     */
    public static int countBulls(int secretNumber, int userNumber) {
        final var secretNumberDigits = toDigits(secretNumber);
        final var userNumberDigits = toDigits(userNumber);

        var bulls = 0;
        for (var i = 0; i < 4; i++) {
            final var secretDigit = secretNumberDigits[i];
            final var userDigit = userNumberDigits[i];
            if (secretDigit == userDigit) {
                bulls++;
            }
        }
        return bulls;
    }

    /**
     * Generates random number from the range between minimum and maximum values.
     *
     * @param min minimum value (inclusive)
     * @param max maximum value (exclusive)
     * @return random number from the range
     */
    public static int randomFromRange(int min, int max) {
        assert min < max;
        return random.nextInt(max - min) + min;
    }

    /**
     * Converts a number into an array of digits.
     *
     * @param number 4-digit number that will be converted
     * @return 4 digits of number in 4-dimensional array
     */
    public static int[] toDigits(int number) {
        return new int[] { number / 1_000, (number / 100) % 10, (number / 10) % 10, number % 10, };
    }

    /**
     * Converts an array of digits into a number.
     *
     * @param digits array of digits that will be converted
     * @return number which have 4 digits from digits
     */
    public static int fromDigits(int[] digits) {
        Objects.requireNonNull(digits);
        assert digits.length == 4;
        return digits[0] * 1_000 + digits[1] * 100 + digits[2] * 10 + digits[3];
    }
}
