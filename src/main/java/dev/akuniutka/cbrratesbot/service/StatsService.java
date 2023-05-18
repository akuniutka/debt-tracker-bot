package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final StatsRepository statsRepository;

    public int getCountOfIncomesGreaterThan(BigDecimal amount) {
        return statsRepository.getCountOfIncomesGreaterThan(amount);
    }

    public int getCountOfExpensesGreaterThan(BigDecimal amount) {
        return statsRepository.getCountOfExpensesGreater(amount);
    }
}
