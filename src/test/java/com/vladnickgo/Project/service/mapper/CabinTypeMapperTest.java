package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CabinTypeDto;
import com.vladnickgo.Project.dao.entity.CabinType;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CabinTypeMapperTest {
    @InjectMocks
    private CabinTypeMapper cabinTypeMapper;

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

    private static Stream<Arguments> provideDataForMapCabinTypeDtoToEntityMethod() {
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
                ),
                Arguments.of(
                        CabinTypeDto.newBuilder()
                                .build(),
                        CabinType.newBuilder()
                                .build(),
                        "Check mapDtoToEntity method with empty values"
                ),
                Arguments.of(null, null,
                        "Check mapDtoToEntity method with null values"
                )

        );
    }

    private static Stream<Arguments> provideDataForMapEntityToCabinTypeDtoMethod() {
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
                ),
                Arguments.of(
                        CabinType.newBuilder()
                                .build(),
                        CabinTypeDto.newBuilder()
                                .build(),
                        "Check mapEntityToDto method with empty values"
                ),
                Arguments.of(
                        null, null, "Check mapEntityToDto method with null values"
                )
        );
    }
}