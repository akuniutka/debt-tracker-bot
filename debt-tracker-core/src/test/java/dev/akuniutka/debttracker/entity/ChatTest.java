package dev.akuniutka.debttracker.entity;

import dev.akuniutka.debttracker.dao.Dao;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {
    private final static long ID = 123456789L;
    private final static Long NULL_ID = null;
    private final static ChatDao CHAT_DAO = new ChatDao();
    private final static ChatDao NULL_DAO = null;

    @Test
    void testGetChatByIdOrCreateNew$ChatDoesNotExist() {
        CHAT_DAO.clear();
        Chat chat = Chat.getChatByIdOrCreateNew(ID, CHAT_DAO);
        assertNotNull(chat);
        assertEquals(ID, chat.getId());
        assertSame(chat, CHAT_DAO.chat);
    }

    @Test
    void testGetChatByIdOrCreateNew$ChatAlreadyExists() {
        CHAT_DAO.clear();
        Chat expected = Chat.getChatByIdOrCreateNew(ID, CHAT_DAO);
        Chat actual = Chat.getChatByIdOrCreateNew(ID, CHAT_DAO);
        assertSame(expected, actual);
    }

    @Test
    void testGetChatByIdOrCreateNew$BothIdAndDaoAreNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Chat.getChatByIdOrCreateNew(NULL_ID, NULL_DAO)
        );
        String expected = "Id and DAO object are null";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testGetChatByIdOrCreateNew$IdIsNull() {
        Exception exception = assertThrows(
                IllegalArgumentException.class, () -> Chat.getChatByIdOrCreateNew(NULL_ID, CHAT_DAO)
        );
        String expected = "Id is null";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testGetChatByIdOrCreateNew$DaoIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Chat.getChatByIdOrCreateNew(ID, NULL_DAO)
        );
        String expected = "DAO object is null";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testGetId() throws NoSuchFieldException, IllegalAccessException {
        Chat chat = new Chat();
        assertNull(chat.getId());
        Field idField = chat.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(chat, ID);
        assertEquals(ID, chat.getId());
    }

    @Test
    void testEquals() throws NoSuchFieldException, IllegalAccessException {
        Chat chatA = new Chat();
        Chat chatB = new Chat();
        assertNotSame(chatA, chatB);
        Field idField = chatA.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(chatA, ID);
        idField.set(chatB, ID);
        assertEquals(chatA, chatB);
        chatB = chatA;
        assertEquals(chatA, chatB);
        assertNotEquals(chatA, null);
        assertNotEquals(chatA, new Object());
    }

    @Test
    void testHashCode() throws NoSuchFieldException, IllegalAccessException {
        Chat chatA = new Chat();
        Chat chatB = new Chat();
        assertNotSame(chatA, chatB);
        Field idField = chatA.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(chatA, ID);
        idField.set(chatB, ID);
        assertEquals(chatA.hashCode(), chatB.hashCode());
    }

    private static class ChatDao implements Dao<Chat> {
        Chat chat;

        @Override
        public Optional<Chat> get(Long id) {
            if (chat == null || !chat.getId().equals(id)) {
                return Optional.empty();
            }
            return Optional.of(chat);
        }

        @Override
        public Chat save(Chat chat) {
            this.chat = chat;
            return chat;
        }

        @Override
        public void delete(Chat chat) {
            if (this.chat != null && this.chat.equals(chat)) {
                this.chat = null;
            }
        }

        void clear() {
            chat = null;
        }
    }
}