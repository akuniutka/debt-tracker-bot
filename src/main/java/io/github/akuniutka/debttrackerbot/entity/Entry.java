package io.github.akuniutka.debttrackerbot.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @JoinColumn(name = "USER_ID", nullable = false)
    private Long userId;
    private EntryType type;
    private BigDecimal amount;
    private String account;
    private OffsetDateTime date;

    public Entry(Long userId, EntryType type, BigDecimal amount, String account, OffsetDateTime date) {
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.account = account;
        this.date = date;
    }

    public EntryType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAccount() {
        return account;
    }

    protected Entry() {
    }
}
