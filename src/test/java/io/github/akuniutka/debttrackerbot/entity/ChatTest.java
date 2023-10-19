package io.github.akuniutka.debttrackerbot.entity;

import io.github.akuniutka.debttrackerbot.script.ChatState;
import io.github.akuniutka.debttrackerbot.service.BotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Chat Entity")
class ChatTest {
    private static final String USER_ID_AND_STATE_ARE_NULL = "Setting the user id and the chat state to null";
    private static final String USER_ID_IS_NULL = "Setting the user id to null";
    private static final String STATE_IS_NULL = "Setting the chat state to null";
    private static final String BOT_SERVICE_IS_NULL = "Setting the bot service to null";
    private static final String STATE_IS_NOT_SET = "Calling to the chat while the chat state is not set";
    private static final String USER_ID_AND_BOT_SERVICE_ARE_NOT_SET =
            "Sending a message while the user id and the bot service are not set";
    private static final String USER_ID_IS_NOT_SET = "Sending a message while the user id is not set";
    private static final String BOT_SERVICE_IS_NOT_SET = "Sending a message while the bot service is not set";

    @Test
    @DisplayName("Instantiate a chat with no arguments")
    void testChatWithNoArguments() {
        assertDoesNotThrow(() -> new Chat());
    }

    @Test
    @DisplayName("Fail to instantiate a chat when both the user id and the state are null")
    void testChatWhenBothUserIdAndInitialStateAreNull() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Chat(null, null));
        assertEquals(USER_ID_AND_STATE_ARE_NULL, e.getMessage());
    }

    @Test
    @DisplayName("Fail to instantiate a chat when only the user id is null")
    void testChatWhenUserIdIsNullAndInitialStateIsNotNull() {
        ChatState state = mock(ChatState.class);
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Chat(null, state));
        assertEquals(USER_ID_IS_NULL, e.getMessage());
        verifyNoInteractions(state);
    }

    @Test
    @DisplayName("Fail to instantiate a chat when only the state is null")
    void testChatWhenUserIdIsNotNullAndInitialStateIsNull() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Chat(1L, null));
        assertEquals(STATE_IS_NULL, e.getMessage());
    }

    @Test
    @DisplayName("Instantiate a chat when the user id and the state are not null")
    void testChatWhenNeitherUserIdNorInitialStateIsNull() {
        ChatState state = mock(ChatState.class);
        assertDoesNotThrow(() -> new Chat(1L, state));
        verifyNoInteractions(state);
    }

    @Test
    @DisplayName("Get the user id")
    void testGetUserId() {
        ChatState state = mock(ChatState.class);
        Chat chat = new Chat(1L, state);
        Assertions.assertEquals(1L, chat.getUserId());
        verifyNoInteractions(state);
    }

    @Test
    @DisplayName("Fail to set the state to null")
    void testSetStateWhenStateIsNull() {
        Chat chat = new Chat();
        Exception e = assertThrows(IllegalArgumentException.class, () -> chat.setState(null));
        assertEquals(STATE_IS_NULL, e.getMessage());
    }

    @Test
    @DisplayName("Set the state to a non-null value")
    void testSetStateWhenStateIsNotNull() {
        Chat chat = new Chat();
        ChatState state = mock(ChatState.class);
        assertDoesNotThrow(() -> chat.setState(state));
        verifyNoInteractions(state);
    }

    @Test
    @DisplayName("Fail to set the bot service to null")
    void testSetBotServiceWhenBotServiceIsNull() {
        Chat chat = new Chat();
        Exception e = assertThrows(IllegalArgumentException.class, () -> chat.setBotService(null));
        assertEquals(BOT_SERVICE_IS_NULL, e.getMessage());
    }

    @Test
    @DisplayName("Set the bot service to a non-null value")
    void testSetBotServiceWhenBotServiceIsNotNull() {
        Chat chat = new Chat();
        BotService botService = mock(BotService.class);
        assertDoesNotThrow(() -> chat.setBotService(botService));
        verifyNoInteractions(botService);
    }

    @Test
    @DisplayName("Fail to process a user message when the state is not set")
    void testAddUserMessageWhenStateIsNotSet() {
        Chat chat = new Chat();
        Exception e = assertThrows(RuntimeException.class, () -> chat.addUserMessage("/start"));
        assertEquals(STATE_IS_NOT_SET, e.getMessage());
    }

    @Test
    @DisplayName("Pass a user message to the state")
    void testAddUserMessageWhenStateIsSet() {
        String command = "/start";
        ChatState state = mock(ChatState.class);
        Chat chat = new Chat(1L, state);
        doNothing().when(state).addUserMessage(chat, command);
        assertDoesNotThrow(() -> chat.addUserMessage(command));
        verify(state).addUserMessage(chat, command);
        verifyNoMoreInteractions(ignoreStubs(state));
    }

    @Test
    @DisplayName("Fail to send a message to user when both the user id and the bot service are not set")
    void testSendMessageToUserWhenBothUserIdAndBotServiceAreNotSet() {
        Chat chat = new Chat();
        Exception e = assertThrows(RuntimeException.class, () -> chat.sendMessageToUser("Hello"));
        assertEquals(USER_ID_AND_BOT_SERVICE_ARE_NOT_SET, e.getMessage());
    }

    @Test
    @DisplayName("Fail to send a message to the user when only the user id is not set")
    void testSendMessageToUserWhenUserIdIsNotSetAndBotServiceIsSet() {
        BotService botService = mock(BotService.class);
        Chat chat = new Chat();
        chat.setBotService(botService);
        Exception e = assertThrows(RuntimeException.class, () -> chat.sendMessageToUser("Hello"));
        assertEquals(USER_ID_IS_NOT_SET, e.getMessage());
        verifyNoInteractions(botService);
    }

    @Test
    @DisplayName("Fail to send a message to the user when only the bot service is not set")
    void testSendMessageToUserWhenUserIdIsSetAndBotServiceIsNotSet() {
        ChatState state = mock(ChatState.class);
        Chat chat = new Chat(1L, state);
        Exception e = assertThrows(RuntimeException.class, () -> chat.sendMessageToUser("Hello"));
        assertEquals(BOT_SERVICE_IS_NOT_SET, e.getMessage());
        verifyNoInteractions(state);
    }

    @Test
    @DisplayName("Send a message to the user when the user id and the state are set")
    void testSendMessageToUserWhenBotServiceIsSet() {
        String message = "Hello";
        Long userId = 1L;
        ChatState state = mock(ChatState.class);
        BotService botService = mock(BotService.class);
        doNothing().when(botService).sendMessage(userId, message);
        Chat chat = new Chat(userId, state);
        chat.setBotService(botService);
        assertDoesNotThrow(() -> chat.sendMessageToUser(message));
        verifyNoInteractions(state);
        verify(botService).sendMessage(userId, message);
        verifyNoMoreInteractions(ignoreStubs(botService));
    }
}