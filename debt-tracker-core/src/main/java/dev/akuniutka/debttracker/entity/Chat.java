package dev.akuniutka.debttracker.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
public class Chat {
    @Id
    private Long id;
    @Column(name = "CHAT_STATUS", nullable = false)
    private ChatState chatState;
    @Column(name = "OPERATION_TYPE")
    private OperationType operationType;
    @Column(name = "ENTRY_TYPE")
    private EntryType entryType;
    private BigDecimal amount;
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "name")
    private Map<String, Account> accounts;

    public Chat(Long id) {
        this();
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        this.id = id;
    }

    public List<String> getReply(String message) {
        chatState = processMessage(message);
        return getMessage();
    }

    protected Chat() {
        accounts = new HashMap<>();
        chatState = ChatState.WAITING_FOR_COMMAND;
    }

    private ChatState processMessage(String message) {
        ChatState result = null;
        switch (chatState) {
            case WAITING_FOR_COMMAND:
            case WAITING_FOR_CORRECT_COMMAND:
            case SHOWING_CURRENT_STATUS:
                result = ChatState.WAITING_FOR_AMOUNT;
                break;
            case WAITING_FOR_AMOUNT:
            case WAITING_FOR_CORRECT_AMOUNT:
                result = ChatState.WAITING_FOR_ACCOUNT;
                break;
            case WAITING_FOR_ACCOUNT:
            case WAITING_FOR_CORRECT_ACCOUNT:
                result = ChatState.WAITING_FOR_COMMAND;
                break;
        }
        return result;
    }

    private List<String> getMessage() {
        List<String> message = new ArrayList<>();
        switch (chatState) {
            case WAITING_FOR_COMMAND:
                message.add("Please, enter a command.");
                break;
            case WAITING_FOR_CORRECT_COMMAND:
                message.add("Unknown command");
                message.add("Please, enter a command.");
                break;
            case SHOWING_CURRENT_STATUS:
                message.add(getCurrentStatus());
                message.add("Please, enter a command");
                break;
            case WAITING_FOR_AMOUNT:
                message.add("Please, enter an amount.");
                break;
            case WAITING_FOR_CORRECT_AMOUNT:
                message.add("A wrong amount.");
                message.add("Please, enter an amount.");
                break;
            case WAITING_FOR_ACCOUNT:
                message.add("Please, enter account's name.");
                break;
            case WAITING_FOR_CORRECT_ACCOUNT:
                message.add("Account's name is incorrect.");
                message.add("Please, enter correct account's name.");
                break;
        }
        return message;
    }

    private String getCurrentStatus() {
        return "(not implemented yet)";
    }
}
