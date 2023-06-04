package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.dto.CountDto;
import dev.akuniutka.cbrratesbot.dto.FilterCriteria;
import dev.akuniutka.cbrratesbot.dto.SumDto;
import dev.akuniutka.cbrratesbot.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    // TODO: add root enpoint to list all reports
    // TODO: /incomes and /expenses endpoints to list income reports and expenses reports respectively
    // TODO: add Spring Security to limit access to data in reports by chatId

    // TODO: add endpoints for COUNT and SUM of incomes and expeses togather and with filters


    @GetMapping("/incomes/v1/count")
    @Operation(summary = "Get the number of income entries with amount above threshold")
    public CountDto getCountOfIncomesAboveThresholdV1(@RequestParam(name = "amount") BigDecimal amount) {
        return new CountDto(reportService.getCountOfIncomesGreaterThanV1(amount));
    }

    @GetMapping("/incomes/v2/count")
    @Operation(summary = "Get the number of income entries with amount above threshold")
    public CountDto getCountOfIncomesAboveThresholdV2(@RequestParam(name = "amount") BigDecimal amount) {
        return new CountDto(reportService.getCountOfIncomesGreaterThanV2(amount));
    }

    @GetMapping(value = {"/incomes/v3/count", "/incomes/count"})
    @Operation(summary = "Get the number of income entries")
    public CountDto getIncomesCount(
            @RequestParam(name = "chatId", required = false) Long chatId,
            @RequestParam(name = "amountFrom", required = false) BigDecimal amountFrom,
            @RequestParam(name = "amountTo", required = false) BigDecimal amountTo,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo
    ) {
        FilterCriteria filter = new FilterCriteria();
        filter.setChatId(chatId);
        filter.setAmountFrom(amountFrom);
        filter.setAmountTo(amountTo);
        filter.setDateFrom(dateFrom);
        filter.setDateTo(dateTo);
        return new CountDto(reportService.getIncomesCount(filter));
    }

    @GetMapping("/incomes/sum")
    @Operation(summary = "Get the sum of income entries")
    public SumDto getIncomesSum(
            @RequestParam(name = "chatId", required = false) Long chatId,
            @RequestParam(name = "amountFrom", required = false) BigDecimal amountFrom,
            @RequestParam(name = "amountTo", required = false) BigDecimal amountTo,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo
    ) {

        FilterCriteria filter = new FilterCriteria();
        filter.setChatId(chatId);
        filter.setAmountFrom(amountFrom);
        filter.setAmountTo(amountTo);
        filter.setDateFrom(dateFrom);
        filter.setDateTo(dateTo);
        return new SumDto(reportService.getIncomesSum(filter));
    }

    // TODO: add amountTo, dateFrom and dateTo filter
    // TODO: add filter by chatId
    // TODO: add SUM for expenses
    @GetMapping("/expenses/count")
    @Operation(summary = "Получить количество расходных операций")
    public CountDto getCountOfExpensesGreaterThan(@RequestParam(name = "amount", required = false) BigDecimal amount) {
        return new CountDto(reportService.getCountOfExpensesGreaterThan(amount));
    }
}
