package dev.akuniutka.debttracker.entity;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.chatbot.core.ChatState;

import javax.persistence.*;

@Entity
@Table(name = "CHAT")
public class DebtTrackerChat extends Chat {
    @Id
    private Long userId;
    private transient DebtTrackerChatDao dao;

    public DebtTrackerChat(Long userId, ChatState initialState) {
        super(initialState);
        this.userId = userId;
    }

    public void setDao(DebtTrackerChatDao dao) {
        if (dao == null) {
            throw new IllegalArgumentException("DAO is null");
        }
        this.dao = dao;
    }

    public DebtTrackerChatDao getDao() {
        return dao;
    }

    @Override
    public void setState(ChatState state) {
        if (dao == null) {
            throw new RuntimeException("DAO is not set");
        }
        super.setState(state);
        dao.save(this);
    }

    protected DebtTrackerChat() {
    }
}
