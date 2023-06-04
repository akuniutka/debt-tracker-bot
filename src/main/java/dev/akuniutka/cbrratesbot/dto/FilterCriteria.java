package dev.akuniutka.cbrratesbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FilterCriteria {
    private Long chatId;
    private BigDecimal amountFrom;
    private BigDecimal amountTo;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
