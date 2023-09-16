package dev.akuniutka.debttracker.entity;

import dev.akuniutka.chatbot.core.Chat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {
    private final static long ID = 123456789L;

//    @Test
//    void testGetChatOrCreateNew$IdIsNotNull() {
//        assertDoesNotThrow(() -> new Chat(ID));
//    }

    @Test
    void testGetChatOrCreateNew$IdIsNull() {
        Exception exception = assertThrows(
                IllegalArgumentException.class, () -> new Chat(null, null)
        );
        String expected = "id is null";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }
}