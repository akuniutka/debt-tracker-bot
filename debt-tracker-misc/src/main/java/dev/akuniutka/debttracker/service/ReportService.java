package dev.akuniutka.debttracker.service;

import dev.akuniutka.debttracker.dto.FilterCriteria;
import dev.akuniutka.debttracker.dto.SimpleFilterCriteria;
import dev.akuniutka.debttracker.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public long getCount(SimpleFilterCriteria filter) {
        FilterCriteria newFilter2 = FilterCriteria.of(filter);
        return reportRepository.getIncomesCount(newFilter2) + reportRepository.getExpensesCount(newFilter2);
    }

    public BigDecimal getSum(SimpleFilterCriteria filter) {
        FilterCriteria newFilter2 = FilterCriteria.of(filter);
        return reportRepository.getIncomesSum(newFilter2).subtract(reportRepository.getExpensesSum(newFilter2));
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
