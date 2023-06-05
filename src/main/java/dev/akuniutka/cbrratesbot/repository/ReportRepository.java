package dev.akuniutka.cbrratesbot.repository;

import dev.akuniutka.cbrratesbot.dto.FilterCriteria;
import dev.akuniutka.cbrratesbot.entity.Expense;
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

    // TODO: remove the method - there is getIncomesCount() instead of it
    public Long getCountOfIncomesGreaterThanWithJdbcTemplate(BigDecimal amount) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM INCOMES WHERE AMOUNT > ?;", Long.class, amount);
    }

    // TODO: remove the method - there is getIncomesCount() instead of it
    public Long getCountOfIncomesGreaterThanWithNamedParameterJdbcTemplate(BigDecimal amount) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(*) AS COUNT FROM INCOMES WHERE AMOUNT > :amount",
                parameters,
                (resultSet, i) -> resultSet.getLong("COUNT")
        );
    }

    public long getIncomesCount(FilterCriteria filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        Root<Income> income = criteria.from(Income.class);
        criteria.select(criteriaBuilder.count(income));
        criteria.where(filterCriteriaToPredicates(criteriaBuilder, income, filter));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    public BigDecimal getIncomesSum(FilterCriteria filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteria = criteriaBuilder.createQuery(BigDecimal.class);
        Root<Income> income = criteria.from(Income.class);
        criteria.select(criteriaBuilder.sum(income.get("amount")));
        criteria.where(filterCriteriaToPredicates(criteriaBuilder, income, filter));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    // TODO: remove the method - there is getExpensesCount() instead of it
    public Long getCountOfExpensesGreaterThanWithJdbcTemplate(BigDecimal amount) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM EXPENSES WHERE AMOUNT > ?;", Long.class, amount);
    }

    // TODO: remove the method - there is getExpensesCount() instead of it
    public Long getCountOfExpensesGreaterThanWithNamedParameterJdbcTemplate(BigDecimal amount) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(*) AS COUNT FROM EXPENSES WHERE AMOUNT > :amount;",
                parameters,
                (resultSet, i) -> resultSet.getLong("COUNT")
        );
    }

    public long getExpensesCount(FilterCriteria filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        Root<Expense> expense = criteria.from(Expense.class);
        criteria.select(criteriaBuilder.count(expense));
        criteria.where(filterCriteriaToPredicates(criteriaBuilder, expense, filter));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    public BigDecimal getExpensesSum(FilterCriteria filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteria = criteriaBuilder.createQuery(BigDecimal.class);
        Root<Expense> expense = criteria.from(Expense.class);
        criteria.select(criteriaBuilder.sum(expense.get("amount")));
        criteria.where(filterCriteriaToPredicates(criteriaBuilder, expense, filter));
        return entityManager.createQuery(criteria).getSingleResult();
    }


    private <T> Predicate[] filterCriteriaToPredicates(CriteriaBuilder builder, Root<T> root, FilterCriteria filter) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getChatId() != null) {
            predicates.add(builder.equal(root.get("chatId"), filter.getChatId()));
        }
        if (filter.getAmountFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("amount"), filter.getAmountFrom()));
        }
        if (filter.getAmountTo() != null) {
            predicates.add(builder.lessThan(root.get("amount"), filter.getAmountTo()));
        }
        if (filter.getDateFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("entryDate"), filter.getDateFrom()));
        }
        if (filter.getDateTo() != null) {
            predicates.add(builder.lessThan(root.get("entryDate"), filter.getDateTo()));
        }
        return predicates.toArray(new Predicate[0]);
    }
}
