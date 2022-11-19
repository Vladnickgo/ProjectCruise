package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.RoutePointDto;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.RoutePoint;

import java.util.Optional;

public class RoutePointMapper implements Mapper<RoutePointDto, RoutePoint> {
    @Override
    public RoutePoint mapDtoToEntity(RoutePointDto routePointDto) {
        if (routePointDto == null) return null;
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
        if (routePoint == null) return null;
        return RoutePointDto.newBuilder()
                .id(routePoint.getId())
                .routeName(getRouteName(routePoint))
                .dayNumber(routePoint.getDayNumber())
                .portNameUa(getPortNameUa(routePoint))
                .portNameEn(getPortNameEn(routePoint))
                .countryUa(getCountryUa(routePoint))
                .countryEn(getCountryEn(routePoint))
                .build();
    }

    private String getPortNameUa(RoutePoint routePoint) {
        return Optional.ofNullable(routePoint.getPort())
                .map(Port::getPortNameUa)
                .orElse(null);
    }

    private String getPortNameEn(RoutePoint routePoint) {
        return Optional.ofNullable(routePoint.getPort())
                .map(Port::getPortNameEn)
                .orElse(null);
    }

    private String getCountryUa(RoutePoint routePoint) {
        return Optional.ofNullable(routePoint.getPort())
                .map(Port::getCountryUa)
                .orElse(null);
    }

    private String getCountryEn(RoutePoint routePoint) {
        return Optional.ofNullable(routePoint.getPort())
                .map(Port::getCountryEn)
                .orElse(null);
    }

    private String getRouteName(RoutePoint routePoint) {
        return Optional.ofNullable(routePoint.getRoute())
                .map(Route::getRouteName)
                .orElse(null);
    }
}
