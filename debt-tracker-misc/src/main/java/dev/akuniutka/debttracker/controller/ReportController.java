package dev.akuniutka.debttracker.controller;

import dev.akuniutka.debttracker.service.ReportService;
import dev.akuniutka.debttracker.dto.CountDto;
import dev.akuniutka.debttracker.dto.FilterCriteria;
import dev.akuniutka.debttracker.dto.SimpleFilterCriteria;
import dev.akuniutka.debttracker.dto.SumDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CountDto getCount(SimpleFilterCriteria filter) {
        return new CountDto(reportService.getCount(filter));
    }

    @GetMapping("/sum")
    @Operation(summary = "Get the sum of entries (both incomes and expenses)")
    public SumDto getSum(SimpleFilterCriteria filter) {
        return new SumDto(reportService.getSum(filter));
    }

    @GetMapping("/incomes/count")
    @Operation(summary = "Get the number of income entries")
    public CountDto getIncomesCount(FilterCriteria filter) {
        return new CountDto(reportService.getIncomesCount(filter));
    }

    @GetMapping("/incomes/sum")
    @Operation(summary = "Get the sum of income entries")
    public SumDto getIncomesSum(FilterCriteria filter) {
        return new SumDto(reportService.getIncomesSum(filter));
    }

    @GetMapping("/expenses/count")
    @Operation(summary = "Get the number of expense entries")
    public CountDto getExpensesCount(FilterCriteria filter) {
        return new CountDto(reportService.getExpensesCount(filter));
    }

    @GetMapping("/expenses/sum")
    @Operation(summary = "Get the sum of expense entries")
    public SumDto getExpensesSum(FilterCriteria filter) {
        return new SumDto(reportService.getExpensesSum(filter));
    }
}
