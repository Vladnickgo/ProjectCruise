package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.RoutePointDto;

public class RoutePointValidator implements Validator<RoutePointDto> {

    @Override
    public void validate(RoutePointDto entity) {
//        if (entity == null) {
//            throw new IllegalArgumentException(ROUTE_POINT_DTO_IS_NULL_MESSAGE);
//        }
//        validateByParam(RoutePointDto::getRouteName, ROUTE_NAME_IS_NULL_MESSAGE, entity);
//        validateByParam(RoutePointDto::getCountryUa, COUNTRY_NAME_UA_IS_NULL_MESSAGE, entity);
//        validateByParam(RoutePointDto::getCountryEn, COUNTRY_NAME_EN_IS_NULL_MESSAGE, entity);
//        validateByParam(RoutePointDto::getPortNameEn, PORT_NAME_UA_IS_NULL_MESSAGE, entity);
//        validateByParam(RoutePointDto::getPortNameEn, PORT_NAME_EN_IS_NULL_MESSAGE, entity);
//    }
//
//    private void validateByParam(Function<RoutePointDto, String> param, String errorMessage, RoutePointDto routePointDto) {
//        Optional.ofNullable(param.apply(routePointDto))
//                .orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }
}
