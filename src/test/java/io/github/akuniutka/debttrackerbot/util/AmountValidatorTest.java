package io.github.akuniutka.debttrackerbot.util;

import io.github.akuniutka.debttrackerbot.exception.WrongAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.github.akuniutka.debttrackerbot.util.Amount.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Amount Validator")
class AmountValidatorTest {
    private static final String AMOUNT_IS_NULL = "Setting the entry amount to null";
    private static final String AMOUNT_IS_NEGATIVE = "Setting the entry amount to a negative value";
    private static final String AMOUNT_IS_ZERO = "Setting the entry amount to zero";
    private static final String WRONG_MINOR_UNITS = "Setting the entry amount to a value with wrong minor units";

    @Test
    @DisplayName("Instantiate an amount validator")
    void testAmountValidator() {
        assertDoesNotThrow(AmountValidatorTest::new);
    }

    @Test
    @DisplayName("Fail when asserting null as an amount")
    void testAssertAmountWhenAmountIsNull() {
        Exception e = assertThrows(WrongAmountException.class, () -> AmountValidator.assertAmount(null));
        assertEquals(AMOUNT_IS_NULL, e.getMessage());
    }

    @Test
    @DisplayName("Fail when asserting a negative amount")
    void testAssertAmountWhenAmountIsNegative() {
        Exception e = assertThrows(WrongAmountException.class, () -> AmountValidator.assertAmount(MINUS_TEN));
        assertEquals(AMOUNT_IS_NEGATIVE, e.getMessage());
    }

    @Test
    @DisplayName("Fail when asserting zero")
    void testAssertAmountWhenAmountIsZero() {
        Exception e = assertThrows(WrongAmountException.class, () -> AmountValidator.assertAmount(ZERO));
        assertEquals(AMOUNT_IS_ZERO, e.getMessage());
    }

    @Test
    @DisplayName("Fail when asserting an amount with wrong minor units")
    void testAssertAmountWhenScaleIsGreaterThanTwoAndNonZeros() {
        Exception e = assertThrows(WrongAmountException.class, () -> AmountValidator.assertAmount(ONE_THOUSANDTH));
        assertEquals(WRONG_MINOR_UNITS, e.getMessage());
    }

    @Test
    @DisplayName("Assert an amount with insignificant minor units")
    void testAssertAmountWhenScaleIsGreaterThanTwoButWithZeroes() {
        assertDoesNotThrow(() -> AmountValidator.assertAmount(TEN_THOUSANDTHS));
    }

    @Test
    @DisplayName("Assert a positive amount")
    void testAssertAmountWhenAmountIsPositiveAndWithScaleNotGreaterThanTwo() {
        assertDoesNotThrow(() -> AmountValidator.assertAmount(TEN));
    }
}