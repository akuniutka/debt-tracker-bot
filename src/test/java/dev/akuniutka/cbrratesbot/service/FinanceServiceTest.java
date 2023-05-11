package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.repository.ExpenseRepository;
import dev.akuniutka.cbrratesbot.repository.IncomeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FinanceServiceTest {

    private static final Random RANDOM = new Random();

    private static final String[] COMMANDS = {
            "/addincome",
            "/addexpense",
            "%s"
    };

    private static final String[] REPLIES = {
            "Доход в размере %s был успешно добавлен",
            "Расход в размере %s был успешно добавлен",
            "Неверная команда"
    };

    @InjectMocks
    private FinanceService financeService;

    @Mock
    private IncomeRepository incomeRepository;

    @Mock
    private ExpenseRepository expenseRepository;

    @Test
    void testAddFinanceOperation() {
        Set<Integer> choices = new HashSet<>(Arrays.asList(0, 1, 2));
        while (!choices.isEmpty()) {
            Integer i = RANDOM.nextInt(choices.size());
            if (choices.contains(i)) {
                String value = RANDOM.nextInt(1000) + "." + RANDOM.nextInt(100);
                String expected = String.format(REPLIES[i], value);
                String command = String.format(COMMANDS[i], value);
                Long chatId = RANDOM.nextLong();
                String actual = financeService.addFinanceOperation(command, value, chatId);
                assertEquals(expected, actual);
                choices.remove(i);
            }
        }
    }
}