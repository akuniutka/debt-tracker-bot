package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

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

    public long getIncomesCount(Long chatId, BigDecimal amountFrom, BigDecimal amountTo, Date dateFrom, Date dateTo) {
        return reportRepository.getIncomesCount(chatId, amountFrom, amountTo, dateFrom, dateTo);
    }

    public BigDecimal getIncomesSum(Long chatId, BigDecimal amountFrom, BigDecimal amountTo, Date dateFrom, Date dateTo) {
        return reportRepository.getIncomesSum(chatId, amountFrom, amountTo, dateFrom, dateTo);
    }

    public long getCountOfExpensesGreaterThan(BigDecimal amount) {
        return reportRepository.getCountOfExpensesGreater(amount);
    }
}
