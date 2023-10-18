package io.github.akuniutka.debttrackerbot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "entry_draft")
public class EntryDraft {
    @Id
    private Long userId;
    private EntryType type;
    private BigDecimal amount;

    public EntryDraft(Long userId) {
        this.userId = userId;
    }

    protected EntryDraft() {
    }

    public Long getUserId() {
        return userId;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
