package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.LocalDateDto;

import java.time.LocalDate;
import java.util.Objects;

public class LocalDateDtoUtil {
    private static final LocalDate DEFAULT_DATE_START = LocalDate.now();
    private static final LocalDate DEFAULT_DATE_END = LocalDate.now().plusMonths(1);
    private final String dateStart;
    private final String dateEnd;

    public LocalDateDtoUtil(String dateStart, String dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public LocalDateDto getLocalDateDto() {
        LocalDate localDateStart = initDate(dateStart, DEFAULT_DATE_START, "");
        LocalDate localDateEnd = initDate(dateEnd, DEFAULT_DATE_END, "");
        return LocalDateDto.newBuilder()
                .dateStart(localDateStart)
                .dateEnd(localDateEnd)
                .build();
    }

    private LocalDate initDate(String date, LocalDate defaultValue, String errorMessage) {
        try {
            return Objects.equals(date, null) || date.isBlank() ? defaultValue:LocalDate.parse(date);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public LocalDate getMinDateStart(){
        return DEFAULT_DATE_START;
    }

    public LocalDate getMaxDateStart(){
        return getLocalDateDto().getDateEnd().minusDays(1);
    }

    public LocalDate getMinDateEnd(){
        return getLocalDateDto().getDateStart().plusDays(1);
    }

    public LocalDate getMaxDateEnd(){
        return getLocalDateDto().getDateStart().plusYears(1);
    }
}
