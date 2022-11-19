package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.RoutePointRequestDto;

import java.util.Optional;
import java.util.function.Function;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.*;

public class RoutePointRequestValidator implements Validator<RoutePointRequestDto> {

    public static final RoutePointRequestDto EMPTY_ROUTE_POINT_REQUEST_DTO = RoutePointRequestDto.newBuilder().build();

    @Override
    public void validate(RoutePointRequestDto entity) {
        if (entity == null) {
            throw new IllegalArgumentException(ROUTE_POINT_REQUEST_DTO_IS_NULL_MESSAGE);
        }
        if (entity.equals(EMPTY_ROUTE_POINT_REQUEST_DTO)) {
            throw new IllegalArgumentException(ROUTE_POINT_REQUEST_DTO_IS_EMPTY_MESSAGE);
        }
        validateByParam(RoutePointRequestDto::getRoutePointId, ROUTE_POINT_ID_IS_NULL_MESSAGE, entity);
        validateByParam(RoutePointRequestDto::getRouteId, ROUTE_ID_IS_NULL_MESSAGE, entity);
        validateByParam(RoutePointRequestDto::getDayNumber, DAY_NUMBER_IS_NULL, entity);
        validateByParam(RoutePointRequestDto::getPortId, PORT_ID_IS_NULL, entity);
    }

    private void validateByParam(Function<RoutePointRequestDto, Integer> param, String errorMessage, RoutePointRequestDto routePointRequestDto) {
        Optional.ofNullable(param.apply(routePointRequestDto))
                .orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }
}
