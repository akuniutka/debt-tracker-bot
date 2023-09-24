package dev.akuniutka.debttracker.util;

import dev.akuniutka.debttracker.exception.WrongAmountException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountValidator {
    public static void assertAmount(BigDecimal amount) {
        String message;
        if (amount == null) {
            message = ErrorMessage.AMOUNT_IS_NULL;
        } else if (amount.signum() == 0) {
            message = ErrorMessage.AMOUNT_IS_ZERO;
        } else if (amount.signum() < 0) {
            message = ErrorMessage.AMOUNT_IS_NEGATIVE;
        } else if (amount.setScale(2, RoundingMode.HALF_UP).compareTo(amount) != 0) {
            message = ErrorMessage.WRONG_MINOR_UNITS;
        } else {
            return;
        }
        throw new WrongAmountException(message);
    }
}
