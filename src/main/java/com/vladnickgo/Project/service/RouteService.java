package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.RouteDto;
import com.vladnickgo.Project.service.util.RouteRequestDtoUtil;

import java.util.List;

public interface RouteService {
    List<RouteDto> findAllByNumberOfPageAndSorting(RouteRequestDtoUtil routeRequestDtoUtil);

    void addRoute(RouteDto routeDto);

    Integer getNumberOfPages(Integer recordsOnPage);
}
