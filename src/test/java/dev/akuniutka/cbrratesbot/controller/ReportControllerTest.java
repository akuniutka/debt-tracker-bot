package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.service.ReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReportController.class)
class ReportControllerTest {
    private static final Random RANDOM = new Random();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReportService reportService;

    // TODO: add tests for SUM function for both incomes and expenses
    // TODO: add tests for MAX, MIN, AVG functions for both incomes and expenses

    @Test
    void testGetCountOfIncomesAboveThresholdV1() throws Exception {
        int count = RANDOM.nextInt(1000);
        BigDecimal amount = getRandomBigDecimal();

        given(reportService.getCountOfIncomesGreaterThanV1(amount)).willReturn((long) count);

        mvc.perform(get("/reports/incomes/v1/count?amount=" + amount))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.count", is(count)));
    }

    @Test
    void testGetCountOfIncomesAboveThresholdV2() throws Exception {
        int count = RANDOM.nextInt(1000);
        BigDecimal amount = getRandomBigDecimal();

        given(reportService.getCountOfIncomesGreaterThanV2(amount)).willReturn((long) count);

        mvc.perform(get("/reports/incomes/v2/count?amount=" + amount))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.count", is(count)));
    }

    @Test
    void testGetIncomesCount() throws Exception {
        List<Long> chatIds = Arrays.asList(null, RANDOM.nextLong());
        List<BigDecimal> amountsFrom = Arrays.asList(null, getRandomBigDecimal());
        List<BigDecimal> amountsTo = Arrays.asList(null, getRandomBigDecimal());
//        List<Date> datesFrom = Arrays.asList(null);
//        List<Date> datesTo = Arrays.asList(null);
    }

    @Test
    void testGetCountOfExpensesGreaterThanWithNoAmount() throws Exception {
        int count = RANDOM.nextInt(1000);

        given(reportService.getCountOfExpensesGreaterThan(null)).willReturn((long) count);

        mvc.perform(get("/reports/expenses/count"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.count", is(count)));
    }

    @Test
    void testGetCountOfExpensesGreaterThanWithAmount() throws Exception {
        int count = RANDOM.nextInt(1000);
        BigDecimal amount = BigDecimal.valueOf(RANDOM.nextFloat() * 1000).setScale(2, RoundingMode.HALF_UP);

        given(reportService.getCountOfExpensesGreaterThan(amount)).willReturn((long) count);

        mvc.perform(get("/reports/expenses/count?amount=" + amount))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.count", is(count)));
    }

    private BigDecimal getRandomBigDecimal() {
        return BigDecimal.valueOf(RANDOM.nextFloat() * 1000).setScale(2, RoundingMode.HALF_UP);
    }
}