package dev.akuniutka.debttracker.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SumDto {
    private final BigDecimal sum;
}
