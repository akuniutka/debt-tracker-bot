package io.github.akuniutka.debttrackerbot.entity;

import io.github.akuniutka.debttrackerbot.util.AmountValidator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "entry_draft")
public class EntryDraft {
    private static final String USER_ID_IS_NULL = "Setting the user id to null";
    private static final String ENTRY_TYPE_IS_NULL = "Setting the entry type to null";
    @Id
    private Long userId;
    private EntryType type;
    private BigDecimal amount;

    public EntryDraft(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException(USER_ID_IS_NULL);
        }
        this.userId = userId;
    }

    protected EntryDraft() {
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        if (type == null) {
            throw new IllegalArgumentException(ENTRY_TYPE_IS_NULL);
        }
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        AmountValidator.assertAmount(amount);
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }
}
