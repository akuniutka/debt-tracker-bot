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

    public long getCount(FilterCriteria filter) {
        return reportRepository.getIncomesCount(filter) + reportRepository.getExpensesCount(filter);
    }

    public BigDecimal getSum(FilterCriteria filter) {
        return reportRepository.getIncomesSum(filter).subtract(reportRepository.getExpensesSum(filter));
    }

    public long getIncomesCount(FilterCriteria filter) {
        return reportRepository.getIncomesCount(filter);
    }

    public BigDecimal getIncomesSum(FilterCriteria filter) {
        return reportRepository.getIncomesSum(filter);
    }

    public long getExpensesCount(FilterCriteria filter) {
        return reportRepository.getExpensesCount(filter);
    }

    public BigDecimal getExpensesSum(FilterCriteria filter) {
        return reportRepository.getExpensesSum(filter);
    }
}
