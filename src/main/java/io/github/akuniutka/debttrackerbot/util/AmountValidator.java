package io.github.akuniutka.debttrackerbot.util;

import io.github.akuniutka.debttrackerbot.exception.WrongAmountException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountValidator {
    private static final String AMOUNT_IS_NULL = "Setting the entry amount to null";
    private static final String AMOUNT_IS_NEGATIVE = "Setting the entry amount to a negative value";
    private static final String AMOUNT_IS_ZERO = "Setting the entry amount to zero";
    private static final String WRONG_MINOR_UNITS = "Setting the entry amount to a value with wrong minor units";

    public static void assertAmount(BigDecimal amount) {
        String message;
        if (amount == null) {
            message = AMOUNT_IS_NULL;
        } else if (amount.signum() == 0) {
            message = AMOUNT_IS_ZERO;
        } else if (amount.signum() < 0) {
            message = AMOUNT_IS_NEGATIVE;
        } else if (amount.setScale(2, RoundingMode.HALF_UP).compareTo(amount) != 0) {
            message = WRONG_MINOR_UNITS;
        } else {
            return;
        }
        throw new WrongAmountException(message);
    }
}
