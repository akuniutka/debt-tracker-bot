package dev.akuniutka.chatbot.core;

import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatTest {
    private static final String USER_ID_AND_CHAT_STATE_ARE_NULL = "Setting user id and chat state to null";
    private static final String USER_ID_IS_NULL = "Setting user id to null";
    private static final String CHAT_STATE_IS_NULL = "Setting chat state to null";
    private static final String CHAT_STATE_IS_NOT_SET = "Calling to chat while chat state is not set";
    private final LoggerListener logged = new LoggerListener();

    @BeforeEach
    public void setUp() {
        logged.attach(Chat.class);
    }

    @AfterEach
    public void tearDown() {
        logged.detach();
    }

    @Test
    void testChatWhenNoArgs() {
        assertDoesNotThrow(() -> new Chat());
    }

    @Test
    void testChatWhenBothUserIdAndInitialStateAreNull() {
        String expectedDebugMessage = getStackTrace();
        new Chat(null, null);
        assertEquals(2, logged.getSize());
        assertEquals(Level.WARN, logged.getLevel(0));
        assertEquals(USER_ID_AND_CHAT_STATE_ARE_NULL, logged.getMessage(0));
        assertEquals(Level.DEBUG, logged.getLevel(1));
        assertEquals(expectedDebugMessage, logged.getMessage(1));
    }

    @Test
    void testChatWhenUserIdIsNull() {
        ChatState chatState = mock(ChatState.class);
        String expectedDebugMessage = getStackTrace();
        new Chat(null, chatState);
        assertEquals(2, logged.getSize());
        assertEquals(Level.WARN, logged.getLevel(0));
        assertEquals(USER_ID_IS_NULL, logged.getMessage(0));
        assertEquals(Level.DEBUG, logged.getLevel(1));
        assertEquals(expectedDebugMessage, logged.getMessage(1));
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testChatWhenInitialStateIsNull() {
        String expectedDebugMessage = getStackTrace();
        new Chat(1L, null);
        assertEquals(2, logged.getSize());
        assertEquals(Level.WARN, logged.getLevel(0));
        assertEquals(CHAT_STATE_IS_NULL, logged.getMessage(0));
        assertEquals(Level.DEBUG, logged.getLevel(1));
        assertEquals(expectedDebugMessage, logged.getMessage(1));
    }

    @Test
    void testChatWhenNeitherUserIdNorInitialStateAreNull() {
        ChatState chatState = mock(ChatState.class);
        new Chat(1L, chatState);
        assertEquals(0, logged.getSize());
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testSetUserIdWhenUserIdIsNull() {
        ChatState chatState = mock(ChatState.class);
        Chat chat = new Chat(1L, chatState);
        String expectedDebugMessage = getStackTrace();
        chat.setUserId(null);
        assertEquals(2, logged.getSize());
        assertEquals(Level.WARN, logged.getLevel(0));
        assertEquals(USER_ID_IS_NULL, logged.getMessage(0));
        assertEquals(Level.DEBUG, logged.getLevel(1));
        assertEquals(expectedDebugMessage, logged.getMessage(1));
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testSetUserIdWhenUserIdIsNotNull() {
        ChatState chatState = mock(ChatState.class);
        Chat chat = new Chat(1L, chatState);
        chat.setUserId(2L);
        assertEquals(0, logged.getSize());
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testGetUserIdWhenNotSet() {
        Chat chat = new Chat();
        assertNull(chat.getUserId());
        assertEquals(0, logged.getSize());
    }

    @Test
    void testGetUserIdWhenSetByConstructor() {
        ChatState chatState = mock(ChatState.class);
        Chat chat = new Chat(1L, chatState);
        assertEquals(1L, chat.getUserId());
        assertEquals(0, logged.getSize());
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testGetUserIdWhenSetBySetter() {
        ChatState chatState = mock(ChatState.class);
        Chat chat = new Chat(1L, chatState);
        chat.setUserId(2L);
        assertEquals(2L, chat.getUserId());
        assertEquals(0, logged.getSize());
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testSetStateWhenStateIsNull() {
        ChatState chatState = mock(ChatState.class);
        Chat chat = new Chat(1L, chatState);
        String expectedDebugMessage = getStackTrace();
        chat.setState(null);
        assertEquals(2, logged.getSize());
        assertEquals(Level.WARN, logged.getLevel(0));
        assertEquals(CHAT_STATE_IS_NULL, logged.getMessage(0));
        assertEquals(Level.DEBUG, logged.getLevel(1));
        assertEquals(expectedDebugMessage, logged.getMessage(1));
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testSetStateWhenStateIsNotNull() {
        ChatState chatState = mock(ChatState.class);
        ChatState newChatState = mock(ChatState.class);
        Chat chat = new Chat(1L, chatState);
        chat.setState(newChatState);
        assertEquals(0, logged.getSize());
        verifyNoMoreInteractions(ignoreStubs(chatState));
        verifyNoMoreInteractions(ignoreStubs(newChatState));
    }

    @Test
    void testGetStateWhenNotSet() {
        Chat chat = new Chat();
        assertNull(chat.getState());
        assertEquals(0, logged.getSize());
    }

    @Test
    void testGetStateWhenSetByConstructor() {
        ChatState chatState = mock(ChatState.class);
        Chat chat = new Chat(1L, chatState);
        assertEquals(chatState, chat.getState());
        assertEquals(0, logged.getSize());
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testGetStateWhenSetBySetter() {
        ChatState chatState = mock(ChatState.class);
        ChatState newChatState = mock(ChatState.class);
        Chat chat = new Chat(1L, chatState);
        chat.setState(newChatState);
        assertEquals(newChatState, chat.getState());
        assertEquals(0, logged.getSize());
        verifyNoMoreInteractions(ignoreStubs(chatState));
        verifyNoMoreInteractions(ignoreStubs(newChatState));
    }

    @Test
    void testGetReplyToMessageWhenChatStateIsNotSet() {
        String message = "Hello";
        Chat chat = new Chat();
        String expectedDebugMessage = null;
        Exception e = null;
        try {
            expectedDebugMessage = getStackTrace();
            chat.getReplyToMessage(message);
        } catch (RuntimeException ex) {
            e = ex;
        }
        assertNotNull(e);
        assertEquals(CHAT_STATE_IS_NOT_SET, e.getMessage());
        assertEquals(2, logged.getSize());
        assertEquals(Level.ERROR, logged.getLevel(0));
        assertEquals(CHAT_STATE_IS_NOT_SET, logged.getMessage(0));
        assertEquals(Level.DEBUG, logged.getLevel(1));
        assertEquals(expectedDebugMessage, logged.getMessage(1));
    }

    @Test
    void testGetReplyToMessageWhenChatStateIsSet() {
        String message = "Hello";
        List<String> expected = Arrays.asList("Hi!", "Nice to see you!");
        ChatState chatState = mock(ChatState.class);
        Chat chat = new Chat(1L, chatState);
        doNothing().when(chatState).processMessage(chat, message);
        when(chatState.getReply()).thenReturn(new ArrayList<>(expected));
        List<String> actual = chat.getReplyToMessage(message);
        assertEquals(expected, actual);
        assertEquals(0, logged.getSize());
        verify(chatState).processMessage(chat, message);
        verify(chatState).getReply();
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    private String getStackTrace() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String expected = stackTrace[2].toString();
        int lineNumber = stackTrace[2].getLineNumber();
        if (lineNumber > 0) {
            expected = expected.replaceAll(lineNumber + "\\)$", (lineNumber + 1) + ")");
        }
        return "at " + expected + "\nat " + Stream.of(stackTrace)
                .skip(3)
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\nat "));
    }
}