package dev.akuniutka.cbrratesbot.controllers;

import dev.akuniutka.cbrratesbot.dto.ExchangeRate;
import dev.akuniutka.cbrratesbot.service.CbrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExchangeRatesController {
    private final CbrService cbrService;

    @GetMapping("/getExchangeRates")
    public List<ExchangeRate> getExchangeRates() throws Exception {
        return cbrService.getExchangeRates();
    }
}
