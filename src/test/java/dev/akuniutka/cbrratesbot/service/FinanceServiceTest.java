package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.repository.ExpenseRepository;
import dev.akuniutka.cbrratesbot.repository.IncomeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FinanceServiceTest {

    private static final Random RANDOM = new Random();

    @InjectMocks
    private FinanceService financeService;

    @Mock
    private IncomeRepository incomeRepository;

    @Mock
    private ExpenseRepository expenseRepository;

    @BeforeEach
    void beforeEach() {
        System.out.println(System.currentTimeMillis());
    }

    @AfterEach
    void afterEach() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    @DisplayName("Add Income Test")
    void testAddIncomeOperation() {
        String value = RANDOM.nextInt(1000) + "." + RANDOM.nextInt(100);
        String expected = String.format("Доход в размере %s был успешно добавлен", value);
        String command = "/addincome";
        Long chatId = RANDOM.nextLong();
        String actual = financeService.addFinanceOperation(command, value, chatId);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Add Expense Test")
    void testAddExpenseOperation() {
        String value = RANDOM.nextInt(1000) + "." + RANDOM.nextInt(100);
        String expected = String.format("Расход в размере %s был успешно добавлен", value);
        String command = "/addexpense";
        Long chatId = RANDOM.nextLong();
        String actual = financeService.addFinanceOperation(command, value, chatId);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Wrong Operation Test")
    void testAddWrongOperation() {
        String value = RANDOM.nextInt(1000) + "." + RANDOM.nextInt(100);
        String expected = "Неверная команда";
        Long chatId = RANDOM.nextLong();
        String actual = financeService.addFinanceOperation(value, value, chatId);
        assertEquals(expected, actual);
    }
}