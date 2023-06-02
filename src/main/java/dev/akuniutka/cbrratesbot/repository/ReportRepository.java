package dev.akuniutka.cbrratesbot.repository;

import dev.akuniutka.cbrratesbot.entity.Income;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReportRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final EntityManager entityManager;

    public long getCountOfIncomes() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        Root<Income> income = criteria.from(Income.class);
        criteria.select(criteriaBuilder.count(income));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    public long getCountOfIncomesGreaterThan(BigDecimal amount) {
        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM INCOMES WHERE INCOME > ?;", Long.class, amount);
        if (count == null) {
            throw new RuntimeException("wrong reply from database");
        }
        return count;
    }

    public long getCountOfExpensesGreater(BigDecimal amount) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        Long count = namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(*) AS COUNT FROM EXPENSES WHERE EXPENSE > :amount;",
                parameters,
                (resultSet, i) -> resultSet.getLong("COUNT")
        );
        if (count == null) {
            throw new RuntimeException("wrong reply from database");
        }
        return count;
    }
}
