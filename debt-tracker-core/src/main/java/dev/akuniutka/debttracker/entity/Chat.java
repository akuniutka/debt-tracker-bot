package dev.akuniutka.debttracker.entity;

import javax.persistence.*;

@Entity
public class Chat {
    @Id
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "CHAT_STATE", nullable = false)
    private ChatState chatState;

    public Chat(Long userId, ChatState initialChatState) {
        if (userId == null) {
            throw new IllegalArgumentException("id is null");
        }
        if (initialChatState == null) {
            throw new IllegalArgumentException("initial chat state is null");
        }
        this.userId = userId;
        chatState = initialChatState;
    }

    public Long getUserId() {
        return userId;
    }

    public void setChatState(ChatState chatState) {
        this.chatState = chatState;
    }

    public ChatReply getReplyToMessage(String message) {
        chatState.processMessage(this, message);
        return new ChatReply(chatState.getReply(), chatState.getPossibleAnswers());
    }

    protected Chat() {
    }
}
