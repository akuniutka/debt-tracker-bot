package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public long getIncomesCount(BigDecimal amountFrom, BigDecimal amountTo) {
        if (amountFrom == null || amountTo != null) {
            return reportRepository.getIncomesCount(amountFrom, amountTo);
        } else {
            return reportRepository.getCountOfIncomesGreaterOrEqual(amountFrom);
        }
    }

    public long getCountOfExpensesGreaterThan(BigDecimal amount) {
        return reportRepository.getCountOfExpensesGreater(amount);
    }
}
