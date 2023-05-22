package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.dto.ExchangeRate;
import dev.akuniutka.cbrratesbot.service.CbrService;
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

    @GetMapping
    @Operation(summary = "получить текущие курсы валют")
    public List<ExchangeRate> getExchangeRates() throws DatatypeConfigurationException, IllegalStateException {
        return cbrService.getExchangeRates();
    }

    @GetMapping("/{currency}")
    @Operation(summary = "получить текущий курс заданной валюты")
    public ExchangeRate getExchangeRate(@PathVariable String currency) throws
            DatatypeConfigurationException, IllegalStateException, NoSuchElementException {
        return cbrService.getExchangeRate(currency);
    }
}
