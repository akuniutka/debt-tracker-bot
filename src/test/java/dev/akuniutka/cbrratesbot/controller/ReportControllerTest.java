package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.dto.FilterCriteria;
import dev.akuniutka.cbrratesbot.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportController.class)
class ReportControllerTest {
    private static final Random RANDOM = new Random();
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReportService reportService;

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
        List<Date> datesFrom = Arrays.asList(null, getRandomDate());
        List<Date> datesTo = Arrays.asList(null, getRandomDate());
        int count = RANDOM.nextInt(1000);
        Map<String, Integer> expected = new HashMap<>();

        for (Long chatId : chatIds) {
            for (BigDecimal amountFrom : amountsFrom) {
                for (BigDecimal amountTo : amountsTo) {
                    for (Date dateFrom : datesFrom) {
                        for (Date dateTo : datesTo) {
                            FilterCriteria filter = new FilterCriteria(chatId, amountFrom, amountTo, dateFrom, dateTo);
                            String query = filterCriteriaToQuery(filter);
                            count += RANDOM.nextInt(1000) + 1;
                            given(reportService.getIncomesCount(filter)).willReturn((long) count);
                            expected.put(query, count);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> entry : expected.entrySet()) {
            mvc.perform(get("/reports/incomes/count" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.*", hasSize(1)))
                    .andExpect(jsonPath("$.count", is(entry.getValue())));
        }

        for (Map.Entry<String, Integer> entry : expected.entrySet()) {
            mvc.perform(get("/reports/incomes/v3/count" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.*", hasSize(1)))
                    .andExpect(jsonPath("$.count", is(entry.getValue())));
        }
    }

    @Test
    void testGetIncomesSum() throws Exception {
        List<Long> chatIds = Arrays.asList(null, RANDOM.nextLong());
        List<BigDecimal> amountsFrom = Arrays.asList(null, getRandomBigDecimal());
        List<BigDecimal> amountsTo = Arrays.asList(null, getRandomBigDecimal());
        List<Date> datesFrom = Arrays.asList(null, getRandomDate());
        List<Date> datesTo = Arrays.asList(null, getRandomDate());
        BigDecimal sum = getRandomBigDecimal();
        Map<String, BigDecimal> expected = new HashMap<>();

        for (Long chatId : chatIds) {
            for (BigDecimal amountFrom : amountsFrom) {
                for (BigDecimal amountTo : amountsTo) {
                    for (Date dateFrom : datesFrom) {
                        for (Date dateTo : datesTo) {
                            FilterCriteria filter = new FilterCriteria(chatId, amountFrom, amountTo, dateFrom, dateTo);
                            String query = filterCriteriaToQuery(filter);
                            sum = sum.add(getRandomBigDecimal());
                            given(reportService.getIncomesSum(filter)).willReturn(sum);
                            expected.put(query, sum);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, BigDecimal> entry : expected.entrySet()) {
            mvc.perform(get("/reports/incomes/sum" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.*", hasSize(1)))
                    .andExpect(jsonPath("$.sum", is(entry.getValue().doubleValue())));
        }
    }

    @Test
    void testGetCountOfExpensesAboveThresholdV1() throws Exception {
        int count = RANDOM.nextInt(1000);
        BigDecimal amount = getRandomBigDecimal();

        given(reportService.getCountOfExpensesGreaterThanV1(amount)).willReturn((long) count);

        mvc.perform(get("/reports/expenses/v1/count?amount=" + amount))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.count", is(count)));
    }

    @Test
    void testGetCountOfExpensesAboveThresholdV2() throws Exception {
        int count = RANDOM.nextInt(1000);
        BigDecimal amount = getRandomBigDecimal();

        given(reportService.getCountOfExpensesGreaterThanV2(amount)).willReturn((long) count);

        mvc.perform(get("/reports/expenses/v2/count?amount=" + amount))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.count", is(count)));
    }

    @Test
    void testGetExpensesCount() throws Exception {
        List<Long> chatIds = Arrays.asList(null, RANDOM.nextLong());
        List<BigDecimal> amountsFrom = Arrays.asList(null, getRandomBigDecimal());
        List<BigDecimal> amountsTo = Arrays.asList(null, getRandomBigDecimal());
        List<Date> datesFrom = Arrays.asList(null, getRandomDate());
        List<Date> datesTo = Arrays.asList(null, getRandomDate());
        int count = RANDOM.nextInt(1000);
        Map<String, Integer> expected = new HashMap<>();

        for (Long chatId : chatIds) {
            for (BigDecimal amountFrom : amountsFrom) {
                for (BigDecimal amountTo : amountsTo) {
                    for (Date dateFrom : datesFrom) {
                        for (Date dateTo : datesTo) {
                            FilterCriteria filter = new FilterCriteria(chatId, amountFrom, amountTo, dateFrom, dateTo);
                            String query = filterCriteriaToQuery(filter);
                            count += RANDOM.nextInt(1000) + 1;
                            given(reportService.getExpensesCount(filter)).willReturn((long) count);
                            expected.put(query, count);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> entry : expected.entrySet()) {
            mvc.perform(get("/reports/expenses/count" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.*", hasSize(1)))
                    .andExpect(jsonPath("$.count", is(entry.getValue())));
        }

        for (Map.Entry<String, Integer> entry : expected.entrySet()) {
            mvc.perform(get("/reports/expenses/v3/count" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.*", hasSize(1)))
                    .andExpect(jsonPath("$.count", is(entry.getValue())));
        }
    }

    @Test
    void testGetExpensesSum() throws Exception {
        List<Long> chatIds = Arrays.asList(null, RANDOM.nextLong());
        List<BigDecimal> amountsFrom = Arrays.asList(null, getRandomBigDecimal());
        List<BigDecimal> amountsTo = Arrays.asList(null, getRandomBigDecimal());
        List<Date> datesFrom = Arrays.asList(null, getRandomDate());
        List<Date> datesTo = Arrays.asList(null, getRandomDate());
        BigDecimal sum = getRandomBigDecimal();
        Map<String, BigDecimal> expected = new HashMap<>();

        for (Long chatId : chatIds) {
            for (BigDecimal amountFrom : amountsFrom) {
                for (BigDecimal amountTo : amountsTo) {
                    for (Date dateFrom : datesFrom) {
                        for (Date dateTo : datesTo) {
                            FilterCriteria filter = new FilterCriteria(chatId, amountFrom, amountTo, dateFrom, dateTo);
                            String query = filterCriteriaToQuery(filter);
                            sum = sum.add(getRandomBigDecimal());
                            given(reportService.getExpensesSum(filter)).willReturn(sum);
                            expected.put(query, sum);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, BigDecimal> entry : expected.entrySet()) {
            mvc.perform(get("/reports/expenses/sum" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.*", hasSize(1)))
                    .andExpect(jsonPath("$.sum", is(entry.getValue().doubleValue())));
        }
    }

    @Test
    void testGetCount() throws Exception {
        List<Long> chatIds = Arrays.asList(null, RANDOM.nextLong());
        List<Date> datesFrom = Arrays.asList(null, getRandomDate());
        List<Date> datesTo = Arrays.asList(null, getRandomDate());
        int count = RANDOM.nextInt(1000);
        Map<String, Integer> expected = new HashMap<>();

        for (Long chatId : chatIds) {
            for (Date dateFrom : datesFrom) {
                for (Date dateTo : datesTo) {
                    FilterCriteria filter = new FilterCriteria(chatId, null, null, dateFrom, dateTo);
                    String query = filterCriteriaToQuery(filter);
                    count += RANDOM.nextInt(1000) + 1;
                    given(reportService.getCount(filter)).willReturn((long) count);
                    expected.put(query, count);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : expected.entrySet()) {
            mvc.perform(get("/reports/count" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.*", hasSize(1)))
                    .andExpect(jsonPath("$.count", is(entry.getValue())));
        }
    }

    @Test
    void testGetSum() throws Exception {
        List<Long> chatIds = Arrays.asList(null, RANDOM.nextLong());
        List<Date> datesFrom = Arrays.asList(null, getRandomDate());
        List<Date> datesTo = Arrays.asList(null, getRandomDate());
        BigDecimal sum = getRandomBigDecimal();
        Map<String, BigDecimal> expected = new HashMap<>();

        for (Long chatId : chatIds) {
            for (Date dateFrom : datesFrom) {
                for (Date dateTo : datesTo) {
                    FilterCriteria filter = new FilterCriteria(chatId, null, null, dateFrom, dateTo);
                    String query = filterCriteriaToQuery(filter);
                    sum = sum.add(getRandomBigDecimal());
                    given(reportService.getSum(filter)).willReturn(sum);
                    expected.put(query, sum);
                }
            }
        }

        for (Map.Entry<String, BigDecimal> entry : expected.entrySet()) {
            mvc.perform(get("/reports/sum" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.*", hasSize(1)))
                    .andExpect(jsonPath("$.sum", is(entry.getValue().doubleValue())));
        }
    }


    private BigDecimal getRandomBigDecimal() {
        return BigDecimal.valueOf(RANDOM.nextFloat() * 1000).setScale(2, RoundingMode.HALF_UP);
    }

    private Date getRandomDate() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, RANDOM.nextInt(1000) - 1000);
        Date date = calendar.getTime();
        String formattedDate = DATE_FORMATTER.format(date);
        return DATE_FORMATTER.parse(formattedDate);
    }

    private String filterCriteriaToQuery(FilterCriteria filter) {
        StringBuilder query = new StringBuilder();
        if (filter.getChatId() != null) {
            query.append("&chatId=").append(filter.getChatId());
        }
        if (filter.getAmountFrom() != null) {
            query.append("&amountFrom=").append(filter.getAmountFrom());
        }
        if (filter.getAmountTo() != null) {
            query.append("&amountTo=").append(filter.getAmountTo());
        }
        if (filter.getDateFrom() != null) {
            query.append("&dateFrom=").append(DATE_FORMATTER.format(filter.getDateFrom()));
        }
        if (filter.getDateTo() != null) {
            query.append("&dateTo=").append(DATE_FORMATTER.format(filter.getDateTo()));
        }
        return query.length() == 0 ? "" : "?" + query.substring(1);
    }
}