package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.dto.ExchangeRate;
import dev.akuniutka.cbrratesbot.service.CbrService;
import dev.akuniutka.cbrratesbot.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.datatype.DatatypeConfigurationException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ExchangeRatesController {
    private final CbrService cbrService;
    private final StatsService statsService;

    @GetMapping("/exchangeRates")
    @Operation(summary = "получить текущие курсы валют")
    public List<ExchangeRate> getExchangeRates() throws DatatypeConfigurationException, IllegalStateException {
        return cbrService.getExchangeRates();
    }

    @GetMapping("/exchangeRates/{currency}")
    @Operation(summary = "получить текущий курс заданной валюты")
    public ExchangeRate getExchangeRate(@PathVariable String currency) throws
            DatatypeConfigurationException, IllegalStateException, NoSuchElementException {
        return cbrService.getExchangeRate(currency);
    }

    @GetMapping("/topIncomes")
    @Operation(summary = "Получить количество поступлений выше указанной суммы")
    public int getCountOfIncomesGreaterThan(@RequestParam(name = "amount", defaultValue = "0") BigDecimal amount) {
        return statsService.getCountOfIncomesGreaterThan(amount);
    }

    @GetMapping("/topExpenses")
    @Operation(summary = "Получить количество расходоы выше указанной суммы")
    public int getCountOfExpensesGreaterThan(@RequestParam(name = "amount", defaultValue = "0") BigDecimal amount) {
        return statsService.getCountOfExpensesGreaterThan(amount);
    }
}
