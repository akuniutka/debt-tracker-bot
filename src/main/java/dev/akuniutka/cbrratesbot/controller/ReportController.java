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
import java.util.Date;

// TODO: add "/" endpoint to list available reports
// TODO: add "/incomes" endpoint to list available reports for incomes
// TODO: add "/expenses" endpoint to list available reports for expenses
// TODO: add Spring Security to limit access by chat ID

@RestController
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/count")
    @Operation(summary = "Get the number of entries (both incomes and expenses)")
    public CountDto getCount(
            @RequestParam(name = "chatId", required = false) Long chatId,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo
    ) {
        FilterCriteria filter = new FilterCriteria(chatId, null, null, dateFrom, dateTo);
        return new CountDto(reportService.getCount(filter));
    }

    @GetMapping("/sum")
    @Operation(summary = "Get the sum of entries (both incomes and expenses)")
    public SumDto getSum(
            @RequestParam(name = "chatId", required = false) Long chatId,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo
    ) {
        FilterCriteria filter = new FilterCriteria(chatId, null, null, dateFrom, dateTo);
        return new SumDto(reportService.getSum(filter));
    }

    @GetMapping("/incomes/count")
    @Operation(summary = "Get the number of income entries")
    public CountDto getIncomesCount(
            @RequestParam(name = "chatId", required = false) Long chatId,
            @RequestParam(name = "amountFrom", required = false) BigDecimal amountFrom,
            @RequestParam(name = "amountTo", required = false) BigDecimal amountTo,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo
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
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo
    ) {
        FilterCriteria filter = new FilterCriteria(chatId, amountFrom, amountTo, dateFrom, dateTo);
        return new SumDto(reportService.getIncomesSum(filter));
    }

    @GetMapping("/expenses/count")
    @Operation(summary = "Get the number of expense entries")
    public CountDto getExpensesCount(
            @RequestParam(name = "chatId", required = false) Long chatId,
            @RequestParam(name = "amountFrom", required = false) BigDecimal amountFrom,
            @RequestParam(name = "amountTo", required = false) BigDecimal amountTo,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo
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
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo
    ) {
        FilterCriteria filter = new FilterCriteria(chatId, amountFrom, amountTo, dateFrom, dateTo);
        return new SumDto(reportService.getExpensesSum(filter));
    }
}
