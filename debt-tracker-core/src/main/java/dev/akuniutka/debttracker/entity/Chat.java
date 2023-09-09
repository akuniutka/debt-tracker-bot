package dev.akuniutka.debttracker.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
public class Chat {
    @Id
    private Long id;
    @Column(name = "CHAT_STATE", nullable = false)
    private ChatState chatState = new WaitingForStartChatState();
    @Column(name = "ENTRY_TYPE")
    private EntryType entryType;
    private BigDecimal amount;
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "name")
    private Map<String, Account> accounts = new HashMap<>();

    public Chat(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        this.id = id;
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
