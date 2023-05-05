package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.entity.Income;
import dev.akuniutka.cbrratesbot.entity.Expense;
import dev.akuniutka.cbrratesbot.repository.IncomeRepository;
import dev.akuniutka.cbrratesbot.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FinanceService {
    private static final String ADD_INCOME = "/addincome";
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public String addFinanceOperation(String operationType, String value, Long chatId) {
        String message;
        if (ADD_INCOME.equalsIgnoreCase(operationType)) {
            Income income = new Income();
            income.setChatId(chatId);
            income.setValue(new BigDecimal(value));
            incomeRepository.save(income);
            message = "Доход в размере " + value +  " был успешно добавлен";
        } else {
            Expense expense = new Expense();
            expense.setChatId(chatId);
            expense.setValue(new BigDecimal(value));
            expenseRepository.save(expense);
            message = "Расход в размере " + value +  " был успешно добавлен";
        }
        return message;
    }
}
