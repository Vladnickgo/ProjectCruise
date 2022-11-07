package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.RoutePointDto;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.RoutePoint;

public class RoutePointMapper implements Mapper<RoutePointDto, RoutePoint> {
    @Override
    public RoutePoint mapDtoToEntity(RoutePointDto routePointDto) {
        return RoutePoint.newBuilder()
                .id(routePointDto.getId())
                .route(Route.newBuilder()
                        .routeName(routePointDto.getRouteName())
                        .build())
                .dayNumber(routePointDto.getDayNumber())
                .port(Port.newBuilder()
                        .id(routePointDto.getId())
                        .portNameUa(routePointDto.getPortNameUa())
                        .portNameEn(routePointDto.getPortNameEn())
                        .countryUa(routePointDto.getCountryUa())
                        .countryEn(routePointDto.getCountryEn())
                        .build())
                .build();
    }

    @Override
    public RoutePointDto mapEntityToDto(RoutePoint routePoint) {
        return RoutePointDto.newBuilder()
                .id(routePoint.getId())
                .routeName(routePoint.getRoute().getRouteName())
                .dayNumber(routePoint.getDayNumber())
                .portNameUa(routePoint.getPort().getPortNameUa())
                .portNameEn(routePoint.getPort().getPortNameEn())
                .countryUa(routePoint.getPort().getCountryUa())
                .countryEn(routePoint.getPort().getCountryEn())
                .build();
    }
}
