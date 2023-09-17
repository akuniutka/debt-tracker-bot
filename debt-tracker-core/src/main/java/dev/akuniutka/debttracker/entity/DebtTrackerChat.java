package dev.akuniutka.debttracker.entity;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.chatbot.core.ChatState;

import javax.persistence.*;

@Entity
@Table(name = "CHAT")
public class DebtTrackerChat extends Chat {
    @Id
    private Long userId;

    public DebtTrackerChat(Long userId, ChatState initialState) {
        super(initialState);
        this.userId = userId;
    }

    protected DebtTrackerChat() {
    }
}
