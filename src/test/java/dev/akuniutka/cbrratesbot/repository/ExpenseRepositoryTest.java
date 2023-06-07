package dev.akuniutka.cbrratesbot.repository;

import dev.akuniutka.cbrratesbot.entity.Expense;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {"spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect"})
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    void testFindAll() {
        List<Expense> expenses = expenseRepository.findAll();
        assertEquals(13, expenses.size());
    }
}