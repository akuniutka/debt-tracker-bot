package dev.akuniutka.debttracker.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static dev.akuniutka.debttracker.util.ErrorMessage.*;

class WrongAmountExceptionTest {
    @Test
    void testWrongAmountException() {
        Exception e = assertThrows(WrongAmountException.class, () -> {
            throw new WrongAmountException(AMOUNT_IS_NULL);
        });
        assertEquals(AMOUNT_IS_NULL, e.getMessage());
    }
}