package dev.akuniutka.debttracker.entity;

import javax.persistence.*;

@Entity
public class Chat {
    @Id
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "CHAT_STATE", nullable = false)
    private ChatState state;

    public Chat(Long userId, ChatState initialState) {
        if (userId == null) {
            throw new IllegalArgumentException("id is null");
        }
        if (initialState == null) {
            throw new IllegalArgumentException("initial chat state is null");
        }
        this.userId = userId;
        state = initialState;
    }

    public Long getUserId() {
        return userId;
    }

    public ChatState getState() {
        return state;
    }

    public void setState(ChatState state) {
        if (state == null) {
            throw new IllegalArgumentException("chat state is null");
        }
        this.state = state;
    }

    public ChatReply getReplyToMessage(String message) {
        state.processMessage(this, message);
        return new ChatReply(state.getReply(), state.getPossibleAnswers());
    }

    protected Chat() {
    }
}
