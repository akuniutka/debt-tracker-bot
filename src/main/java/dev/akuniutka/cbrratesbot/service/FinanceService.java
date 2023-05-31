package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.entity.Income;
import dev.akuniutka.cbrratesbot.entity.Expense;
import dev.akuniutka.cbrratesbot.repository.IncomeRepository;
import dev.akuniutka.cbrratesbot.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class FinanceService {
    private static final String ADD_INCOME = "/addincome";
    private static final String ADD_EXPENSE = "/addexpense";
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public String addFinanceOperation(String operationType, String value, Long chatId) {
        String message = "Неверная команда";
        try {
            if (ADD_INCOME.equals(operationType)) {
                Income income = new Income();
                income.setChatId(chatId);
                income.setValue(new BigDecimal(value));
                income.setEntryDate(new Date());
                incomeRepository.save(income);
                message = "Доход в размере " + value + " был успешно добавлен";
            } else if (ADD_EXPENSE.equals((operationType))) {
                Expense expense = new Expense();
                expense.setChatId(chatId);
                expense.setValue(new BigDecimal(value));
                expense.setEntryDate(new Date());
                expenseRepository.save(expense);
                message = "Расход в размере " + value + " был успешно добавлен";
            }
        } catch (NumberFormatException e) {
            log.error("Wrong number format", e);
        }
        return message;
    }
}
