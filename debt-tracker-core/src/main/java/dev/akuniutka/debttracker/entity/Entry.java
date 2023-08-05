package dev.akuniutka.debttracker.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;
    @Column(nullable = false)
    private EntryType type;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private Date date;

    public Entry(Account account, EntryType type, BigDecimal amount, Date date) {
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    protected Entry() {}
}
