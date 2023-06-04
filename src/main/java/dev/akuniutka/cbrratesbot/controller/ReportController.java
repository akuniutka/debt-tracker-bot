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

// TODO: add "/" endpoint to list available reports
// TODO: add "/incomes" endpoint to list available reports for incomes
// TODO: add "/expenses" endpoint to list aialable reports for expenses
// TODO: add Spring Security to limit access by chat ID

@RestController
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    // TODO: add endpoints for COUNT and SUM of entries (both incomes and expenses) and with filters
    @GetMapping("/count")
    @Operation(summary = "Get the number of entries (both incomes and expenses)")
    public CountDto getEntriesCount(
            @RequestParam(name = "chatId", required = false) Long chatId,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo
    ) {
        FilterCriteria filter = new FilterCriteria(chatId, null, null, dateFrom, dateTo);
        return new CountDto(reportService.getEntriesCount(filter));
    }

    // TODO: remove the endpoint - there is a newer version (v3)
    @GetMapping("/incomes/v1/count")
    @Operation(summary = "Get the number of income entries with amount above threshold")
    public CountDto getCountOfIncomesAboveThresholdV1(@RequestParam(name = "amount") BigDecimal amount) {
        return new CountDto(reportService.getCountOfIncomesGreaterThanV1(amount));
    }

    // TODO: remove the endpoint - there is a newer version (v3)
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
        FilterCriteria filter = new FilterCriteria(chatId, amountFrom, amountTo, dateFrom, dateTo);
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

        FilterCriteria filter = new FilterCriteria(chatId, amountFrom, amountTo, dateFrom, dateTo);
        return new SumDto(reportService.getIncomesSum(filter));
    }

    // TODO: remove the endpoint - there is a newer version (v3)
    @GetMapping("/expenses/v1/count")
    @Operation(summary = "Get the number of expense entries with amount above threshold")
    public CountDto getCountOfExpensesAboveThresholdV1(@RequestParam(name = "amount") BigDecimal amount) {
        return new CountDto(reportService.getCountOfExpensesGreaterThanV1(amount));
    }

    // TODO: remove the endpoint - there is a newer version (v3)
    @GetMapping("/expenses/v2/count")
    @Operation(summary = "Get the number of expense entries with amount above threshold")
    public CountDto getCountOfExpensesAboveThresholdV2(@RequestParam(name = "amount") BigDecimal amount) {
        return new CountDto(reportService.getCountOfExpensesGreaterThanV2(amount));
    }

    @GetMapping(value = {"/expenses/v3/count", "/expenses/count"})
    @Operation(summary = "Get the number of expense entries")
    public CountDto getExpensesCount(
            @RequestParam(name = "chatId", required = false) Long chatId,
            @RequestParam(name = "amountFrom", required = false) BigDecimal amountFrom,
            @RequestParam(name = "amountTo", required = false) BigDecimal amountTo,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo
    ) {
        FilterCriteria filter = new FilterCriteria(chatId, amountFrom, amountTo, dateFrom, dateTo);
        return new CountDto(reportService.getExpensesCount(filter));
    }

    @GetMapping("/expenses/sum")
    @Operation(summary = "Get the sum of expense entries")
    public SumDto getExpensesSum(
            @RequestParam(name = "chatId", required = false) Long chatId,
            @RequestParam(name = "amountFrom", required = false) BigDecimal amountFrom,
            @RequestParam(name = "amountTo", required = false) BigDecimal amountTo,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo
    ) {
        FilterCriteria filter = new FilterCriteria(chatId, amountFrom, amountTo, dateFrom, dateTo);
        return new SumDto(reportService.getExpensesSum(filter));
    }
}
