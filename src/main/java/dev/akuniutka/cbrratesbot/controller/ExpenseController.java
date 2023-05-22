package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expenses")
public class ExpenseController {
    private final StatsService statsService;

    // TODO: add endpoint for retrieving expenses
    // TODO: add endpoint for adding expense

    // TODO: change response to DTO
    @GetMapping("/count")
    @Operation(summary = "Получить количество расходов выше указанной суммы")
    public int getCountOfExpensesGreaterThan(@RequestParam(name = "greaterThan", defaultValue = "0") BigDecimal amount) {
        return statsService.getCountOfExpensesGreaterThan(amount);
    }
}
