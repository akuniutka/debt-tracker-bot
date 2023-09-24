package dev.akuniutka.debttracker.entity;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.chatbot.core.ChatState;
import dev.akuniutka.debttracker.util.AmountValidator;
import dev.akuniutka.debttracker.util.ErrorMessage;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "CHAT")
public class DebtTrackerChat extends Chat {
    @Id
    private Long userId;
    @Column(name = "CACHED_AMOUNT")
    @Access(AccessType.PROPERTY)
    private BigDecimal cachedAmount;
    @Column(name = "CACHED_ENTRY_TYPE")
    private EntryType cachedEntryType;

    public DebtTrackerChat(Long userId, ChatState initialState) {
        super(initialState);
        if (userId == null) {
            throw new IllegalArgumentException(ErrorMessage.USER_ID_IS_NULL);
        }
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setCachedEntryType(EntryType cachedEntryType) {
        if (cachedEntryType == null) {
            throw new IllegalArgumentException(ErrorMessage.ENTRY_TYPE_IS_NULL);
        }
        this.cachedEntryType = cachedEntryType;
    }

    public EntryType getCachedEntryType() {
        return cachedEntryType;
    }

    public void setCachedAmount(BigDecimal cachedAmount) {
        if (cachedAmount != null) {
            AmountValidator.assertAmount(cachedAmount);
            this.cachedAmount = cachedAmount.setScale(2, RoundingMode.HALF_UP);
        } else {
            this.cachedAmount = null;
        }
    }

    public BigDecimal getCachedAmount() {
        return cachedAmount;
    }

    public void clearCache() {
        cachedAmount = null;
        cachedEntryType = null;
    }

    protected DebtTrackerChat() {
    }
}
