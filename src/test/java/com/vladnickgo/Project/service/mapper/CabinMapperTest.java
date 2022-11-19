package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CabinDto;
import com.vladnickgo.Project.dao.entity.Cabin;
import com.vladnickgo.Project.dao.entity.CabinType;
import com.vladnickgo.Project.dao.entity.Ship;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CabinMapperTest {
    @InjectMocks
    private CabinMapper cabinMapper;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCheckDtoToEntityMethod")
    void mapDtoToEntity(CabinDto cabinDto, Cabin expected, String message) {
        Cabin actual = cabinMapper.mapDtoToEntity(cabinDto);
        assertEquals(expected, actual, message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCheckEntityToDtoMethod")
    void mapEntityToDto(Cabin cabin, CabinDto expected, String message) {
        CabinDto actual = cabinMapper.mapEntityToDto(cabin);
        assertEquals(expected, actual, message);
    }

    private static Stream<Arguments> provideDataForCheckDtoToEntityMethod() {
        return Stream.of(
                Arguments.of(
                        CabinDto.newBuilder()
                                .id(1)
                                .shipName("Silver Wave")
                                .cabinTypeName("balcony")
                                .build(),
                        Cabin.newBuilder()
                                .id(1)
                                .ship(Ship.newBuilder()
                                        .shipName("Silver Wave")
                                        .build())
                                .cabinType(CabinType.newBuilder()
                                        .cabinTypeName("balcony")
                                        .build())
                                .build(),
                        "Check dtoToEntity method"),
                Arguments.of(
                        CabinDto.newBuilder()
                                .build(),
                        Cabin.newBuilder()
                                .ship(Ship.newBuilder()
                                        .build())
                                .cabinType(CabinType.newBuilder()
                                        .build())
                                .build(),
                        "Check dtoToEntity method with empty values"),
                Arguments.of(null, null,
                        "Check dtoToEntity method with null values")
        );
    }

    private static Stream<Arguments> provideDataForCheckEntityToDtoMethod() {
        return Stream.of(
                Arguments.of(
                        Cabin.newBuilder()
                                .id(1)
                                .ship(Ship.newBuilder()
                                        .id(1)
                                        .passengersCapacity(2000)
                                        .numberOfStaff(1000)
                                        .shipName("Silver Wave")
                                        .shipImageSource("silver_wave.jpg")
                                        .build())
                                .cabinType(CabinType.newBuilder()
                                        .id(1)
                                        .numberOfBeds(2)
                                        .price(1000)
                                        .cabinTypeName("balcony")
                                        .build())
                                .build(),
                        CabinDto.newBuilder()
                                .id(1)
                                .shipName("Silver Wave")
                                .cabinTypeName("balcony")
                                .build(),
                        "Check mapEntityToDto method"),
                Arguments.of(
                        Cabin.newBuilder()
                                .build(),
                        CabinDto.newBuilder()
                                .build(),
                        "Check mapEntityToDto method with empty values"),
                Arguments.of(
                        null,
                        null,
                        "Check mapEntityToDto method with null values"));
    }
}