package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.RouteDto;
import com.vladnickgo.Project.dao.RouteDao;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.service.RouteService;
import com.vladnickgo.Project.service.impl.exception.EntityAlreadyExistException;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.RouteRequestDtoUtil;
import com.vladnickgo.Project.validator.Validator;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.FIRST_PORT_ID_IS_NULL;
import static com.vladnickgo.Project.validator.ValidatorErrorMessage.ROUTE_ALREADY_EXIST_ERROR_MESSAGE;

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
                .sorted(Comparator.comparing(t -> t.getRouteName().toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer addRoute(RouteDto routeDto, String firstPortOfRouteId) {
        int firstPortId;
        try{firstPortId = Integer.parseInt(firstPortOfRouteId);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(FIRST_PORT_ID_IS_NULL);
        }
        routeDtoValidator.validate(routeDto);
        Route route = mapper.mapDtoToEntity(routeDto);
        if (routeRepository.findByName(route.getRouteName()).isPresent()) {
            String message = String.format(ROUTE_ALREADY_EXIST_ERROR_MESSAGE, route.getRouteName());
            throw new EntityAlreadyExistException(message);
        }
        try {
            return routeRepository.addRouteAndRoutePoint(route, firstPortId);
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public Integer getNumberOfPages(Integer recordsOnPage) {
        Integer countAll = routeRepository.countAll();
        return countAll / recordsOnPage + (countAll % recordsOnPage > 0 ? 1 : 0);
    }

    @Override
    public List<RouteDto> findAllRoutes() {
        return routeRepository.findAll().stream()
                .map(mapper::mapEntityToDto)
                .sorted(Comparator.comparing(RouteDto::getRouteName))
                .collect(Collectors.toList());
    }


}
