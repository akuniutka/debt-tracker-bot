package io.github.akuniutka.debttrackerbot.entity;

import io.github.akuniutka.debttrackerbot.script.ChatState;
import io.github.akuniutka.debttrackerbot.service.BotService;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Slf4j
public class Chat {
    private static final String USER_ID_AND_CHAT_STATE_ARE_NULL = "Setting the user id and the chat state to null";
    private static final String USER_ID_IS_NULL = "Setting the user id to null";
    private static final String CHAT_STATE_IS_NULL = "Setting the chat state to null";
    private static final String BOT_SERVICE_IS_NULL = "Setting the bot service to null";
    private static final String CHAT_STATE_IS_NOT_SET = "Calling to the chat while the chat state is not set";
    private static final String USER_ID_AND_BOT_SERVICE_ARE_NOT_SET =
            "Sending a message while the user id and the bot service are not set";
    private static final String USER_ID_IS_NOT_SET = "Sending a message while the user id is not set";
    private static final String BOT_SERVICE_IS_NOT_SET = "Sending a message while the bot service is not set";

    @Id
    private Long userId;

    @Column(name = "chat_state", nullable = false)
    private ChatState state;
    private transient BotService botService;

    public Chat() {
    }

    public Chat(Long userId, ChatState initialState) {
        if (userId == null && initialState == null) {
            throw new IllegalArgumentException(USER_ID_AND_CHAT_STATE_ARE_NULL);
        } else if (userId == null) {
            throw new IllegalArgumentException(USER_ID_IS_NULL);
        } else if (initialState == null) {
            throw new IllegalArgumentException(CHAT_STATE_IS_NULL);
        }
        this.userId = userId;
        this.state = initialState;
    }

    public Long getUserId() {
        return userId;
    }

    public void setState(ChatState state) {
        if (state == null) {
            throw new IllegalArgumentException(CHAT_STATE_IS_NULL);
        }
        this.state = state;
    }

    public void setBotService(BotService botService) {
        if (botService == null) {
            throw new IllegalArgumentException(BOT_SERVICE_IS_NULL);
        }
        this.botService = botService;
    }

    public void addUserMessage(String message) {
        if (state == null) {
            throw new RuntimeException(CHAT_STATE_IS_NOT_SET);
        }
        state.addUserMessage(this, message);
    }

    public void sendMessageToUser(String message) {
        if (userId == null && botService == null) {
            throw new RuntimeException(USER_ID_AND_BOT_SERVICE_ARE_NOT_SET);
        } else if (userId == null) {
            throw new RuntimeException(USER_ID_IS_NOT_SET);
        } else if (botService == null) {
            throw new RuntimeException(BOT_SERVICE_IS_NOT_SET);
        }
        botService.sendMessage(userId, message);
    }
}
