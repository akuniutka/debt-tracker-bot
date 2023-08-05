package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.Expense;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpenseRepositoryIT {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    void testFindAll() {
        List<Expense> expenses = expenseRepository.findAll();
        assertEquals(13, expenses.size());
    }
}