package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.entity.CruiseStatus;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.Ship;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CruiseMapperTest {
    @InjectMocks
    private CruiseMapper cruiseMapper;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapDtoToEntityMethod")
    void checkMapDtoToEntity(CruiseDto cruiseDto, Cruise expected, String message) {
        Cruise actual = cruiseMapper.mapDtoToEntity(cruiseDto);
        assertEquals(expected, actual, message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForEntityToDtoMethod")
    void checkMapEntityToDto(Cruise cruise, CruiseDto expected, String message) {
        CruiseDto actual = cruiseMapper.mapEntityToDto(cruise);
        assertEquals(expected, actual, message);
    }

    private static Stream<Arguments> provideDataForMapDtoToEntityMethod() {
        return Stream.of(
                Arguments.of(
                        CruiseDto.newBuilder()
                                .id(1)
                                .cruiseName("Winter Tour")
                                .cruiseStatusId(1)
                                .nights(10)
                                .shipId(1)
                                .dateStart(LocalDate.parse("2022-12-01"))
                                .dateEnd(LocalDate.parse("2022-12-11"))
                                .routeID(1)
                                .build(),
                        Cruise.newBuilder()
                                .id(1)
                                .cruiseName("Winter Tour")
                                .route(Route.newBuilder()
                                        .id(1)
                                        .build())
                                .dateStart(LocalDate.parse("2022-12-01"))
                                .dateEnd(LocalDate.parse("2022-12-11"))
                                .nights(10)
                                .cruiseStatus(CruiseStatus.newBuilder()
                                        .id(1)
                                        .build())
                                .ship(Ship.newBuilder()
                                        .id(1)
                                        .build())
                                .build(),
                        "Check mapCruiseDtoToEntity method"),
                Arguments.of(
                        CruiseDto.newBuilder()
                                .build(),
                        Cruise.newBuilder()
                                .route(Route.newBuilder().build())
                                .cruiseStatus(CruiseStatus.newBuilder().build())
                                .ship(Ship.newBuilder().build())
                                .build(),
                        "Check mapCruiseDtoToEntity method with empty values"),
                Arguments.of(null, null,
                        "Check mapCruiseDtoToEntity method with null values")
        );
    }

    private static Stream<Arguments> provideDataForEntityToDtoMethod() {
        return Stream.of(
                Arguments.of(
                        Cruise.newBuilder()
                                .id(1)
                                .cruiseName("Diamond Tour")
                                .route(Route.newBuilder()
                                        .id(1)
                                        .routeName("european")
                                        .build())
                                .cruiseStatus(CruiseStatus.newBuilder()
                                        .id(1)
                                        .cruiseStatusName("available")
                                        .build())
                                .dateStart(LocalDate.parse("2022-12-01"))
                                .dateEnd(LocalDate.parse("2022-01-11"))
                                .nights(10)
                                .ship(Ship.newBuilder()
                                        .id(1)
                                        .shipName("Princess Ruby")
                                        .passengersCapacity(2000)
                                        .numberOfStaff(1000)
                                        .shipImageSource("princess_ruby.jpg")
                                        .build())
                                .build(),
                        CruiseDto.newBuilder()
                                .id(1)
                                .cruiseName("Diamond Tour")
                                .routeID(1)
                                .dateStart(LocalDate.parse("2022-12-01"))
                                .dateEnd(LocalDate.parse("2022-01-11"))
                                .cruiseStatusId(1)
                                .shipId(1)
                                .nights(10)
                                .build(),
                        "Check mapEntityToCruiseDto method"),
                Arguments.of(
                        Cruise.newBuilder()
                                .build(),
                        CruiseDto.newBuilder()
                                .build(),
                        "Check mapEntityToCruiseDto method with empty values"),
                Arguments.of(null, null,
                        "Check mapEntityToCruiseDto method with null values"));
    }
}