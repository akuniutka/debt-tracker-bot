package io.github.akuniutka.debttrackerbot.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Wrong Amount Exception")
class WrongAmountExceptionTest {
    private static final String AMOUNT_IS_NULL = "Setting the entry amount to null";

    @Test
    @DisplayName("Throwing the exception with a message for user")
    void testWrongAmountException() {
        Exception e = assertThrows(WrongAmountException.class, () -> {
            throw new WrongAmountException(AMOUNT_IS_NULL);
        });
        assertEquals(AMOUNT_IS_NULL, e.getMessage());
    }
}