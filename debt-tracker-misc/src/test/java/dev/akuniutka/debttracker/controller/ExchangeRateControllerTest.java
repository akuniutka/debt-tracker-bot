package dev.akuniutka.debttracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.akuniutka.debttracker.config.BotTestsConfig;
import dev.akuniutka.debttracker.dto.ExchangeRate;
import dev.akuniutka.debttracker.service.CbrService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExchangeRateController.class)
@Import(BotTestsConfig.class)
class ExchangeRateControllerTest {
    private static final Random RANDOM = new Random();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private List<ExchangeRate> exchangeRates;

    @MockBean
    private CbrService cbrService;


    // TODO: check size of an individual object in a exchange rates collection
    @Test
    void testGetExchangeRates() throws Exception {
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
    void testGetExchangeRate() throws Exception {
        ExchangeRate exchangeRate = exchangeRates.get(RANDOM.nextInt(exchangeRates.size()));
        String expectedJson = new ObjectMapper().writeValueAsString(exchangeRate);

        given(cbrService.getExchangeRate(exchangeRate.getCurrencyAlphabeticCode())).willReturn(exchangeRate);

        mvc.perform(get("/exchangeRates/{currency}", exchangeRate.getCurrencyAlphabeticCode()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}