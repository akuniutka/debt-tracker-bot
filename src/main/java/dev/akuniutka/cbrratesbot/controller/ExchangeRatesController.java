package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.dto.ExchangeRate;
import dev.akuniutka.cbrratesbot.service.CbrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ExchangeRatesController {
    private final CbrService cbrService;

    @GetMapping("/exchangeRates")
    public List<ExchangeRate> getExchangeRates() throws DatatypeConfigurationException, IllegalStateException {
        return cbrService.getExchangeRates();
    }

    @GetMapping("/exchangeRates/{currency}")
    public ExchangeRate getExchangeRate(@PathVariable String currency) throws
            DatatypeConfigurationException, IllegalStateException, NoSuchElementException {
        return cbrService.getExchangeRate(currency);
    }
}
