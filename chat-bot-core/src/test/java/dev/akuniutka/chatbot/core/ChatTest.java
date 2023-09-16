package dev.akuniutka.chatbot.core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatTest {
    private final static long ID = 123456789L;

    @Test
    void testChatWhenNoArgs() {
        assertDoesNotThrow(() -> new Chat());
    }

    @Test
    void testChatWhenUserIdIsNull() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Chat(null, null));
        assertEquals("id is null", e.getMessage());
    }

    @Test
    void testChatWhenInitialStateIsNull() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Chat(ID, null));
        assertEquals("initial chat state is null", e.getMessage());
    }

    @Test
    void testChatWhenBothUserIdAndInitialStateAreNotNull() {
        ChatState chatState = mock(ChatState.class);
        assertDoesNotThrow(() -> new Chat(ID, chatState));
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testGetUserId() {
        ChatState chatState = mock(ChatState.class);
        Chat chat = new Chat(ID, chatState);
        assertEquals(ID, chat.getUserId());
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testSetStateWhenStateIsNull() {
        ChatState chatState = mock(ChatState.class);
        Chat chat = new Chat(ID, chatState);
        Exception e = assertThrows(IllegalArgumentException.class, () -> chat.setState(null));
        assertEquals("chat state is null", e.getMessage());
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }

    @Test
    void testSetStateWhenStateIsNotNull() {
        ChatState chatState = mock(ChatState.class);
        ChatState newChatState = mock(ChatState.class);
        Chat chat = new Chat(ID, chatState);
        assertDoesNotThrow(() -> chat.setState(newChatState));
        verifyNoMoreInteractions(ignoreStubs(chatState));
        verifyNoMoreInteractions(ignoreStubs(newChatState));
    }

    @Test
    void testGetState() {
        ChatState chatState = mock(ChatState.class);
        ChatState newChatState = mock(ChatState.class);
        Chat chat = new Chat(ID, chatState);
        chat.setState(newChatState);
        assertEquals(newChatState, chat.getState());
        verifyNoMoreInteractions(ignoreStubs(chatState));
        verifyNoMoreInteractions(ignoreStubs(newChatState));
    }

    @Test
    void testGetReplyToMessage() {
        String message = "Hello";
        List<String> reply = Arrays.asList("Hi!", "Nice to see you!");
        List<String> possibleAnswers = Arrays.asList("Thanks!", "Bye!");
        ChatState chatState = mock(ChatState.class);
        Chat chat = new Chat(ID, chatState);
        doNothing().when(chatState).processMessage(chat, message);
        when(chatState.getReply()).thenReturn(new ArrayList<>(reply));
        when(chatState.getPossibleAnswers()).thenReturn(new ArrayList<>(possibleAnswers));
        ChatReply chatReply = chat.getReplyToMessage(message);
        assertEquals(reply, chatReply.getReply());
        assertEquals(possibleAnswers, chatReply.getPossibleAnswers());
        verify(chatState).processMessage(chat, message);
        verify(chatState).getReply();
        verify(chatState).getPossibleAnswers();
        verifyNoMoreInteractions(ignoreStubs(chatState));
    }
}