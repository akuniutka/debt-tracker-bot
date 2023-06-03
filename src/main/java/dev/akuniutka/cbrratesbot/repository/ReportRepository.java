package dev.akuniutka.cbrratesbot.repository;

import dev.akuniutka.cbrratesbot.entity.Income;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ReportRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final EntityManager entityManager;

    public long getCountOfIncomesGreaterThanWithJdbcTemplate(BigDecimal amount) {
        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM INCOMES WHERE AMOUNT = ?;", Long.class, amount);
        if (count == null) {
            throw new RuntimeException("wrong reply from database");
        }
        return count;
    }

    public long getCountOfIncomesGreaterThanWithNamedParameterJdbcTemplate(BigDecimal amount) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        Long count = namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(*) AS COUNT FROM INCOMES WHERE AMOUNT > :amount",
                parameters,
                (resultSet, i) -> resultSet.getLong("COUNT")
        );
        if (count == null) {
            throw new RuntimeException("wrong reply from database");
        }
        return count;
    }

    public long getIncomesCount(Long chatId, BigDecimal amountFrom, BigDecimal amountTo, Date dateFrom, Date dateTo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        Root<Income> income = criteria.from(Income.class);
        criteria.select(criteriaBuilder.count(income));
        List<Predicate> predicates = new ArrayList<>();
        if (chatId != null) {
            predicates.add(criteriaBuilder.equal(income.get("chatId"), chatId));
        }
        if (amountFrom != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(income.get("amount"), amountFrom));
        }
        if (amountTo != null) {
            predicates.add(criteriaBuilder.lessThan(income.get("amount"), amountTo));
        }
        if (dateFrom != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(income.get("entryDate"), dateFrom));
        }
        if (dateTo != null) {
            predicates.add(criteriaBuilder.lessThan(income.get("entryDate"), dateTo));
        }
        criteria.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    public BigDecimal getIncomesSum(Long chatId, BigDecimal amountFrom, BigDecimal amountTo, Date dateFrom, Date dateTo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteria = criteriaBuilder.createQuery(BigDecimal.class);
        Root<Income> income = criteria.from(Income.class);
        criteria.select(criteriaBuilder.sum(income.get("amount")));
        List<Predicate> predicates = new ArrayList<>();
        if (chatId != null) {
            predicates.add(criteriaBuilder.equal(income.get("chatId"), chatId));
        }
        if (amountFrom != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(income.get("amount"), amountFrom));
        }
        if (amountTo != null) {
            predicates.add(criteriaBuilder.lessThan(income.get("amount"), amountTo));
        }
        if (dateFrom != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(income.get("entryDate"), dateFrom));
        }
        if (dateTo != null) {
            predicates.add(criteriaBuilder.lessThan(income.get("entryDate"), dateTo));
        }
        criteria.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    public long getCountOfExpensesGreater(BigDecimal amount) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        Long count = namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(*) AS COUNT FROM EXPENSES WHERE AMOUNT > :amount;",
                parameters,
                (resultSet, i) -> resultSet.getLong("COUNT")
        );
        if (count == null) {
            throw new RuntimeException("wrong reply from database");
        }
        return count;
    }
}
