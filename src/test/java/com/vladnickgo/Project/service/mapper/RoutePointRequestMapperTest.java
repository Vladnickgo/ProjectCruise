package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.RoutePointRequestDto;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.RoutePoint;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoutePointRequestMapperTest {
    private final Mapper<RoutePointRequestDto, RoutePoint> mapper = new RoutePointRequestMapper();

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapRoutePointToRoutePointRequestDtoMethod")
    void checkMapEntityToDtoMethod(RoutePoint routePoint, RoutePointRequestDto expectedDto, String message) {
        RoutePointRequestDto actualDto = mapper.mapEntityToDto(routePoint);
        assertEquals(expectedDto, actualDto, message);
    }

    private static Stream<Arguments> provideDataForMapRoutePointToRoutePointRequestDtoMethod() {
        return Stream.of(
                Arguments.of(
                        RoutePoint.newBuilder()
                                .id(1)
                                .route(Route.newBuilder()
                                        .id(1)
                                        .routeName("nordic")
                                        .build())
                                .port(Port.newBuilder()
                                        .id(1)
                                        .portNameUa("Одеса")
                                        .portNameEn("Odessa")
                                        .countryUa("Україна")
                                        .countryUa("Ukraine")
                                        .build())
                                .dayNumber(1)
                                .build(),
                        RoutePointRequestDto.newBuilder()
                                .routePointId(1)
                                .portId(1)
                                .routeId(1)
                                .dayNumber(1)
                                .build(),
                        "Check mapRoutePointToRoutePointRequestDto method"),
                Arguments.of(RoutePoint.newBuilder()
                                .id(2)
                                .route(Route.newBuilder()
                                        .id(2)
                                        .routeName("nordic")
                                        .build())
                                .port(Port.newBuilder()
                                        .id(2)
                                        .portNameUa("Неаполь")
                                        .portNameEn("Naples")
                                        .countryUa("Італія")
                                        .countryUa("Italy")
                                        .build())
                                .dayNumber(2)
                                .build(),
                        RoutePointRequestDto.newBuilder()
                                .routePointId(2)
                                .portId(2)
                                .routeId(2)
                                .dayNumber(2)
                                .build(),
                        "Check mapRoutePointToRoutePointRequestDto method"
                ),
                Arguments.of(RoutePoint.newBuilder()
                                .id(3)
                                .route(Route.newBuilder()
                                        .id(2)
                                        .routeName("nordic")
                                        .build())
                                .port(Port.newBuilder()
                                        .id(2)
                                        .portNameUa("Генуя")
                                        .portNameEn("Genoa")
                                        .countryUa("Італія")
                                        .countryUa("Italy")
                                        .build())
                                .dayNumber(2)
                                .build(),
                        RoutePointRequestDto.newBuilder()
                                .routePointId(3)
                                .portId(2)
                                .routeId(2)
                                .dayNumber(2)
                                .build(),
                        "Check mapRoutePointToRoutePointRequestDto method"
                ));
    }
}
