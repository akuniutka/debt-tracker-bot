package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    default Optional<Expense> get(Long id) {
        return findById(id);
    }
}
