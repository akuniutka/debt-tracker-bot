package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.config.BotTestsConfig;
import dev.akuniutka.cbrratesbot.dto.ExchangeRate;
import dev.akuniutka.cbrratesbot.service.CbrService;
import dev.akuniutka.cbrratesbot.service.StatsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ExchangeRatesController.class)
@Import(BotTestsConfig.class)
class ExchangeRatesControllerTest {

    private static final Random RANDOM = new Random();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private List<ExchangeRate> exchangeRates;

    @MockBean
    private CbrService cbrService;

    @MockBean
    private StatsService statsService;

    @Test
    void whenGetExchangeRatesShouldReturnListOfExchangeRates() throws Exception {
        int testSampleSize = RANDOM.nextInt( exchangeRates.size() - 1) + 2;
        List<ExchangeRate> testSample = new ArrayList<>();
        while (testSample.size() < testSampleSize) {
            ExchangeRate exchangeRate = exchangeRates.get(RANDOM.nextInt(exchangeRates.size()));
            if (!testSample.contains(exchangeRate)) {
                testSample.add(exchangeRate);
            }
        }

        given(cbrService.getExchangeRates()).willReturn(testSample);

        ResultActions resultActions = mvc.perform(get("/exchangeRates"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(testSampleSize)));
        for (int i = 0; i < testSampleSize; i++) {
            resultActions
                    .andExpect(jsonPath("$[" + i +"].currency", is(testSample.get(i).getCurrency())))
                    .andExpect(jsonPath("$[" + i + "].currencyAlphabeticCode", is(testSample.get(i).getCurrencyAlphabeticCode())))
                    .andExpect(jsonPath("$["+ i + "].currencyNumericCode", is(testSample.get(i).getCurrencyNumericCode())))
                    .andExpect(jsonPath("$["+ i + "].units", is(testSample.get(i).getUnits())))
                    .andExpect(jsonPath("$["+ i + "].value", is(testSample.get(i).getValue())));
        }
    }

    @Test
    void getExchangeRate() throws Exception {
        ExchangeRate exchangeRate = exchangeRates.get(RANDOM.nextInt(exchangeRates.size()));

        given(cbrService.getExchangeRate(exchangeRate.getCurrencyAlphabeticCode())).willReturn(exchangeRate);

        mvc.perform(get("/exchangeRates/" + exchangeRate.getCurrencyAlphabeticCode()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currency", is(exchangeRate.getCurrency())))
                .andExpect(jsonPath("$.currencyAlphabeticCode", is(exchangeRate.getCurrencyAlphabeticCode())))
                .andExpect(jsonPath("$.currencyNumericCode", is(exchangeRate.getCurrencyNumericCode())))
                .andExpect(jsonPath("$.units", is(exchangeRate.getUnits())))
                .andExpect(jsonPath("$.value", is(exchangeRate.getValue())));
    }
}