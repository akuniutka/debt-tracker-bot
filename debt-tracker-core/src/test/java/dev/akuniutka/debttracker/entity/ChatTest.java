package dev.akuniutka.debttracker.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {
    private final static long ID = 123456789L;
    private final static Long NULL_ID = null;

    @Test
    void testGetChatOrCreateNew$IdIsNotNull() {
        assertDoesNotThrow(() -> new Chat(ID));
    }

    @Test
    void testGetChatOrCreateNew$IdIsNull() {
        Exception exception = assertThrows(
                IllegalArgumentException.class, () -> new Chat(NULL_ID)
        );
        String expected = "Id is null";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testGetId() {
        Chat chat = new Chat(ID);
        assertEquals(ID, chat.getId());
    }

    @Test
    void testEquals() {
        Chat chatA = new Chat(ID);
        Chat chatB = new Chat(ID);
        assertNotSame(chatA, chatB);
        assertEquals(chatA, chatB);
        chatB = chatA;
        assertEquals(chatA, chatB);
        assertNotEquals(chatA, null);
        assertNotEquals(chatA, new Object());
    }

    @Test
    void testHashCode() {
        Chat chatA = new Chat(ID);
        Chat chatB = new Chat(ID);
        assertNotSame(chatA, chatB);
        assertEquals(chatA.hashCode(), chatB.hashCode());
    }
}