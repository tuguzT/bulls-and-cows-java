package io.github.tuguzt.bullsandcows;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static io.github.tuguzt.bullsandcows.Utils.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestsUtils {
    @Test
    public void testRandomFromRange() {
        var random = randomFromRange(0, 1);
        assertEquals(random, 0);

        random = randomFromRange(100, 1000);
        System.out.println(random);
        assertTrue(random < 1000);

        random = randomFromRange(10, 13);
        System.out.println(random);
        assertFalse(random < 9);
    }

    @Test
    public void testFromDigits() {
        var result = fromDigits(new int[]{1, 0, 0, 0,});
        assertEquals(1000, result);

        result = fromDigits(new int[]{3, 6, 1, 9,});
        assertNotEquals(3742, result);
    }

    @Test
    public void testToDigits() {
        var result = toDigits(1000);
        assertArrayEquals(new int[]{1, 0, 0, 0,}, result);

        result = toDigits(3742);
        int[] finalResult = result;
        assertThrows(AssertionFailedError.class, () -> assertArrayEquals(new int[]{}, finalResult));
    }

    @Test
    public void testBulls() {
        assertEquals(countBulls(1234, 1234), 4);
        assertEquals(countBulls(3458, 3518), 2);
        assertNotEquals(countBulls(9876, 8273), 0);
        assertNotEquals(countBulls(3497, 3487), 4);
    }

    @Test
    public void testCows() {
        assertEquals(countCows(1234, 5678), 0);
        assertEquals(countCows(9358, 5893), 4);
        assertNotEquals(countCows(1285, 9371), 2);
        assertNotEquals(countCows(3950, 5092), 4);
    }
}
