package dev.akuniutka.cbrratesbot.repository;

import dev.akuniutka.cbrratesbot.entity.Income;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {"spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect"})
class IncomeRepositoryTest {

    @Autowired
    private IncomeRepository incomeRepository;

    @Test
    void testRpository() {
        for (int i = 0; i < 10; i++) {
            Income income = new Income();
            income.setChatId(444444444L);
            income.setAmount(BigDecimal.valueOf(555.55));
            income.setEntryDate(new Date());
            incomeRepository.save(income);
        }
        final List<Income> incomes = incomeRepository.findAll();
        assertEquals(23, incomes.size());
    }

}