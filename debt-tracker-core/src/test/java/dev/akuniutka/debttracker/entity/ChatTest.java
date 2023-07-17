package dev.akuniutka.debttracker.entity;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {

    @Test
    void testGetId() throws NoSuchFieldException, IllegalAccessException {
        Chat chat = new Chat();
        Long expected = 123456789L;
        assertNull(chat.getId());
        Field idField = chat.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(chat, expected);
        assertEquals(expected, chat.getId());
    }

    @Test
    void setId() throws NoSuchFieldException, IllegalAccessException {
        Chat chat = new Chat();
        Long expected = 123456789L;
        Field idField = chat.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        assertNull(idField.get(chat));
        chat.setId(expected);
        assertEquals(expected, idField.get(chat));
    }

    @Test
    void testEquals() {
        Chat chatA = new Chat();
        Chat chatB = new Chat();
        long id = 123456789L;
        assertNotSame(chatA, chatB);
        chatA.setId(id);
        chatB.setId(id);
        assertEquals(chatA, chatB);
    }

    @Test
    void testHashCode() {
    }
}