package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.RoutePointDto;
import com.vladnickgo.Project.controller.dto.RoutePointRequestDto;
import com.vladnickgo.Project.dao.RoutePointDao;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.RoutePoint;
import com.vladnickgo.Project.service.mapper.RoutePointMapper;
import com.vladnickgo.Project.validator.RoutePointRequestValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoutePointServiceImplTest {
    private static final RoutePoint TEST_ROUTE_POINT1 =
            RoutePoint.newBuilder()
                    .id(1)
                    .port(Port.newBuilder()
                            .id(1)
                            .portNameUa("Антверпен")
                            .portNameEn("Antwerpen")
                            .countryUa("Бельгія")
                            .countryEn("Belgium")
                            .build())
                    .route(Route.newBuilder()
                            .id(1)
                            .routeName("european")
                            .build())
                    .dayNumber(1)
                    .build();

    public static final RoutePoint TEST_ROUTE_POINT2 = RoutePoint.newBuilder()
            .id(2)
            .port(Port.newBuilder()
                    .id(1)
                    .portNameUa("Венеція")
                    .portNameEn("Venice")
                    .countryUa("Італія")
                    .countryEn("Italy")
                    .build())
            .route(Route.newBuilder()
                    .id(1)
                    .routeName("european")
                    .build())
            .dayNumber(1)
            .build();

    public static final RoutePoint TEST_ROUTE_POINT3 = RoutePoint.newBuilder()
            .id(3)
            .port(Port.newBuilder()
                    .id(2)
                    .portNameUa("Барселона")
                    .portNameEn("Barcelona")
                    .countryUa("Іспанія")
                    .countryEn("Spain")
                    .build())
            .route(Route.newBuilder()
                    .id(1)
                    .routeName("european")
                    .build())
            .dayNumber(1)
            .build();

    private static final RoutePointDto TEST_ROUTE_POINT_DTO1 = RoutePointDto.newBuilder()
            .id(1)
            .portNameUa("Антверпен")
            .portNameEn("Antwerpen")
            .countryUa("Бельгія")
            .countryEn("Belgium")
            .dayNumber(1)
            .routeName("european")
            .build();

    public static final RoutePointDto TEST_ROUTE_POINT_DTO2 = RoutePointDto.newBuilder()
            .id(2)
            .portNameUa("Венеція")
            .portNameEn("Venice")
            .countryUa("Італія")
            .countryEn("Italy")
            .routeName("european")
            .dayNumber(1)
            .build();

    public static final RoutePointDto TEST_ROUTE_POINT_DTO3 = RoutePointDto.newBuilder()
            .id(3)
            .portNameUa("Барселона")
            .portNameEn("Barcelona")
            .countryUa("Іспанія")
            .countryEn("Spain")
            .routeName("european")
            .dayNumber(1)
            .build();


    private static final List<RoutePoint> TEST_ROUTE_POINT_LIST = new ArrayList<>(
            List.of(TEST_ROUTE_POINT1, TEST_ROUTE_POINT2, TEST_ROUTE_POINT3));

    private static final List<RoutePointDto> TEST_ROUTE_POINT_DTO_LIST = new ArrayList<>(List.of(
            TEST_ROUTE_POINT_DTO1, TEST_ROUTE_POINT_DTO2, TEST_ROUTE_POINT_DTO3));

    private static final Integer TEST_ROUTE_ID = 1;

    @Mock
    private RoutePointDao routePintRepository;

    @Spy
    private RoutePointMapper routePointMapper;

    @Spy
    private RoutePointRequestValidator validator;

    @InjectMocks
    private RoutePointServiceImpl routePointService;

    @Test
    void findAllByRouteIdSuccessful() {
        when(routePintRepository.findAllByRouteId(TEST_ROUTE_ID)).thenReturn(TEST_ROUTE_POINT_LIST);
        when(routePointMapper.mapEntityToDto(TEST_ROUTE_POINT1)).thenReturn(TEST_ROUTE_POINT_DTO1);
        when(routePointMapper.mapEntityToDto(TEST_ROUTE_POINT2)).thenReturn(TEST_ROUTE_POINT_DTO2);
        when(routePointMapper.mapEntityToDto(TEST_ROUTE_POINT3)).thenReturn(TEST_ROUTE_POINT_DTO3);
        List<RoutePointDto> actual = routePointService.findAllByRouteId(TEST_ROUTE_ID);
        assertEquals(TEST_ROUTE_POINT_DTO_LIST, actual);
    }

    @Test
    void findAllIsSuccessful() {
        when(routePintRepository.findAll()).thenReturn(TEST_ROUTE_POINT_LIST);
        when(routePointMapper.mapEntityToDto(TEST_ROUTE_POINT1)).thenReturn(TEST_ROUTE_POINT_DTO1);
        when(routePointMapper.mapEntityToDto(TEST_ROUTE_POINT2)).thenReturn(TEST_ROUTE_POINT_DTO2);
        when(routePointMapper.mapEntityToDto(TEST_ROUTE_POINT3)).thenReturn(TEST_ROUTE_POINT_DTO3);
        List<RoutePointDto> actual = routePointService.findAll();
        assertEquals(TEST_ROUTE_POINT_DTO_LIST, actual);
    }

    @Test
    void deleteRoutePointByIdIsSuccessful() {
        routePointService.deleteRoutePointById(TEST_ROUTE_POINT_DTO1.getId());
        verify(routePintRepository).deleteById(TEST_ROUTE_POINT_DTO1.getId());
    }

    private static Stream<Arguments> provideDataForCreateRoutPointWithNullParameters() {
        return Stream.of(
                Arguments.of(
                        RoutePointRequestDto.newBuilder()
                                .build(),
                        "RoutePointRequestDto is empty",
                        "Check with empty of RoutePointRequestDto"
                ),
                Arguments.of(
                        RoutePointRequestDto.newBuilder()
                                .routeId(1)
                                .portId(1)
                                .dayNumber(1)
                                .build(),
                        "Route point id is null",
                        "Check with null routePointId"
                ),
                Arguments.of(
                        RoutePointRequestDto.newBuilder()
                                .routePointId(1)
                                .portId(1)
                                .dayNumber(1)
                                .build(),
                        "Route id is null",
                        "Check with null routeId"
                ),
                Arguments.of(
                        RoutePointRequestDto.newBuilder()
                                .routeId(1)
                                .portId(1)
                                .dayNumber(1)
                                .build(),
                        "Route point id is null",
                        "Check with null routePointId"
                ),
                Arguments.of(
                        RoutePointRequestDto.newBuilder()
                                .routeId(1)
                                .routePointId(1)
                                .portId(1)
                                .build(),
                        "Day number is null",
                        "Check with null dayNumber"
                ),
                Arguments.of(
                        null,
                        "RoutePointRequestDto is null",
                        "Check with null routePointRequestDto"
                ));
    }
}