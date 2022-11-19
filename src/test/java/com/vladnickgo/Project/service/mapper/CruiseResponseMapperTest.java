package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CruiseResponseDto;
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
class CruiseResponseMapperTest {
    @InjectMocks
    private CruiseResponseMapper cruiseMapper;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapEntityToCruiseResponseMethod")
    void checkMapEntityToDto(Cruise cruise, CruiseResponseDto expectedCruiseResponseDto, String message) {
        CruiseResponseDto actualCruiseResponseDto = cruiseMapper.mapEntityToDto(cruise);
        assertEquals(expectedCruiseResponseDto, actualCruiseResponseDto, message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCruiseResponseDtoToEntityMethod")
    void checkMapDtoToEntity(CruiseResponseDto cruiseResponseDto, Cruise expected, String message) {
        Cruise actual = cruiseMapper.mapDtoToEntity(cruiseResponseDto);
        assertEquals(expected, actual, message);
    }

    private static Stream<Arguments> provideDataForMapEntityToCruiseResponseMethod() {
        return Stream.of(
                Arguments.of(
                        Cruise.newBuilder()
                                .id(1)
                                .ship(Ship.newBuilder()
                                        .id(1)
                                        .shipName("Silver Wave")
                                        .shipImageSource("ship_img.jpg")
                                        .numberOfStaff(1200)
                                        .passengersCapacity(2000)
                                        .build())
                                .nights(8)
                                .dateStart(LocalDate.parse("2022-11-10"))
                                .dateEnd(LocalDate.parse("2022-11-19"))
                                .cruiseStatus(CruiseStatus.newBuilder()
                                        .id(1)
                                        .cruiseStatusName("available")
                                        .build())
                                .cruiseName("Golden tour")
                                .route(Route.newBuilder()
                                        .id(1)
                                        .routeName("european")
                                        .build())
                                .build(),
                        CruiseResponseDto.newBuilder()
                                .id(1)
                                .cruiseName("Golden tour")
                                .routeName("european")
                                .dateStart(LocalDate.parse("2022-11-10"))
                                .dateEnd(LocalDate.parse("2022-11-19"))
                                .nights(8)
                                .cruiseStatusName("available")
                                .shipName("Silver Wave")
                                .shipImageSource("ship_img.jpg")
                                .build(),
                        "Check mapEntityToCruiseResponseDto method"),
                Arguments.of(
                        Cruise.newBuilder()
                                .build(),
                        CruiseResponseDto.newBuilder()
                                .build(),
                        "Check mapEntityToCruiseResponseDto method with empty values"),
                Arguments.of(null, null,
                        "Check mapEntityToCruiseResponseDto method with null values"));
    }

    private static Stream<Arguments> provideDataForCruiseResponseDtoToEntityMethod() {
        return Stream.of(Arguments.of(
                        CruiseResponseDto.newBuilder()
                                .id(1)
                                .cruiseName("Golden tour")
                                .routeName("european")
                                .dateStart(LocalDate.parse("2022-11-10"))
                                .dateEnd(LocalDate.parse("2022-11-19"))
                                .nights(8)
                                .cruiseStatusName("available")
                                .shipName("Silver Wave")
                                .shipImageSource("ship_img.jpg")
                                .build(),
                        Cruise.newBuilder()
                                .id(1)
                                .ship(Ship.newBuilder()
                                        .shipName("Silver Wave")
                                        .shipImageSource("ship_img.jpg")
                                        .build())
                                .nights(8)
                                .dateStart(LocalDate.parse("2022-11-10"))
                                .dateEnd(LocalDate.parse("2022-11-19"))
                                .cruiseStatus(CruiseStatus.newBuilder()
                                        .cruiseStatusName("available")
                                        .build())
                                .cruiseName("Golden tour")
                                .route(Route.newBuilder()
                                        .routeName("european")
                                        .build())
                                .build(),
                        "Check mapCruiseResponseDtoToEntity method"),
                Arguments.of(CruiseResponseDto.newBuilder()
                                .build(),
                        Cruise.newBuilder()
                                .ship(Ship.newBuilder()
                                        .build())
                                .cruiseStatus(CruiseStatus.newBuilder()
                                        .build())
                                .route(Route.newBuilder()
                                        .build())
                                .build(),
                        "Check mapCruiseResponseDtoToEntity method with empty values"),
                Arguments.of(null, null,
                        "Check mapCruiseResponseDtoToEntity method"));
    }
}