package dev.akuniutka.debttracker.entity;

import dev.akuniutka.debttracker.dao.Dao;

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
//        String reply = "";
//        BigDecimal amount;
//        if (chatState == ChatState.WAITING_FOR_COMMAND) {
//            if ("/currentrates".equals(message)) {
//                reply = "Пока не реализовано";
////                String accountName = "test account 2";
////                accounts.putIfAbsent(accountName, new Account(this, accountName));
//            } else if ("/addincome".equals(message)) {
//                reply = "Отправьте мне сумму полученного дохода";
//                chatState = ChatState.WAITING_FOR_AMOUNT_OF_INCOME;
//                operationType = OperationType.INCOME;
//            } else if ("/addexpense".equals(message)) {
//                reply = "Отправьте мне сумму расходов";
//                chatState = ChatState.WAITING_FOR_AMOUNT_OF_EXPENSE;
//                operationType = OperationType.EXPENSE;
//            } else {
//                reply = "Неверная команда";
//            }
//        } else if (chatState == ChatState.WAITING_FOR_AMOUNT_OF_INCOME) {
//            try {
//                amount = new BigDecimal(message);
//                Income income = new Income();
//                income.setChatId(id);
//                income.setAmount(amount);
//                income.setEntryDate(new Date());
//                incomeDao.save(income);
//                reply = "Доход в размере " + amount + " был успешно добавлен";
//                chatState = ChatState.WAITING_FOR_COMMAND;
//                operationType = null;
//            } catch (NumberFormatException e) {
//                reply = "Неверный формат суммы\nПожалуйста, отправьте мне сумму полученного дохода";
//            }
//        } else if (chatState == ChatState.WAITING_FOR_AMOUNT_OF_EXPENSE) {
//            try {
//                amount = new BigDecimal(message);
//                Expense expense = new Expense();
//                expense.setChatId(id);
//                expense.setAmount(amount);
//                expense.setEntryDate(new Date());
//                expenseDao.save(expense);
//                reply = "Расход в размере " + amount + " был успешно добавлен";
//                chatState = ChatState.WAITING_FOR_COMMAND;
//                operationType = null;
//            } catch (NumberFormatException e) {
//                reply = "Неверный формат суммы\nПожалуйста, отправьте мне сумму расходов";
//            }
//        } else {
//            assert false : "Unexpected chat status";
//        }
//        return reply;
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
            case WAITING_FOR_CORRECT_AQCCOUNT:
                result = ChatState.WAITING_FOR_COMMAND;
                break;
        }
        return result;
    }

    private List<String> getMessage() {
        List<String> messsage = new ArrayList<>();
        switch (chatState) {
            case WAITING_FOR_COMMAND:
                messsage.add("Please, enter a command.");
                break;
            case WAITING_FOR_CORRECT_COMMAND:
                messsage.add("Unknown command");
                messsage.add("Please, anter a command.");
                break;
            case SHOWING_CURRENT_STATUS:
                messsage.add(getCurrentStatus());
                messsage.add("Please, enter a command");
                break;
            case WAITING_FOR_AMOUNT:
                messsage.add("Please, enter an amount.");
                break;
            case WAITING_FOR_CORRECT_AMOUNT:
                messsage.add("A wrong amount.");
                messsage.add("Please, enter an amount.");
                break;
            case WAITING_FOR_ACCOUNT:
                messsage.add("Please, enter account's name.");
                break;
            case WAITING_FOR_CORRECT_AQCCOUNT:
                messsage.add("Account's name is incorrect.");
                messsage.add("Please, enter correct account's name.");
                break;
        }
        return messsage;
    }

    private String getCurrentStatus() {
        return "(not implemented yet)";
    }
}
