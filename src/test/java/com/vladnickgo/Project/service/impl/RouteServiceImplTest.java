package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.RouteDto;
import com.vladnickgo.Project.dao.RouteDao;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.service.impl.exception.EntityAlreadyExistException;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.RouteRequestDtoUtil;
import com.vladnickgo.Project.validator.RouteValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {
    private static final String FIRST_ROUTE_POINT_ID = "1";
    private static final RouteDto TEST_ROUTE_DTO = RouteDto.newBuilder().id(1)
            .routeName("european")
            .build();
    private static final Route TEST_ROUTE_ENTITY = Route.newBuilder()
            .id(1)
            .routeName("european")
            .build();
    private static final List<Route> TEST_ROUTE_ENTITY_LIST = List.of(TEST_ROUTE_ENTITY);
    private static final List<RouteDto> TEST_ROUTE_DTO_LIST = List.of(TEST_ROUTE_DTO);


    @Mock
    private RouteDao routeRepository;

    @Mock
    private RouteRequestDtoUtil routeRequestDtoUtil;

    @Spy
    private Mapper<RouteDto, Route> mapper;

    @Spy
    private RouteValidator routeValidator;

    @InjectMocks
    private RouteServiceImpl routeService;

    @Test
    public void addRouteIsNull() {
        String expectedExceptionMessage = "RouteDto is null";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> routeService.addRoute(null, FIRST_ROUTE_POINT_ID));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void addRouteIsNullRouteName() {
        String expectedExceptionMessage = "Route name is empty";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> routeService.addRoute(RouteDto.newBuilder().id(1).build(), FIRST_ROUTE_POINT_ID));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void addRouteIsEmptyRoute() {
        String expectedExceptionMessage = "RouteDto is empty";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> routeService.addRoute(RouteDto.newBuilder().build(), FIRST_ROUTE_POINT_ID));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void addRouteIfNullFirstRoutePoint() {
        String expectedExceptionMessage = "First port id is null";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> routeService.addRoute(RouteDto.newBuilder().build(), null));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void addRouteIsRouteAlreadyExist() {
        doNothing().when(routeValidator).validate(TEST_ROUTE_DTO);
        when(mapper.mapDtoToEntity(TEST_ROUTE_DTO)).thenReturn(TEST_ROUTE_ENTITY);
        when(routeRepository.findByName(TEST_ROUTE_DTO.getRouteName())).thenReturn(Optional.of(TEST_ROUTE_ENTITY));
        EntityAlreadyExistException exception = Assertions.assertThrows(EntityAlreadyExistException.class,
                () -> routeService.addRoute(TEST_ROUTE_DTO, FIRST_ROUTE_POINT_ID));
        String EXPECTED_EXCEPTION_MESSAGE = "Route with name EUROPEAN already exist";
        assertEquals(EXPECTED_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void findAllRoutesIfExist() {
        when(routeRepository.findAll()).thenReturn(TEST_ROUTE_ENTITY_LIST);
        when(mapper.mapEntityToDto(TEST_ROUTE_ENTITY)).thenReturn(TEST_ROUTE_DTO);
        List<RouteDto> actualRouteList = routeService.findAllRoutes();
        assertEquals(TEST_ROUTE_DTO_LIST, actualRouteList);
    }

    @Test
    public void findAllByNumberOfPageAndSortingIfExist(){
        when(routeRepository.findAllByNumberOfPageAndSorting(routeRequestDtoUtil)).thenReturn(TEST_ROUTE_ENTITY_LIST);
        when(mapper.mapEntityToDto(TEST_ROUTE_ENTITY)).thenReturn(TEST_ROUTE_DTO);
        List<RouteDto> actualRouteList = routeService.findAllByNumberOfPageAndSorting(routeRequestDtoUtil);
        assertEquals(TEST_ROUTE_DTO_LIST, actualRouteList);
    }

    @ParameterizedTest(name = "[{index}]{3}")
    @MethodSource("provideNumberOfRecordsOnPageAndTotalRecords")
    public void getTotalPagesTest(Integer recordsOnPage, Integer totalRecords, Integer expectedPages, String message) {
        Mockito.when(routeRepository.countAll()).thenReturn(totalRecords);
        Integer actualPages = routeService.getNumberOfPages(recordsOnPage);
        assertEquals(expectedPages, actualPages, message);
    }

    private static Stream<Arguments> provideNumberOfRecordsOnPageAndTotalRecords() {
        return Stream.of(
                Arguments.of(
                        5, 7, 2, "Check for 7 records, 5 records on page"
                ),
                Arguments.of(
                        5, 1, 1, "Check for 1 records, 5 records on page"
                ),
                Arguments.of(
                        5, 10, 2, "Check for 11 records, 10 records on page"
                ),
                Arguments.of(
                        1, 0, 0, "Check for 0 records, 0 records on page"
                ),
                Arguments.of(
                        1, 1, 1, "Check for 1 records, 1 records on page"
                )
        );
    }
}