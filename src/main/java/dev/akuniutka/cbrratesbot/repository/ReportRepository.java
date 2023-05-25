package dev.akuniutka.cbrratesbot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReportRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int getCountOfIncomesGreaterThan(BigDecimal amount) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM INCOMES WHERE INCOME > ?;", Integer.class, amount);
        if (count == null) {
            throw new RuntimeException("wrong reply from database");
        }
        return count;
    }

    public int getCountOfExpensesGreater(BigDecimal amount) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        Integer count = namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(*) AS COUNT FROM EXPENSES WHERE EXPENSE > :amount;",
                parameters,
                (resultSet, i) -> resultSet.getInt("COUNT")
        );
        if (count == null) {
            throw new RuntimeException("wrong reply from database");
        }
        return count;
    }
}
