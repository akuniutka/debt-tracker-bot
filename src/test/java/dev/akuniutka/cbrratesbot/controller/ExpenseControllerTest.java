package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.service.StatsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;


import java.math.BigDecimal;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {
    private static final Random RANDOM = new Random();


    @Autowired
    private MockMvc mvc;

    @MockBean
    private StatsService statsService;

    @Test
    void getCountOfExpensesGreaterThan() throws Exception {
        int count = RANDOM.nextInt(1000);

        given(statsService.getCountOfExpensesGreaterThan(BigDecimal.valueOf(0))).willReturn(count);
        mvc.perform(get("/expenses/count"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}