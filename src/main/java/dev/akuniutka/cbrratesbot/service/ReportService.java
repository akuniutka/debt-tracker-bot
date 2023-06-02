package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public long getCountOfIncomesGreaterThan(BigDecimal amount) {
        if (amount == null) {
            return reportRepository.getCountOfIncomes();
        } else {
            return reportRepository.getCountOfIncomesGreaterThan(amount);
        }
    }

    public long getCountOfExpensesGreaterThan(BigDecimal amount) {
        return reportRepository.getCountOfExpensesGreater(amount);
    }
}
