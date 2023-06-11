package dev.akuniutka.cbrratesbot.dto;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FilterCriteria extends SimpleFilterCriteria {
    private BigDecimal amountFrom;
    private BigDecimal amountTo;

    public FilterCriteria(Long chatId, LocalDate dateFrom, LocalDate dateTo, BigDecimal amountFrom, BigDecimal amountTo) {
        super(chatId, dateFrom, dateTo);
        setAmountFrom(amountFrom);
        setAmountTo(amountTo);
    }

    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(BigDecimal amountFrom) {
        this.amountFrom = amountFrom;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }

    public static FilterCriteria of(SimpleFilterCriteria filter) {
        return new FilterCriteria(
                filter.getChatId(), filter.getDateFrom(), filter.getDateTo(), null, null
        );
    }
}
