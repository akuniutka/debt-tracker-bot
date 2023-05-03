package dev.akuniutka.cbrratesbot.controllers;

import dev.akuniutka.cbrratesbot.dto.ValuteCursOnDate;
import dev.akuniutka.cbrratesbot.service.CbrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController {
    private final CbrService cbrService;

    @PostMapping("/getCurrencies")
    public List<ValuteCursOnDate> getValuteCursOnDate() throws Exception {
        return cbrService.getCurrenciesFromCbr();
    }
}
