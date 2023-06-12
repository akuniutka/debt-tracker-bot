package dev.akuniutka.cbrratesbot.repository;

import dev.akuniutka.cbrratesbot.entity.Expense;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(ReportRepository.class)
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    void testFindAll() {
        List<Expense> expenses = expenseRepository.findAll();
        assertEquals(13, expenses.size());
    }
}