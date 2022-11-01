package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.RouteDto;
import com.vladnickgo.Project.service.util.RouteRequestDtoUtil;

import java.util.List;

public interface RouteService {
    List<RouteDto> findAllByNumberOfPageAndSorting(RouteRequestDtoUtil routeRequestDtoUtil);

    Integer addRoute(RouteDto routeDto, Integer firstPortOfRouteId);

    Integer getNumberOfPages(Integer recordsOnPage);

    List<RouteDto> findAllRoutes();

}
