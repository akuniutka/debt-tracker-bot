package io.github.akuniutka.debttrackerbot.entity;

import io.github.akuniutka.debttrackerbot.exception.WrongAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.github.akuniutka.debttrackerbot.util.Amount.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Entry Draft Entity")
class EntryDraftTest {
    @Test
    @DisplayName("Instantiate an entry draft with no arguments")
    void testEntryDraftWithNoArgs() {
        assertDoesNotThrow(() -> new EntryDraft());
    }

    @Test
    @DisplayName("Fail to instantiate an entry draft with the null user id")
    void testEntryDraftWhenUserIdIsNull() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new EntryDraft(null));
        assertEquals("Setting the user id to null", e.getMessage());
    }

    @Test
    @DisplayName("Instantiate an entry draft with a non-null user id")
    void testEntryDraftWhenUserIdIsNotNull() {
        assertDoesNotThrow(() -> new EntryDraft(1L));
    }

    @Test
    @DisplayName("Get the entry type")
    void testGetType() {
        EntryDraft draft = new EntryDraft();
        draft.setType(EntryType.BORROWED);
        assertEquals(EntryType.BORROWED, draft.getType());
    }

    @Test
    @DisplayName("Fail to set the entry type to null")
    void testSetTypeWhenTypeIsNull() {
        EntryDraft draft = new EntryDraft();
        assertNull(draft.getType());
        Exception e = assertThrows(IllegalArgumentException.class, () -> draft.setType(null));
        assertEquals("Setting the entry type to null", e.getMessage());
    }

    @Test
    @DisplayName("Set the entry type to a non-null value")
    void testSetTypeWhenTypeIsNotNull() {
        EntryDraft draft = new EntryDraft();
        assertNull(draft.getType());
        assertDoesNotThrow(() -> draft.setType(EntryType.BORROWED));
    }

    @Test
    @DisplayName("Get the entry amount")
    void testGetAmount() {
        EntryDraft draft = new EntryDraft();
        draft.setAmount(TEN);
        assertEquals(FORMATTED_TEN, draft.getAmount());
    }

    @Test
    @DisplayName("Fail to set the entry amount to null")
    void testSetAmountWhenAmountIsNull() {
        EntryDraft draft = new EntryDraft();
        assertNull(draft.getAmount());
        Exception e = assertThrows(WrongAmountException.class, () -> draft.setAmount(null));
        assertEquals("Setting the entry amount to null", e.getMessage());
    }

    @Test
    @DisplayName("Fail to set the entry amount to a negative value")
    void testSetAmountWhenAmountIsNegative() {
        EntryDraft draft = new EntryDraft();
        assertNull(draft.getAmount());
        Exception e = assertThrows(WrongAmountException.class, () -> draft.setAmount(MINUS_TEN));
        assertEquals("Setting the entry amount to a negative value", e.getMessage());
    }

    @Test
    @DisplayName("Fail to set the entry amount to zero")
    void testSetAmountWhenAmountIsZero() {
        EntryDraft draft = new EntryDraft();
        assertNull(draft.getAmount());
        Exception e = assertThrows(WrongAmountException.class, () -> draft.setAmount(ZERO));
        assertEquals("Setting the entry amount to zero", e.getMessage());
    }

    @Test
    @DisplayName("Fail to set the entry amount to a value with wrong minor units")
    void testSetAmountWhenScaleIsGreaterThanTwoAndNonZeros() {
        EntryDraft draft = new EntryDraft();
        assertNull(draft.getAmount());
        Exception e = assertThrows(WrongAmountException.class, () -> draft.setAmount(ONE_THOUSANDTH));
        assertEquals("Setting the entry amount to a value with wrong minor units", e.getMessage());
    }

    @Test
    @DisplayName("Set the entry amount to a value with insignificant minor units")
    void testSetAmountWhenScaleIsGreaterThanTwoButZeros() {
        EntryDraft draft = new EntryDraft();
        assertDoesNotThrow(() -> draft.setAmount(TEN_THOUSANDTHS));
        assertEquals(FORMATTED_TEN_THOUSANDTHS, draft.getAmount());
    }

    @Test
    @DisplayName("Set the entry amount to a positive value")
    void testSetAmountWhenAmountIsPositive() {
        EntryDraft draft = new EntryDraft();
        assertDoesNotThrow(() -> draft.setAmount(TEN));
        assertEquals(FORMATTED_TEN, draft.getAmount());
    }
}