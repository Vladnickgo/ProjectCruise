package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.dao.entity.Port;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
class PortMapperTest {
    @InjectMocks
    private PortMapper mapper;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapPortDtoToEntityMethod")
    void mapDtoToEntity(PortDto portDto, Port expected, String message) {
        Port actual = mapper.mapDtoToEntity(portDto);
        assertEquals(expected, actual, message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapEntityToPortDtoMethod")
    void mapEntityToDto(Port port, PortDto expected, String message) {
        PortDto actual = mapper.mapEntityToDto(port);
        assertEquals(expected, actual, message);
    }

    private static Stream<Arguments> provideDataForMapPortDtoToEntityMethod() {
        return Stream.of(
                Arguments.of(
                        PortDto.newBuilder()
                                .id(1)
                                .portNameUa("Неаполь")
                                .portNameEn("Naples")
                                .countryUa("Італія")
                                .countryEn("Italy")
                                .build(),
                        Port.newBuilder()
                                .id(1)
                                .portNameUa("Неаполь")
                                .portNameEn("Naples")
                                .countryUa("Італія")
                                .countryEn("Italy")
                                .build(),
                        "Check mapPortDtoToPort method"),
                Arguments.of(
                        PortDto.newBuilder()
                                .build(),
                        Port.newBuilder()
                                .build(),
                        "Check mapPortDtoToPort method with empty values"),
                Arguments.of(null,null,
                        "Check mapPortDtoToPort method with null values")
                );
    }

    private static Stream<Arguments> provideDataForMapEntityToPortDtoMethod() {
        return Stream.of(
                Arguments.of(
                        Port.newBuilder()
                                .id(1)
                                .portNameUa("Неаполь")
                                .portNameEn("Naples")
                                .countryUa("Італія")
                                .countryEn("Italy")
                                .build(),
                        PortDto.newBuilder()
                                .id(1)
                                .portNameUa("Неаполь")
                                .portNameEn("Naples")
                                .countryUa("Італія")
                                .countryEn("Italy")
                                .build(),
                        "Check mapEntityToPortDto method"),
                Arguments.of(
                        Port.newBuilder()
                                .build(),
                        PortDto.newBuilder()
                                .build(),
                        "Check mapEntityToPortDto method with empty values"),
                Arguments.of(null,null,
                        "Check mapEntityToPortDto method")
        );
    }
}