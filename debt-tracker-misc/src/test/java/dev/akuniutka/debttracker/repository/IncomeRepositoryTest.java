package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.Income;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(ReportRepository.class)
class IncomeRepositoryTest {

    @Autowired
    private IncomeRepository incomeRepository;

    @Test
    void testFindAll() {
        List<Income> incomes = incomeRepository.findAll();
        assertEquals(13, incomes.size());
    }

}