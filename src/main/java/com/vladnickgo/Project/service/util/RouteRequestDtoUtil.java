package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.RouteRequestDto;

import java.util.Objects;

public class RouteRequestDtoUtil {
    private final RouteRequestDto routeRequestDto;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 4;

    public RouteRequestDtoUtil(RouteRequestDto routeRequestDto) {
        this.routeRequestDto = routeRequestDto;
    }

    public Integer getNumberOfPage() {
        return initParameterValue(routeRequestDto.getNumberOfPage(), DEFAULT_PAGE_NUMBER);
    }

    public Integer getItemsOnPage() {
        return initParameterValue(routeRequestDto.getRecordsOnPage(), DEFAULT_ITEMS_ON_PAGE);
    }

    private Integer initParameterValue(String parameter, Integer defaultValue) {
        try {
            return Objects.equals(null, parameter) ? defaultValue : Integer.valueOf(parameter);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public Integer getFirstRecordOnPage() {
        return (getNumberOfPage() - 1) * getItemsOnPage();
    }

}
