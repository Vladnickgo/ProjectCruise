package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.RoutePointDto;
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
class RoutePointMapperTest {
    @InjectMocks
    private RoutePointMapper routePointMapper;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCheckMapDtoToEntityMethod")
    void mapDtoToEntity(RoutePointDto routePointDto, RoutePoint expectedRoutePoint, String message) {
        RoutePoint actualRoutePoint = routePointMapper.mapDtoToEntity(routePointDto);
        assertEquals(expectedRoutePoint, actualRoutePoint, message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCheckMapEntityToDtoMethod")
    void mapEntityToDto(RoutePoint routePoint, RoutePointDto expectedRoutePointDto, String message) {
        RoutePointDto actualRoutePointDto = routePointMapper.mapEntityToDto(routePoint);
        assertEquals(expectedRoutePointDto, actualRoutePointDto, message);
    }

    private static Stream<Arguments> provideDataForCheckMapDtoToEntityMethod() {
        return Stream.of(
                Arguments.of(RoutePointDto.newBuilder()
                                .id(1)
                                .routeName("european")
                                .portNameUa("Рим")
                                .portNameEn("Rome")
                                .countryUa("Італія")
                                .countryEn("Italy")
                                .dayNumber(1)
                                .build(),
                        RoutePoint.newBuilder()
                                .id(1)
                                .route(Route.newBuilder()
                                        .routeName("european")
                                        .build())
                                .port(Port.newBuilder()
                                        .id(1)
                                        .portNameUa("Рим")
                                        .portNameEn("Rome")
                                        .countryUa("Італія")
                                        .countryEn("Italy")
                                        .build())
                                .dayNumber(1)
                                .build(),
                        "Check mapDtoToEntity"),
                Arguments.of(RoutePointDto.newBuilder()
                                .build(),
                        RoutePoint.newBuilder()
                                .id(null)
                                .route(Route.newBuilder()
                                        .build())
                                .port(Port.newBuilder()
                                        .build())
                                .dayNumber(null)
                                .build(),
                        "Check mapDtoToEntity with empty values"),
                Arguments.of(null, null,
                        "Check mapDtoToEntity method with null values")
        );
    }

    private static Stream<Arguments> provideDataForCheckMapEntityToDtoMethod() {
        return Stream.of(
                Arguments.of(
                        RoutePoint.newBuilder()
                                .id(1)
                                .route(Route.newBuilder()
                                        .routeName("european")
                                        .build())
                                .port(Port.newBuilder()
                                        .id(1)
                                        .portNameUa("Рим")
                                        .portNameEn("Rome")
                                        .countryUa("Італія")
                                        .countryEn("Italy")
                                        .build())
                                .dayNumber(1)
                                .build(),
                        RoutePointDto.newBuilder()
                                .id(1)
                                .routeName("european")
                                .portNameUa("Рим")
                                .portNameEn("Rome")
                                .countryUa("Італія")
                                .countryEn("Italy")
                                .dayNumber(1)
                                .build(),
                        "Check mapEntityToDto method"),
                Arguments.of(
                        RoutePoint.newBuilder()
                                .build(),
                        RoutePointDto.newBuilder()
                                .build(),
                        "Check mapEntityToDto method with empty values"),
                Arguments.of(null, null,
                        "Check mapEntityToDto method with null values")
        );
    }
}