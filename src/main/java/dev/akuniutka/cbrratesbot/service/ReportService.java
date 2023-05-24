package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public int getCountOfIncomesGreaterThan(BigDecimal amount) {
        return reportRepository.getCountOfIncomesGreaterThan(amount);
    }

    public int getCountOfExpensesGreaterThan(BigDecimal amount) {
        return reportRepository.getCountOfExpensesGreater(amount);
    }
}
