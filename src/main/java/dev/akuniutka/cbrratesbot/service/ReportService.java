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

    // TODO: remove the method - there is getIncomesCount() instead of it
    public long getCountOfIncomesGreaterThanV1(BigDecimal amount) {
        return reportRepository.getCountOfIncomesGreaterThanWithJdbcTemplate(amount);
    }

    // TODO: remove the method - there is getIncomesCount() instead of it
    public long getCountOfIncomesGreaterThanV2(BigDecimal amount) {
        return reportRepository.getCountOfIncomesGreaterThanWithNamedParameterJdbcTemplate(amount);
    }

    public long getIncomesCount(FilterCriteria filter) {
        return reportRepository.getIncomesCount(filter);
    }

    public BigDecimal getIncomesSum(FilterCriteria filter) {
        return reportRepository.getIncomesSum(filter);
    }

    // TODO: remove the method - there is getExpensesCount() instead of it
    public long getCountOfExpensesGreaterThanV1(BigDecimal amount) {
        return reportRepository.getCountOfExpensesGreaterThanWithJdbcTemplate(amount);
    }

    // TODO: remove the method - there is getExpensesCount() instead of it
    public long getCountOfExpensesGreaterThanV2(BigDecimal amount) {
        return reportRepository.getCountOfExpensesGreaterThanWithNamedParameterJdbcTemplate(amount);
    }

    public long getExpensesCount(FilterCriteria filter) {
        return reportRepository.getExpensesCount(filter);
    }

    public BigDecimal getExpensesSum(FilterCriteria filter) {
        return reportRepository.getExpensesSum(filter);
    }

    public long getEntriesCount(FilterCriteria filter) {
        return reportRepository.getIncomesCount(filter) + reportRepository.getExpensesCount(filter);
    }
}
