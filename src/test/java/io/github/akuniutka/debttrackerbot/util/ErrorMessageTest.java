package io.github.akuniutka.debttrackerbot.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ErrorMessageTest {
    @Test
    void testErrorMessage() {
        assertDoesNotThrow(ErrorMessage::new);
    }
}