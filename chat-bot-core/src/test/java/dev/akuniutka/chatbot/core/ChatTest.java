package dev.akuniutka.chatbot.core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatTest {
    @Test
    void testChatWhenNoArgs() {
        assertDoesNotThrow(() -> new Chat());
    }

    @Test
    void testChatWhenInitialStateIsNull() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Chat(null));
        assertEquals("initial chat state is null", e.getMessage());
    }

    @Test
    void testChatWhenInitialStateIsNotNull() {
        ChatState chatState = mock(ChatState.class);
        assertDoesNotThrow(() -> new Chat(chatState));
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testSetStateWhenStateIsNull() {
        ChatState chatState = mock(ChatState.class);
        Chat chat = new Chat(chatState);
        Exception e = assertThrows(IllegalArgumentException.class, () -> chat.setState(null));
        assertEquals("chat state is null", e.getMessage());
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testSetStateWhenStateIsNotNull() {
        ChatState chatState = mock(ChatState.class);
        ChatState newChatState = mock(ChatState.class);
        Chat chat = new Chat(chatState);
        assertDoesNotThrow(() -> chat.setState(newChatState));
        verifyNoMoreInteractions(ignoreStubs(chatState));
        verifyNoMoreInteractions(ignoreStubs(newChatState));
    }

    @Test
    void testGetState() {
        ChatState chatState = mock(ChatState.class);
        ChatState newChatState = mock(ChatState.class);
        Chat chat = new Chat(chatState);
        chat.setState(newChatState);
        assertEquals(newChatState, chat.getState());
        verifyNoMoreInteractions(ignoreStubs(chatState));
        verifyNoMoreInteractions(ignoreStubs(newChatState));
    }

    @Test
    void testGetReplyToMessage() {
        String message = "Hello";
        List<String> expected = Arrays.asList("Hi!", "Nice to see you!");
        ChatState chatState = mock(ChatState.class);
        Chat chat = new Chat(chatState);
        doNothing().when(chatState).processMessage(chat, message);
        when(chatState.getReply()).thenReturn(new ArrayList<>(expected));
        List<String> actual = chat.getReplyToMessage(message);
        assertEquals(expected, actual);
        verify(chatState).processMessage(chat, message);
        verify(chatState).getReply();
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }
}