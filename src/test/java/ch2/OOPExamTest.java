package ch2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OOPExamTest {

    @Test
    void addTest() {
        final CalculateService calculateService = new CalculateService();
        final int result = calculateService.sum('+', 1, 1);

        Assertions.assertEquals(result, 2);
    }

    @Test
    void subTest() {
        final CalculateService calculateService = new CalculateService();
        final int result = calculateService.sum('-', 4, 1);

        Assertions.assertEquals(result, 3);
    }

    @Test
    void multiTest() {
        final CalculateService calculateService = new CalculateService();
        final int result = calculateService.sum('*', 4, 2);

        Assertions.assertEquals(result, 8);
    }

    @Test
    void divTest() {
        final CalculateService calculateService = new CalculateService();
        final int result = calculateService.sum('/', 8, 2);

        Assertions.assertEquals(result, 4);
    }
}