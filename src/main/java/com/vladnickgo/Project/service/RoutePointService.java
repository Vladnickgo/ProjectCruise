package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.RoutePointDto;

import java.util.List;

public interface RoutePointService {

    List<RoutePointDto> findAllByRouteId(Integer routeId);
    List<RoutePointDto> findAll();
}
