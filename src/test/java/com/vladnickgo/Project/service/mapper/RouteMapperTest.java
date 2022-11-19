package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.RouteDto;
import com.vladnickgo.Project.dao.entity.Route;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RouteMapperTest {
    @InjectMocks
    private RouteMapper routeMapper;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapRouteDtoToRoute")
    void checkMapDtoToEntity(RouteDto routeDto, Route expectedRoute, String message) {
        Route actualRoute = routeMapper.mapDtoToEntity(routeDto);
        assertEquals(expectedRoute, actualRoute, message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapRouteToRouteDto")
    void checkMapEntityToDto(Route route, RouteDto expectedRouteDto, String message) {
        RouteDto actualRouteDto = routeMapper.mapEntityToDto(route);
        assertEquals(expectedRouteDto, actualRouteDto, message);
    }

    private static Stream<Arguments> provideDataForMapRouteDtoToRoute() {
        return Stream.of(
                Arguments.of(
                        RouteDto.newBuilder()
                                .id(1)
                                .routeName("european")
                                .build(),
                        Route.newBuilder()
                                .id(1)
                                .routeName("european")
                                .build(),
                        "Check mapRouteDtoToEntity method"),
                Arguments.of(
                        RouteDto.newBuilder()
                                .build(),
                        Route.newBuilder()
                                .build(),
                        "Check mapRouteDtoToEntity method with empty values"),
                Arguments.of(null, null,
                        "Check mapRouteDtoToEntity method with null values")
        );
    }

    private static Stream<Arguments> provideDataForMapRouteToRouteDto() {
        return Stream.of(
                Arguments.of(
                        Route.newBuilder()
                                .id(1)
                                .routeName("european")
                                .build(),
                        RouteDto.newBuilder()
                                .id(1)
                                .routeName("european")
                                .build(),
                        "Check mapEntityToRouteDto method"),
                Arguments.of(
                        Route.newBuilder()
                                .build(),
                        RouteDto.newBuilder()
                                .build(),
                        "Check mapEntityToRouteDto method with empty values"),
                Arguments.of(null, null,
                        "Check mapEntityToRouteDto method with null values")
        );
    }
}