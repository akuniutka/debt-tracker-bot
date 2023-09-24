package dev.akuniutka.debttracker.entity;

import dev.akuniutka.chatbot.core.ChatState;
import dev.akuniutka.debttracker.exception.WrongAmountException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static dev.akuniutka.debttracker.util.ErrorMessage.*;

class DebtTrackerChatTest {
    @Test
    void testDebtTrackerChatWithNoArgs() {
        assertDoesNotThrow(() -> new DebtTrackerChat());
    }

    @Test
    void testDebtTrackerChatWhenUserIdIsNull() {
        ChatState initialChatState = mock(ChatState.class);
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new DebtTrackerChat(null, initialChatState)
        );
        assertEquals(USER_ID_IS_NULL, e.getMessage());
        verifyNoMoreInteractions(ignoreStubs(initialChatState));
    }

    @Test
    void testDebtTrackerChatWhenInitialStateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new DebtTrackerChat(1L, null));
    }

    @Test
    void testDebtTrackerChatWhenNeitherUserIdNorInitialStateAreNull() {
        ChatState initialChatState = mock(ChatState.class);
        assertDoesNotThrow(() -> new DebtTrackerChat(1L, initialChatState));
        verifyNoMoreInteractions(ignoreStubs(initialChatState));
    }

    @Test
    void testGetUserId() {
        ChatState initialChatState = mock(ChatState.class);
        DebtTrackerChat chat = new DebtTrackerChat(1L, initialChatState);
        assertEquals(1L, chat.getUserId());
        verifyNoMoreInteractions(ignoreStubs(initialChatState));
    }

    @Test
    void testSetCachedEntryTypeWhenEntryTypeIsNull() {
        DebtTrackerChat chat = new DebtTrackerChat();
        Exception e = assertThrows(IllegalArgumentException.class, () -> chat.setCachedEntryType(null));
        assertEquals(ENTRY_TYPE_IS_NULL, e.getMessage());
    }

    @Test
    void testSetCachedEntryTypeWhenEntryTypeIsNotNull() {
        DebtTrackerChat chat = new DebtTrackerChat();
        assertDoesNotThrow(() -> chat.setCachedEntryType(EntryType.LENT));
    }

    @Test
    void testGetCachedEntryType() {
        DebtTrackerChat chat = new DebtTrackerChat();
        chat.setCachedEntryType(EntryType.LENT);
        assertEquals(EntryType.LENT, chat.getCachedEntryType());
    }

    @Test
    void testSetCachedAmountWhenAmountIsNegative() {
        BigDecimal amount = BigDecimal.ONE.negate();
        DebtTrackerChat chat = new DebtTrackerChat();
        Exception e = assertThrows(WrongAmountException.class, () -> chat.setCachedAmount(amount));
        assertEquals(AMOUNT_IS_NEGATIVE, e.getMessage());
    }

    @Test
    void testSetCachedAmountWhenAmountIsZero() {
        BigDecimal amount = BigDecimal.ZERO;
        DebtTrackerChat chat = new DebtTrackerChat();
        Exception e = assertThrows(WrongAmountException.class, () -> chat.setCachedAmount(amount));
        assertEquals(AMOUNT_IS_ZERO, e.getMessage());
    }

    @Test
    void testSetCachedAmountWhenScaleIsGreaterThanTwoAndNonZeros() {
        BigDecimal amount = BigDecimal.ONE
                .setScale(3, RoundingMode.HALF_UP)
                .divide(BigDecimal.TEN, RoundingMode.HALF_UP)
                .divide(BigDecimal.TEN, RoundingMode.HALF_UP)
                .divide(BigDecimal.TEN, RoundingMode.HALF_UP);
        DebtTrackerChat chat = new DebtTrackerChat();
        Exception e = assertThrows(WrongAmountException.class, () -> chat.setCachedAmount(amount));
        assertEquals(WRONG_MINOR_UNITS, e.getMessage());
    }

    @Test
    void testSetCachedAmountWhenAmountIsNull() {
        DebtTrackerChat chat = new DebtTrackerChat();
        assertDoesNotThrow(() -> chat.setCachedAmount(null));
    }

    @Test
    void testSetCachedAmountWhenScaleIsGreaterThanTwoButWithZeros() {
        BigDecimal amount = BigDecimal.ONE
                .setScale(3, RoundingMode.HALF_UP)
                .divide(BigDecimal.TEN, RoundingMode.HALF_UP)
                .divide(BigDecimal.TEN, RoundingMode.HALF_UP);
        DebtTrackerChat chat = new DebtTrackerChat();
        assertDoesNotThrow(() -> chat.setCachedAmount(amount));
    }

    @Test
    void testSetCachedAmountWhenAmountIsPositiveAndScaleIsNotGreaterThanTwo() {
        BigDecimal amount = BigDecimal.ONE;
        DebtTrackerChat chat = new DebtTrackerChat();
        assertDoesNotThrow(() -> chat.setCachedAmount(amount));
    }

    @Test
    void testGetCachedAmount() {
        BigDecimal amount = BigDecimal.ONE;
        DebtTrackerChat chat = new DebtTrackerChat();
        chat.setCachedAmount(amount);
        assertEquals(amount.setScale(2, RoundingMode.HALF_UP), chat.getCachedAmount());
    }

    @Test
    void testClearCache() {
        DebtTrackerChat chat = new DebtTrackerChat();
        chat.setCachedEntryType(EntryType.BORROWED);
        chat.setCachedAmount(BigDecimal.ONE);
        chat.clearCache();
        assertNull(chat.getCachedEntryType());
        assertNull(chat.getCachedAmount());
    }
}