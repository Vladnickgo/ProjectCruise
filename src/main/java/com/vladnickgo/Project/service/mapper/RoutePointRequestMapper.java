package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.RoutePointRequestDto;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.RoutePoint;

public class RoutePointRequestMapper implements Mapper<RoutePointRequestDto, RoutePoint> {
    @Override
    public RoutePoint mapDtoToEntity(RoutePointRequestDto routePointRequestDto) {
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
        return RoutePointRequestDto.newBuilder()
                .routePointId(routePoint.getId())
                .routeId(routePoint.getRoute().getId())
                .dayNumber(routePoint.getDayNumber())
                .portId(routePoint.getPort().getId())
                .build();
    }
}
