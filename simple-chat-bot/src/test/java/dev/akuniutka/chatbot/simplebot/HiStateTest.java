package dev.akuniutka.chatbot.simplebot;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.chatbot.core.ChatState;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HiStateTest {
    @Test
    void testProcessMessageWhenHello() {
        ArgumentCaptor<ChatState> stateCaptor = ArgumentCaptor.forClass(ChatState.class);
        Chat chat = mock(Chat.class);
        doNothing().when(chat).setState(stateCaptor.capture());
        ChatState chatState = new HiState();
        chatState.processMessage(chat, "Hello");
        assertTrue(stateCaptor.getValue() instanceof HelloState);
        verifyNoMoreInteractions(ignoreStubs(chat));
    }

    @Test
    void testProcessMessageWhenHi() {
        ArgumentCaptor<ChatState> stateCaptor = ArgumentCaptor.forClass(ChatState.class);
        Chat chat = mock(Chat.class);
        doNothing().when(chat).setState(stateCaptor.capture());
        ChatState chatState = new HiState();
        chatState.processMessage(chat, "Hi");
        assertTrue(stateCaptor.getValue() instanceof HiState);
        verifyNoMoreInteractions(ignoreStubs(chat));
    }

    @Test
    void testProcessMessageWhenNeitherHelloNorHi() {
        ArgumentCaptor<ChatState> stateCaptor = ArgumentCaptor.forClass(ChatState.class);
        Chat chat = mock(Chat.class);
        doNothing().when(chat).setState(stateCaptor.capture());
        ChatState chatState = new HiState();
        chatState.processMessage(chat, "OK");
        assertTrue(stateCaptor.getValue() instanceof AskToRephraseState);
        verifyNoMoreInteractions(ignoreStubs(chat));
    }

    @Test
    void testGetReply() {
        Chat chat = mock(Chat.class);
        List<String> expected = Arrays.asList("Hi!", "Nice to see you!");
        ChatState chatState = new HiState();
        List<String> actual = chatState.getReply(chat);
        assertEquals(expected, actual);
        verifyNoMoreInteractions(ignoreStubs(chat));
    }
}