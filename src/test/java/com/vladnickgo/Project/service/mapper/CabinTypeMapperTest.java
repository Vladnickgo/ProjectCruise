package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CabinTypeDto;
import com.vladnickgo.Project.dao.entity.CabinType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CabinTypeMapperTest {
    private final Mapper<CabinTypeDto, CabinType> cabinTypeMapper = new CabinTypeMapper();

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapCabinTypeDtoToEntityMethod")
    void checkMapDtoToEntity(CabinTypeDto cabinTypeDto, CabinType expectedCabinType, String message) {
        CabinType actualCabinType = cabinTypeMapper.mapDtoToEntity(cabinTypeDto);
        assertEquals(expectedCabinType, actualCabinType, message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapEntityToCabinTypeDtoMethod")
    void checkMapEntityToDto(CabinType cabinType, CabinTypeDto expectedCabinTypeDto, String message) {
        CabinTypeDto actualCabinTypeDto = cabinTypeMapper.mapEntityToDto(cabinType);
        assertEquals(expectedCabinTypeDto, actualCabinTypeDto, message);
    }

    private static Stream<Arguments> provideDataForMapCabinTypeDtoToEntityMethod(){
        return Stream.of(
                Arguments.of(
                        CabinTypeDto.newBuilder()
                                .id(1)
                                .cabinTypeName("balcony")
                                .numberOfBeds(2)
                                .price(1000)
                                .build(),
                        CabinType.newBuilder()
                                .id(1)
                                .cabinTypeName("balcony")
                                .numberOfBeds(2)
                                .price(1000)
                                .build(),
                        "Check mapDtoToEntity method"
                ));
    }

    private static Stream<Arguments> provideDataForMapEntityToCabinTypeDtoMethod(){
        return Stream.of(
                Arguments.of(
                        CabinType.newBuilder()
                                .id(1)
                                .cabinTypeName("balcony")
                                .numberOfBeds(2)
                                .price(1000)
                                .build(),
                        CabinTypeDto.newBuilder()
                                .id(1)
                                .cabinTypeName("balcony")
                                .numberOfBeds(2)
                                .price(1000)
                                .build(),
                        "Check mapEntityToDto method"
                ));
    }
}