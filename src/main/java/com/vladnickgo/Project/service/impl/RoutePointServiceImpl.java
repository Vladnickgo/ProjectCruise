package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.RoutePointDto;
import com.vladnickgo.Project.controller.dto.RoutePointRequestDto;
import com.vladnickgo.Project.dao.RoutePointDao;
import com.vladnickgo.Project.dao.entity.RoutePoint;
import com.vladnickgo.Project.service.RoutePointService;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.validator.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoutePointServiceImpl implements RoutePointService {
    private final RoutePointDao routePointRepository;
    private final Mapper<RoutePointDto, RoutePoint> routePointMapper;
    private final Mapper<RoutePointRequestDto, RoutePoint> routePointRequestDtoRoutePointMapper;
    private final Validator<RoutePointRequestDto> validator;

    public RoutePointServiceImpl(RoutePointDao routePointRepository, Mapper<RoutePointDto, RoutePoint> routePointMapper, Mapper<RoutePointRequestDto, RoutePoint> routePointRequestDtoRoutePointMapper, Validator<RoutePointRequestDto> validator) {
        this.routePointRepository = routePointRepository;
        this.routePointMapper = routePointMapper;
        this.routePointRequestDtoRoutePointMapper = routePointRequestDtoRoutePointMapper;
        this.validator = validator;
    }

    @Override
    public List<RoutePointDto> findAllByRouteId(Integer routeId) {
        return routePointRepository.findAllByRouteId(routeId).stream()
                .map(routePointMapper::mapEntityToDto)
                .sorted(Comparator.comparing(RoutePointDto::getDayNumber))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoutePointDto> findAll() {
        return routePointRepository.findAll().stream()
                .map(routePointMapper::mapEntityToDto)
                .sorted(Comparator.comparing(RoutePointDto::getDayNumber))
                .collect(Collectors.toList());
    }

    @Override
    public void createRoutePoint(RoutePointRequestDto routePointRequestDto) {
        validator.validate(routePointRequestDto);
        RoutePoint routePoint = routePointRequestDtoRoutePointMapper.mapDtoToEntity(routePointRequestDto);
        routePointRepository.save(routePoint);
    }

    @Override
    public void deleteRoutePointById(Integer routePointId) {
        routePointRepository.deleteById(routePointId);
    }
}
