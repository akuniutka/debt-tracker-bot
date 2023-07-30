package dev.akuniutka.debttracker.entity;

import dev.akuniutka.debttracker.dao.Dao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class Chat {
    @Id
    private Long id;
    @Column(name = "CHAT_STATUS", nullable = false)
    private ChatStatus chatStatus;
    @Column(name = "OPERATION_TYPE")
    private OperationType operationType;
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapKey(name = "name")
    private Map<String, Account> accounts;

    public Chat(Long id) {
        this();
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getReply(String message, Dao<Income> incomeDao, Dao<Expense> expenseDao) {
        String reply = "";
        BigDecimal amount;
        if (chatStatus == ChatStatus.WAITING_FOR_COMMAND) {
            if ("/currentrates".equals(message)) {
                reply = "Пока не реализовано";
            } else if ("/addincome".equals(message)) {
                reply = "Отправьте мне сумму полученного дохода";
                chatStatus = ChatStatus.WAITING_FOR_AMOUNT_OF_INCOME;
                operationType = OperationType.INCOME;
            } else if ("/addexpense".equals(message)) {
                reply = "Отправьте мне сумму расходов";
                chatStatus = ChatStatus.WAITING_FOR_AMOUNT_OF_EXPENSE;
                operationType = OperationType.EXPENSE;
            } else {
                reply = "Неверная команда";
            }
        } else if (chatStatus == ChatStatus.WAITING_FOR_AMOUNT_OF_INCOME) {
            try {
                amount = new BigDecimal(message);
                Income income = new Income();
                income.setChatId(id);
                income.setAmount(amount);
                income.setEntryDate(new Date());
                incomeDao.save(income);
                reply = "Доход в размере " + amount + " был успешно добавлен";
                chatStatus = ChatStatus.WAITING_FOR_COMMAND;
                operationType = null;
            } catch (NumberFormatException e) {
                reply = "Неверный формат суммы\nПожалуйста, отправьте мне сумму полученного дохода";
            }
        } else if (chatStatus == ChatStatus.WAITING_FOR_AMOUNT_OF_EXPENSE) {
            try {
                amount = new BigDecimal(message);
                Expense expense = new Expense();
                expense.setChatId(id);
                expense.setAmount(amount);
                expense.setEntryDate(new Date());
                expenseDao.save(expense);
                reply = "Расход в размере " + amount + " был успешно добавлен";
                chatStatus = ChatStatus.WAITING_FOR_COMMAND;
                operationType = null;
            } catch (NumberFormatException e) {
                reply = "Неверный формат суммы\nПожалуйста, отправьте мне сумму расходов";
            }
        } else {
            assert false : "Unexpected chat status";
        }
        return reply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        return Objects.equals(id, chat.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    protected Chat() {
        accounts = new HashMap<>();
        chatStatus = ChatStatus.WAITING_FOR_COMMAND;
    }
}
