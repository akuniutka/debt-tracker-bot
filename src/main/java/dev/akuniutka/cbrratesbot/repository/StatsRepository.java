package dev.akuniutka.cbrratesbot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@RequiredArgsConstructor
public class StatsRepository {
    private final JdbcTemplate jdbcTemplate;

    public int getCountOfIncomesThatGrater(BigDecimal amount) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM INCOMES WHERE INCOME > ?;", Integer.class, amount);
    }
}
