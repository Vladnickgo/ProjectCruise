package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.RouteDto;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class RouteValidator implements Validator<RouteDto> {
    @Override
    public void validate(RouteDto entity) {
        if (entity == null) throw new IllegalArgumentException("RouteDto is null");
        if (isBlank(entity.getRouteName()))throw new IllegalArgumentException("Route name is empty");
    }
}
