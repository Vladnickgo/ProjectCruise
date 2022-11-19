package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.RoutePointRequestDto;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.RoutePoint;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RoutePointRequestMapperTest {
    @InjectMocks
    private RoutePointRequestMapper mapper;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapRoutePointToRoutePointRequestDtoMethod")
    void checkMapEntityToDtoMethod(RoutePoint routePoint, RoutePointRequestDto expectedDto, String message) {
        RoutePointRequestDto actualDto = mapper.mapEntityToDto(routePoint);
        assertEquals(expectedDto, actualDto, message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapRoutePointRequestDtoToEntityMethod")
    void checkMapDtoToEntityMethod(RoutePointRequestDto routePointRequestDto, RoutePoint expected, String message) {
        RoutePoint actual = mapper.mapDtoToEntity(routePointRequestDto);
        assertEquals(expected, actual, message);
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
                        "Check mapRoutePointToRoutePointRequestDtoToEntity method"),
                Arguments.of(RoutePoint.newBuilder()
                                .build(),
                        RoutePointRequestDto.newBuilder()
                                .build(),
                        "Check mapRoutePointToRoutePointRequestDtoToEntity method with empty values"
                ),
                Arguments.of(null, null,
                        "Check mapRoutePointToRoutePointRequestDtoToEntity method with null values"
                ));
    }

    private static Stream<Arguments> provideDataForMapRoutePointRequestDtoToEntityMethod() {
        return Stream.of(
                Arguments.of(
                        RoutePointRequestDto.newBuilder()
                                .routePointId(1)
                                .portId(1)
                                .routeId(1)
                                .dayNumber(1)
                                .build(),
                        RoutePoint.newBuilder()
                                .id(1)
                                .route(Route.newBuilder()
                                        .id(1)
                                        .build())
                                .port(Port.newBuilder()
                                        .id(1)
                                        .build())
                                .dayNumber(1)
                                .build(),
                        "Check mapDtoToEntity method"
                ),
                Arguments.of(
                        RoutePointRequestDto.newBuilder()
                                .build(),
                        RoutePoint.newBuilder()
                                .id(null)
                                .route(Route.newBuilder()
                                        .build())
                                .dayNumber(null)
                                .port(Port.newBuilder()
                                        .build())
                                .build(),
                        "Check mapDtoToEntity method with empty values"
                ),
                Arguments.of(null,
                        null,
                        "Check mapDtoToEntity method with null values"
                )
        );
    }
}
