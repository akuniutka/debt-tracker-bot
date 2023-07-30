package dev.akuniutka.debttracker.controller;

import dev.akuniutka.debttracker.dto.ExchangeRate;
import dev.akuniutka.debttracker.service.CbrService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchangeRates")
public class ExchangeRateController {
    private final CbrService cbrService;

    // TODO: return error codes if error occurs
    @GetMapping
    @Operation(summary = "получить текущие курсы валют")
    public List<ExchangeRate> getExchangeRates() throws DatatypeConfigurationException, IllegalStateException {
        return cbrService.getExchangeRates();
    }

    // TODO: return 404 if no currency found
    @GetMapping("/{currency}")
    @Operation(summary = "получить текущий курс заданной валюты")
    public ExchangeRate getExchangeRate(@PathVariable String currency) throws
            DatatypeConfigurationException, IllegalStateException, NoSuchElementException {
        return cbrService.getExchangeRate(currency);
    }
}
