package dev.akuniutka.debttracker.entity;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testGetId() throws NoSuchFieldException, IllegalAccessException {
        User user = new User();
        Long expected = 123456789L;
        assertNull(user.getId());
        Field idField = user.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(user, expected);
        assertEquals(expected, user.getId());
    }

    @Test
    void setId() throws NoSuchFieldException, IllegalAccessException {
        User user = new User();
        Long expected = 123456789L;
        Field idField = user.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        assertNull(idField.get(user));
        user.setId(expected);
        assertEquals(expected, idField.get(user));
    }

    @Test
    void testEquals() {
        User userA = new User();
        User userB = new User();
        long id = 123456789L;
        assertNotSame(userA, userB);
        userA.setId(id);
        userB.setId(id);
        assertEquals(userA, userB);
    }

    @Test
    void testHashCode() {
    }
}