package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    // TODO: add root enpoint to list all reports
    // TODO: /incomes and /expenses endpoints to list income reports and expenses reports respectively

    @GetMapping("/incomes/count")
    @Operation(summary = "Получить количество поступлений")
    public int getCountOfIncomesGreaterThan(@RequestParam(name = "greaterThan", defaultValue = "0") BigDecimal amount) {
        return reportService.getCountOfIncomesGreaterThan(amount);
    }

    @GetMapping("/count")
    @Operation(summary = "Получить количество расходов")
    public int getCountOfExpensesGreaterThan(@RequestParam(name = "greaterThan", defaultValue = "0") BigDecimal amount) {
        return reportService.getCountOfExpensesGreaterThan(amount);
    }
}
