package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.RouteDto;
import com.vladnickgo.Project.dao.entity.Route;

public class RouteMapper implements Mapper<RouteDto, Route> {
    @Override
    public Route mapDtoToEntity(RouteDto routeDto) {
        if (routeDto == null) return null;
        return Route.newBuilder()
                .id(routeDto.getId())
                .routeName(routeDto.getRouteName())
                .build();
    }

    @Override
    public RouteDto mapEntityToDto(Route route) {
        if (route == null) return null;
        return RouteDto.newBuilder()
                .id(route.getId())
                .routeName(route.getRouteName())
                .build();
    }
}
