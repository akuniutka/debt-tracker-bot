package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.dto.CountDto;
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
    // TODO: add Spring Security to limit access to data in reports by chatId

    @GetMapping("/incomes/count")
    @Operation(summary = "Получить количество доходных операций")
    public CountDto getCountOfIncomesGreaterThan(@RequestParam(name = "greaterThan", defaultValue = "0") BigDecimal amount) {
        return new CountDto(reportService.getCountOfIncomesGreaterThan(amount));
    }

    @GetMapping("/expenses/count")
    @Operation(summary = "Получить количество расходных операций")
    public CountDto getCountOfExpensesGreaterThan(@RequestParam(name = "greaterThan", defaultValue = "0") BigDecimal amount) {
        return new CountDto(reportService.getCountOfExpensesGreaterThan(amount));
    }
}
