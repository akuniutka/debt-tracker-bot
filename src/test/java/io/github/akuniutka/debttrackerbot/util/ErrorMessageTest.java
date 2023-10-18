package io.github.akuniutka.debttrackerbot.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorMessageTest {
    @Test
    void testErrorMessage() {
        assertDoesNotThrow(ErrorMessage::new);
    }
}