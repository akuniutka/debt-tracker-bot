package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.dto.ExchangeRate;
import dev.akuniutka.cbrratesbot.service.CbrService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(ExchangeRatesController.class)
class ExchangeRatesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CbrService cbrService;

    @Test
    void whenGetExchangeRatesShouldReturnListOfExchangeRates() throws Exception {
        ExchangeRate usd = new ExchangeRate();
        usd.setCurrency("Доллар США");
        usd.setCurrencyAlphabeticCode("USD");
        usd.setCurrencyNumericCode("840");
        usd.setUnits(1);
        usd.setValue(60.0);
        ExchangeRate eur = new ExchangeRate();
        eur.setCurrency("Евро");
        eur.setCurrencyAlphabeticCode("EUR");
        eur.setCurrencyNumericCode("978");
        eur.setUnits(1);
        eur.setValue(80.0);

        List<ExchangeRate> exchangeRates = Arrays.asList(usd, eur);

        given(cbrService.getExchangeRates()).willReturn(exchangeRates);

        mvc.perform(get("/exchangeRates"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].currency", is(usd.getCurrency())))
                .andExpect(jsonPath("$[0].currencyAlphabeticCode", is(usd.getCurrencyAlphabeticCode())))
                .andExpect(jsonPath("$[0].currencyNumericCode", is(usd.getCurrencyNumericCode())))
                .andExpect(jsonPath("$[0].units", is(usd.getUnits())))
                .andExpect(jsonPath("$[0].value", is(usd.getValue())))
                .andExpect(jsonPath("$[1].currency", is(eur.getCurrency())))
                .andExpect(jsonPath("$[1].currencyAlphabeticCode", is(eur.getCurrencyAlphabeticCode())))
                .andExpect(jsonPath("$[1].currencyNumericCode", is(eur.getCurrencyNumericCode())))
                .andExpect(jsonPath("$[1].units", is(eur.getUnits())))
                .andExpect(jsonPath("$[1].value", is(eur.getValue())));
    }

    @Test
    void getExchangeRate() throws Exception {
        ExchangeRate usd = new ExchangeRate();
        usd.setCurrency("Доллар США");
        usd.setCurrencyAlphabeticCode("USD");
        usd.setCurrencyNumericCode("840");
        usd.setUnits(1);
        usd.setValue(60.0);

        given(cbrService.getExchangeRate("USD")).willReturn(usd);

        mvc.perform(get("/exchangeRates/USD"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currency", is(usd.getCurrency())))
                .andExpect(jsonPath("$.currencyAlphabeticCode", is(usd.getCurrencyAlphabeticCode())))
                .andExpect(jsonPath("$.currencyNumericCode", is(usd.getCurrencyNumericCode())))
                .andExpect(jsonPath("$.units", is(usd.getUnits())))
                .andExpect(jsonPath("$.value", is(usd.getValue())));
    }
}