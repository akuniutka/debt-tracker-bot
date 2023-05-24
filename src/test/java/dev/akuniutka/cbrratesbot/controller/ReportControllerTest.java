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
import java.util.Random;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void testGetCountOfIncomesGreaterThanWithNoAmount() throws Exception {
        int count = RANDOM.nextInt(1000);

        given(reportService.getCountOfExpensesGreaterThan(BigDecimal.valueOf(0))).willReturn(count);

        // TODO: add check for the value in response
        mvc.perform(get("/reports/incomes/count"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetCountOfIncomesGreaterThanWithAmount() throws Exception {
        int count = RANDOM.nextInt(1000);
        BigDecimal amount = BigDecimal.valueOf(RANDOM.nextFloat() * 1000).setScale(2, RoundingMode.HALF_UP);

        given(reportService.getCountOfExpensesGreaterThan(amount)).willReturn(count);

        // TODO: add check for the value in response
        mvc.perform(get("/reports/incomes/count?greaterThan=" + amount))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetCountOfExpensesGreaterThanWithNoAmount() throws Exception {
        int count = RANDOM.nextInt(1000);

        given(reportService.getCountOfExpensesGreaterThan(BigDecimal.valueOf(0))).willReturn(count);

        // TODO: add check for the value in response
        mvc.perform(get("/reports/expenses/count"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetCountOfExpensesGreaterThanWithAmount() throws Exception {
        int count = RANDOM.nextInt(1000);
        BigDecimal amount = BigDecimal.valueOf(RANDOM.nextFloat() * 1000).setScale(2, RoundingMode.HALF_UP);

        given(reportService.getCountOfExpensesGreaterThan(amount)).willReturn(count);

        // TODO: add check for the value in response
        mvc.perform(get("/reports/expenses/count?greaterThan=" + amount))
                .andDo(print())
                .andExpect(status().isOk());
    }
}