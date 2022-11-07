package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.dao.entity.Port;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PortMapperTest {
    private final Mapper<PortDto, Port> mapper = new PortMapper();

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
                        "Check mapPortDtoToPort method"));
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
                        "Check mapEntityToPortDto method"));
    }
}