package dev.akuniutka.cbrratesbot.dto;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
public class FilterCriteria extends SimpleFilterCriteria {
    private BigDecimal amountFrom;
    private BigDecimal amountTo;

    public FilterCriteria() {}

    public FilterCriteria(Long chatId, String dateFrom, String dateTo, BigDecimal amountFrom, BigDecimal amountTo) {
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

    public static FilterCriteria of(SimpleFilterCriteria oldFilter) {
        FilterCriteria newFilter = new FilterCriteria();
        newFilter.chatId = oldFilter.chatId;
        newFilter.dateFrom = oldFilter.dateFrom;
        newFilter.dateTo = oldFilter.dateTo;
        return newFilter;
    }
}
