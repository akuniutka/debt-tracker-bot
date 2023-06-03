package dev.akuniutka.cbrratesbot.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FilterCriteria {
    private Long chatId;
    private BigDecimal amountFrom;
    private BigDecimal amountTo;
    private Date dateFrom;
    private Date dateTo;
}
