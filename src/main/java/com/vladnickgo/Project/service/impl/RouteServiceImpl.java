package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.RouteDto;
import com.vladnickgo.Project.dao.RouteDao;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.service.RouteService;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.RouteRequestDtoUtil;
import com.vladnickgo.Project.validator.Validator;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class RouteServiceImpl implements RouteService {
    private final RouteDao routeRepository;
    private final Mapper<RouteDto, Route> mapper;
    private final Validator<RouteDto> routeDtoValidator;

    public RouteServiceImpl(RouteDao routeDao, Mapper<RouteDto, Route> mapper, Validator<RouteDto> routeDtoValidator) {
        this.routeRepository = routeDao;
        this.mapper = mapper;
        this.routeDtoValidator = routeDtoValidator;
    }

    @Override
    public List<RouteDto> findAllByNumberOfPageAndSorting(RouteRequestDtoUtil routeRequestDtoUtil) {
        return routeRepository.findAllByNumberOfPageAndSorting(routeRequestDtoUtil)
                .stream()
                .map(mapper::mapEntityToDto)
                .sorted(Comparator.comparing(t->t.getRouteName().toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer addRoute(RouteDto routeDto, Integer firstPortOfRouteId) {
        routeDtoValidator.validate(routeDto);
        Route route = mapper.mapDtoToEntity(routeDto);
        try {
            return routeRepository.addRouteAndRoutePoint(route, firstPortOfRouteId);
        }catch (SQLException e){
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public Integer getNumberOfPages(Integer recordsOnPage) {
        Integer countAll = routeRepository.countAll();
        return countAll / recordsOnPage + (countAll % recordsOnPage > 0 ? 1 : 0);
    }
}
