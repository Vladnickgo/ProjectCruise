package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CabinStatusDto;
import com.vladnickgo.Project.dao.entity.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CabinStatusMapperTest {
    @InjectMocks
    private CabinStatusMapper cabinStatusMapper;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForDtoToEntityMethod")
    void mapDtoToEntity(CabinStatusDto cabinStatusDto, CabinStatus expected, String message) {
        CabinStatus actual = cabinStatusMapper.mapDtoToEntity(cabinStatusDto);
        assertEquals(expected, actual, message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForEntityToDtoMethod")
    void mapEntityToDto(CabinStatus cabinStatus, CabinStatusDto expected, String message) {
        CabinStatusDto actual = cabinStatusMapper.mapEntityToDto(cabinStatus);
        assertEquals(expected, actual, message);
    }

    private static Stream<Arguments> provideDataForDtoToEntityMethod() {
        return Stream.of(
                Arguments.of(
                        CabinStatusDto.newBuilder()
                                .id(1)
                                .cabinId(1)
                                .statusStart(LocalDate.parse("2022-10-01"))
                                .statusEnd(LocalDate.parse("2023-10-01"))
                                .statusStatementName("available")
                                .build(),
                        CabinStatus.newBuilder()
                                .id(1)
                                .cabin(Cabin.newBuilder()
                                        .id(1)
                                        .build())
                                .statusStart(LocalDate.parse("2022-10-01"))
                                .statusEnd(LocalDate.parse("2023-10-01"))
                                .statusStatement(CabinStatusStatement.newBuilder()
                                        .statusStatementName("available")
                                        .build())
                                .build(),
                        "Check dtoToEntity method"),
                Arguments.of(
                        CabinStatusDto.newBuilder()
                                .build(),
                        CabinStatus.newBuilder()
                                .cabin(Cabin.newBuilder()
                                        .build())
                                .statusStatement(CabinStatusStatement.newBuilder()
                                        .build())
                                .build(),
                        "Check dtoToEntity method with empty values"),
                Arguments.of(null, null,
                        "Check dtoToEntity method with null values")

        );
    }

    private static Stream<Arguments> provideDataForEntityToDtoMethod() {
        return Stream.of(
                Arguments.of(
                        CabinStatus.newBuilder()
                                .id(1)
                                .cabin(Cabin.newBuilder()
                                        .id(1)
                                        .cabinType(CabinType.newBuilder()
                                                .id(1)
                                                .price(1000)
                                                .numberOfBeds(2)
                                                .cabinTypeName("balcony")
                                                .build())
                                        .ship(Ship.newBuilder()
                                                .id(1)
                                                .shipName("Silver Wave")
                                                .shipImageSource("silver_wave.jpg")
                                                .numberOfStaff(1000)
                                                .passengersCapacity(2500)
                                                .build())
                                        .build())
                                .statusStart(LocalDate.parse("2022-10-01"))
                                .statusEnd(LocalDate.parse("2023-10-01"))
                                .statusStatement(CabinStatusStatement.newBuilder()
                                        .id(1)
                                        .statusStatementName("available")
                                        .build())
                                .build(),
                        CabinStatusDto.newBuilder()
                                .id(1)
                                .cabinId(1)
                                .statusStart(LocalDate.parse("2022-10-01"))
                                .statusEnd(LocalDate.parse("2023-10-01"))
                                .statusStatementName("available")
                                .build(), "Check entityToDto method"),
                Arguments.of(CabinStatus.newBuilder()
                                .build(),
                        CabinStatusDto.newBuilder()
                                .build(),
                        "Check entityToDto method with empty values"),
                Arguments.of(null, null,
                        "Check entityToDto method with null values")
        );
    }

}