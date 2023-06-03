package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.dto.FilterCriteria;
import dev.akuniutka.cbrratesbot.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public long getCountOfIncomesGreaterThanV1(BigDecimal amount) {
        return reportRepository.getCountOfIncomesGreaterThanWithJdbcTemplate(amount);
    }

    public long getCountOfIncomesGreaterThanV2(BigDecimal amount) {
        return reportRepository.getCountOfIncomesGreaterThanWithNamedParameterJdbcTemplate(amount);
    }

    public long getIncomesCount(FilterCriteria filter) {
        return reportRepository.getIncomesCount(filter);
    }

    public BigDecimal getIncomesSum(FilterCriteria filter) {
        return reportRepository.getIncomesSum(filter);
    }

    public long getCountOfExpensesGreaterThan(BigDecimal amount) {
        return reportRepository.getCountOfExpensesGreater(amount);
    }
}
