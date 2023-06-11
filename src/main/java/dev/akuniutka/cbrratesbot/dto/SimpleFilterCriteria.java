package dev.akuniutka.cbrratesbot.dto;

import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@EqualsAndHashCode
public class SimpleFilterCriteria {
    protected Long chatId;
    protected LocalDate dateFrom;
    protected LocalDate dateTo;

    public SimpleFilterCriteria() {}

    public SimpleFilterCriteria(Long chatId, String dateFrom, String dateTo) {
        this.chatId = chatId;
        setDateFrom(dateFrom);
        setDateTo(dateTo);
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Date getDateFrom() {
        return dateFrom == null ? null : java.sql.Date.valueOf(dateFrom);
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom == null ? null : LocalDate.parse(dateFrom, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public Date getDateTo() {
        return dateTo == null ? null : java.sql.Date.valueOf(dateTo);
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo == null ? null : LocalDate.parse(dateTo, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
