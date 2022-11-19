package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.RouteDto;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class RouteValidator implements Validator<RouteDto> {
    @Override
    public void validate(RouteDto entity) {
        if (entity == null) throw new IllegalArgumentException("RouteDto is null");
        if (Objects.equals(entity,RouteDto.newBuilder().build()))throw new IllegalArgumentException("RouteDto is empty");
        if (isBlank(entity.getRouteName()))throw new IllegalArgumentException("Route name is empty");
    }
}
