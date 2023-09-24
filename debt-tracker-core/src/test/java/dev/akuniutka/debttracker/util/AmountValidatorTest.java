package dev.akuniutka.debttracker.util;

import dev.akuniutka.debttracker.exception.WrongAmountException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static dev.akuniutka.debttracker.util.ErrorMessage.*;

class AmountValidatorTest {
    @Test
    void testAmountValidator() {
        assertDoesNotThrow(AmountValidatorTest::new);
    }

    @Test
    void testAssertAmountWhenAmountIsNull() {
        Exception e = assertThrows(WrongAmountException.class, () -> AmountValidator.assertAmount(null));
        assertEquals(AMOUNT_IS_NULL, e.getMessage());
    }

    @Test
    void testAssertAmountWhenAmountIsNegative() {
        BigDecimal amount = BigDecimal.ONE.negate();
        Exception e = assertThrows(WrongAmountException.class, () -> AmountValidator.assertAmount(amount));
        assertEquals(AMOUNT_IS_NEGATIVE, e.getMessage());
    }

    @Test
    void testAssertAmountWhenAmountIsZero() {
        BigDecimal amount = BigDecimal.ZERO;
        Exception e = assertThrows(WrongAmountException.class, () -> AmountValidator.assertAmount(amount));
        assertEquals(AMOUNT_IS_ZERO, e.getMessage());
    }

    @Test
    void testAssertAmountWhenScaleIsGreaterThanTwoAndNonZeros() {
        BigDecimal amount = BigDecimal.ONE
                .setScale(3, RoundingMode.HALF_UP)
                .divide(BigDecimal.TEN, RoundingMode.HALF_UP)
                .divide(BigDecimal.TEN, RoundingMode.HALF_UP)
                .divide(BigDecimal.TEN, RoundingMode.HALF_UP);
        Exception e = assertThrows(WrongAmountException.class, () -> AmountValidator.assertAmount(amount));
        assertEquals(WRONG_MINOR_UNITS, e.getMessage());
    }

    @Test
    void testAssertAmountWhenScaleIsGreaterThanTwoButWithZeroes() {
        BigDecimal amount = BigDecimal.ONE
                .setScale(3, RoundingMode.HALF_UP)
                .divide(BigDecimal.TEN, RoundingMode.HALF_UP)
                .divide(BigDecimal.TEN, RoundingMode.HALF_UP);
        assertDoesNotThrow(() -> AmountValidator.assertAmount(amount));
    }

    @Test
    void testAssertAmountWhenAmountIsPositiveAndWithScaleNotGreaterThanTwo() {
        BigDecimal amount = BigDecimal.ONE;
        assertDoesNotThrow(() -> AmountValidator.assertAmount(amount));
    }
}