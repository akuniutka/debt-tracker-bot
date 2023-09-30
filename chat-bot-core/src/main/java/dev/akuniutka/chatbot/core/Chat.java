package dev.akuniutka.chatbot.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chat {
    private static final Logger LOGGER = LoggerFactory.getLogger(Chat.class);
    private static final String USER_ID_AND_CHAT_STATE_ARE_NULL = "Setting user id and chat state to null";
    private static final String USER_ID_IS_NULL = "Setting user id to null";
    private static final String CHAT_STATE_IS_NULL = "Setting chat state to null";
    private static final String CHAT_STATE_IS_NOT_SET = "Calling to chat while chat state is not set";
    private Long userId;
    private ChatState state;

    public Chat() {
    }

    public Chat(Long userId, ChatState initialState) {
        if (userId == null || initialState == null) {
            if (userId == null && initialState == null) {
                LOGGER.warn(USER_ID_AND_CHAT_STATE_ARE_NULL);
            } else if (userId == null) {
                LOGGER.warn(USER_ID_IS_NULL);
            } else {
                LOGGER.warn(CHAT_STATE_IS_NULL);
            }
            LOGGER.debug(stackTrace());
        }
        this.userId = userId;
        this.state = initialState;
    }

    public void setUserId(Long userId) {
        if (userId == null) {
            LOGGER.warn(USER_ID_IS_NULL);
            LOGGER.debug(stackTrace());
        }
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setState(ChatState state) {
        if (state == null) {
            LOGGER.warn(CHAT_STATE_IS_NULL);
            LOGGER.debug(stackTrace());
        }
        this.state = state;
    }

    public ChatState getState() {
        return state;
    }

    public List<String> getReplyToMessage(String message) {
        if (state == null) {
            LOGGER.error(CHAT_STATE_IS_NOT_SET);
            throw new RuntimeException(CHAT_STATE_IS_NOT_SET);
        }
        state.processMessage(this, message);
        return state.getReply();
    }

    private String stackTrace() {
        return "at " + Stream.of(Thread.currentThread().getStackTrace())
                .skip(3)
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\nat "));
    }
}
