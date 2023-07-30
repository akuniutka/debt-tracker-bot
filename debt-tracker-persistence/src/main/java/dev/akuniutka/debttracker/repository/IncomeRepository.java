package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.dao.Dao;
import dev.akuniutka.debttracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>, Dao<Income> {
    default Optional<Income> get(Long id) {
        return findById(id);
    }
}