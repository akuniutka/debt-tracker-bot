package dev.akuniutka.cbrratesbot.repository;

import dev.akuniutka.cbrratesbot.dto.FilterCriteria;
import dev.akuniutka.cbrratesbot.entity.Expense;
import dev.akuniutka.cbrratesbot.entity.Income;
import lombok.RequiredArgsConstructor;
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
    private final EntityManager entityManager;

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
