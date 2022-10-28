package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.RoutePointDto;
import com.vladnickgo.Project.controller.dto.RoutePointRequestDto;

import java.util.List;

public interface RoutePointService {

    List<RoutePointDto> findAllByRouteId(Integer routeId);

    List<RoutePointDto> findAll();

    void createRoutePoint(RoutePointRequestDto routePointRequestDto);

    void deleteRoutePointById(Integer routePointId);
}
