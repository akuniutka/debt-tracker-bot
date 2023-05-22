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
@RequestMapping("/incomes")
public class IncomeController {
    private final StatsService statsService;

    // TODO: add endpoint for retrieving expenses
    // TODO: add endpoint for adding expense

    // TODO: change response to DTO
    @GetMapping("/count")
    @Operation(summary = "Получить количество поступлений выше указанной суммы")
    public int getCountOfIncomesGreaterThan(@RequestParam(name = "greaterThan", defaultValue = "0") BigDecimal amount) {
        return statsService.getCountOfIncomesGreaterThan(amount);
    }
}
