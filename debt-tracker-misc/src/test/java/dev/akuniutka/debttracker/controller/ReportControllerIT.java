package dev.akuniutka.debttracker.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ReportControllerIT {
    @Autowired
    private ReportController reportController;
    private static List<Long> chatIds;
    private static List<LocalDate> datesFrom;
    private static List<LocalDate> datesTo;
    private static List<BigDecimal> amountsFrom;
    private static List<BigDecimal> amountsTo;

    @BeforeAll
    static void prepareTestData() {
        chatIds = Arrays.asList(null, 123456789L);
        amountsFrom = Arrays.asList(null, BigDecimal.valueOf(100.00));
        amountsTo = Arrays.asList(null, BigDecimal.valueOf(500.00));
        datesFrom = Arrays.asList(null, LocalDate.of(2023,1,1));
        datesTo = Arrays.asList(null, LocalDate.of(2023,6,1));
    }

    @Test
    void testGetCount() {
        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    String queryParameters = getQueryParameters(chatId, dateFrom, dateTo);
                    long expected = getExpectedCount(chatId, dateFrom, dateTo);
                    String uri = "/reports/count" + queryParameters;
                    WebTestClient
                            .bindToController(reportController)
                            .build()
                            .get()
                            .uri(uri)
                            .exchange()
                            .expectStatus().isOk()
                            .expectHeader().valueEquals("Content-Type", "application/json")
                            .expectBody().json("{\"count\":" + expected + "}");
                }
            }
        }
    }

    @Test
    void testGetSum() {
        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    String queryParameters = getQueryParameters(chatId, dateFrom, dateTo);
                    BigDecimal expected = getExpectedSum(chatId, dateFrom, dateTo);
                    String uri = "/reports/sum" + queryParameters;
                    WebTestClient
                            .bindToController(reportController)
                            .build()
                            .get()
                            .uri(uri)
                            .exchange()
                            .expectStatus().isOk()
                            .expectHeader().valueEquals("Content-Type", "application/json")
                            .expectBody().json("{\"sum\":" + expected + "}");
                }
            }
        }
    }

    @Test
    void testGetIncomesCount() {
        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    for (BigDecimal amountFrom : amountsFrom) {
                        for (BigDecimal amountTo : amountsTo) {
                            String queryParameters = getQueryParameters(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            long expected = getExpectedIncomesCount(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            String uri = "/reports/incomes/count" + queryParameters;
                            WebTestClient
                                    .bindToController(reportController)
                                    .build()
                                    .get()
                                    .uri(uri)
                                    .exchange()
                                    .expectStatus().isOk()
                                    .expectHeader().valueEquals("Content-Type", "application/json")
                                    .expectBody().json("{\"count\":" + expected + "}");
                        }
                    }
                }
            }
        }
    }

    @Test
    void testGetIncomesSum() {
        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    for (BigDecimal amountFrom : amountsFrom) {
                        for (BigDecimal amountTo : amountsTo) {
                            String queryParameters = getQueryParameters(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            BigDecimal expected = getExpectedIncomesSum(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            String uri = "/reports/incomes/sum" + queryParameters;
                            WebTestClient
                                    .bindToController(reportController)
                                    .build()
                                    .get()
                                    .uri(uri)
                                    .exchange()
                                    .expectStatus().isOk()
                                    .expectHeader().valueEquals("Content-Type", "application/json")
                                    .expectBody().json("{\"sum\":" + expected + "}");
                        }
                    }
                }
            }
        }
    }

    @Test
    void testGetExpensesCount() {
        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    for (BigDecimal amountFrom : amountsFrom) {
                        for (BigDecimal amountTo : amountsTo) {
                            String queryParameters = getQueryParameters(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            long expected = getExpectedExpensesCount(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            String uri = "/reports/expenses/count" + queryParameters;
                            WebTestClient
                                    .bindToController(reportController)
                                    .build()
                                    .get()
                                    .uri(uri)
                                    .exchange()
                                    .expectStatus().isOk()
                                    .expectHeader().valueEquals("Content-Type", "application/json")
                                    .expectBody().json("{\"count\":" + expected + "}");
                        }
                    }
                }
            }
        }
    }

    @Test
    void testGetExpensesSum() {
        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    for (BigDecimal amountFrom : amountsFrom) {
                        for (BigDecimal amountTo : amountsTo) {
                            String queryParameters = getQueryParameters(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            BigDecimal expected = getExpectedExpensesSum(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            String uri = "/reports/expenses/sum" + queryParameters;
                            WebTestClient
                                    .bindToController(reportController)
                                    .build()
                                    .get()
                                    .uri(uri)
                                    .exchange()
                                    .expectStatus().isOk()
                                    .expectHeader().valueEquals("Content-Type", "application/json")
                                    .expectBody().json("{\"sum\":" + expected + "}");
                        }
                    }
                }
            }
        }
    }

    private String getQueryParameters(Long chatId, LocalDate dateFrom, LocalDate dateTo, BigDecimal amountFrom, BigDecimal amountTo) {
        StringBuilder query = new StringBuilder();
        if (chatId != null) {
            query.append("&chatId=").append(chatId);
        }
        if (dateFrom != null) {
            query.append("&dateFrom=").append(dateFrom);
        }
        if (dateTo != null) {
            query.append("&dateTo=").append(dateTo);
        }
        if (amountFrom != null) {
            query.append("&amountFrom=").append(amountFrom);
        }
        if (amountTo != null) {
            query.append("&amountTo=").append(amountTo);
        }
        return query.length() == 0 ? "" : "?" + query.substring(1);
    }

    private String getQueryParameters(Long chatId, LocalDate dateFrom, LocalDate dateTo) {
        return getQueryParameters(chatId, dateFrom, dateTo, null, null);
    }

    private long getExpectedIncomesCount(Long chatId, LocalDate dateFrom, LocalDate dateTo, BigDecimal amountFrom, BigDecimal amountTo) {
        long count = 3L;
        count += chatId == null ? 2 : 0;
        count += amountFrom == null ? 2 : 0;
        count += amountTo == null ? 2 : 0;
        count += dateFrom == null ? 2 : 0;
        count += dateTo == null ? 2 : 0;
        return count;
    }

    private BigDecimal getExpectedIncomesSum(Long chatId, LocalDate dateFrom, LocalDate dateTo, BigDecimal amountFrom, BigDecimal amountTo) {
        BigDecimal sum = BigDecimal.valueOf(700.00).setScale(2, RoundingMode.HALF_UP);
        sum = sum.add(BigDecimal.valueOf(chatId == null ? 600.00 : 0.0));
        sum = sum.add(BigDecimal.valueOf(amountFrom == null ? 125.00 : 0.0));
        sum = sum.add(BigDecimal.valueOf(amountTo == null ? 1500.00 : 0.0));
        sum = sum.add(BigDecimal.valueOf(dateFrom == null ? 600.00 : 0.0));
        sum = sum.add(BigDecimal.valueOf(dateTo == null ? 600.00 : 0.0));
        return sum;
    }

    private long getExpectedExpensesCount(Long chatId, LocalDate dateFrom, LocalDate dateTo, BigDecimal amountFrom, BigDecimal amountTo) {
        return getExpectedIncomesCount(chatId, dateFrom, dateTo, amountFrom, amountTo);
    }

    private BigDecimal getExpectedExpensesSum(Long chatId, LocalDate dateFrom, LocalDate dateTo, BigDecimal amountFrom, BigDecimal amountTo) {
        return getExpectedIncomesSum(chatId, dateFrom, dateTo, amountFrom, amountTo);
    }

    private long getExpectedCount(Long chatId, LocalDate dateFrom, LocalDate dateTo) {
        return getExpectedIncomesCount(chatId, dateFrom, dateTo, null, null) +
                getExpectedExpensesCount(chatId, dateFrom, dateTo, null, null);
    }

    private BigDecimal getExpectedSum(Long chatId, LocalDate dateFrom, LocalDate dateTo) {
        return getExpectedIncomesSum(chatId, dateFrom, dateTo, null, null)
                .subtract(getExpectedExpensesSum(chatId, dateFrom, dateTo, null, null));
    }
}
