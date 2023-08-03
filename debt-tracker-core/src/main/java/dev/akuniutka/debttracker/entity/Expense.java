package dev.akuniutka.debttracker.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "EXPENSES")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CHAT_ID", nullable = false)
    private Long chatId;
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;
    @Column(name = "ENTRY_DATE", nullable = false)
    private Date entryDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense = (Expense) o;

        if (!Objects.equals(id, expense.id)) return false;
        if (!Objects.equals(chatId, expense.chatId)) return false;
        if (!Objects.equals(amount, expense.amount)) return false;
        return Objects.equals(entryDate, expense.entryDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (entryDate != null ? entryDate.hashCode() : 0);
        return result;
    }
}
