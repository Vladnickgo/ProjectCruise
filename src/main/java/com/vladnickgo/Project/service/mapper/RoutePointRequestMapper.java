package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.RoutePointRequestDto;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.RoutePoint;

import java.util.Optional;

public class RoutePointRequestMapper implements Mapper<RoutePointRequestDto, RoutePoint> {
    @Override
    public RoutePoint mapDtoToEntity(RoutePointRequestDto routePointRequestDto) {
        if (routePointRequestDto == null) return null;
        return RoutePoint.newBuilder()
                .id(routePointRequestDto.getRoutePointId())
                .route(Route.newBuilder()
                        .id(routePointRequestDto.getRouteId())
                        .build())
                .dayNumber(routePointRequestDto.getDayNumber())
                .port(Port.newBuilder()
                        .id(routePointRequestDto.getPortId())
                        .build())
                .build();
    }

    @Override
    public RoutePointRequestDto mapEntityToDto(RoutePoint routePoint) {
        if (routePoint == null) return null;
        return RoutePointRequestDto.newBuilder()
                .routePointId(routePoint.getId())
                .routeId(getRouteId(routePoint))
                .dayNumber(routePoint.getDayNumber())
                .portId(getPortId(routePoint))
                .build();
    }

    private Integer getPortId(RoutePoint routePoint) {
        return Optional.ofNullable(routePoint.getPort())
                .map(Port::getId)
                .orElse(null);
    }

    private Integer getRouteId(RoutePoint routePoint) {
        return Optional.ofNullable(routePoint.getRoute())
                .map(Route::getId)
                .orElse(null);
    }
}
