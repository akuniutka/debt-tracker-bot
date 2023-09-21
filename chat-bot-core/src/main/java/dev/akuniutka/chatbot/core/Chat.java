package dev.akuniutka.chatbot.core;

import java.util.List;

public class Chat {
    private ChatState state;

    public Chat(ChatState initialState) {
        if (initialState == null) {
            throw new IllegalArgumentException("initial chat state is null");
        }
        state = initialState;
    }

    public void setState(ChatState state) {
        if (state == null) {
            throw new IllegalArgumentException("chat state is null");
        }
        this.state = state;
    }

    public ChatState getState() {
        return state;
    }

    public List<String> getReplyToMessage(String message) {
        state.processMessage(this, message);
        return state.getReply();
    }

    protected Chat() {
    }
}
