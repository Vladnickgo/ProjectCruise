package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.ShipDto;
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
class ShipMapperTest {
    @InjectMocks
    private ShipMapper shipMapper;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCheckMapDtoToEntityMethod")
    void mapDtoToEntity(ShipDto shipDto, Ship expectedShip, String message) {
        Ship actualShip = shipMapper.mapDtoToEntity(shipDto);
        assertEquals(expectedShip, actualShip, message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCheckMapEntityToDtoMethod")
    void mapEntityToDto(Ship ship, ShipDto expectedShipDto, String message) {
        ShipDto actualShipDto = shipMapper.mapEntityToDto(ship);
        assertEquals(expectedShipDto, actualShipDto, message);
    }

    private static Stream<Arguments> provideDataForCheckMapDtoToEntityMethod() {
        return Stream.of(Arguments.of(ShipDto.newBuilder()
                                .id(1)
                                .shipName("Queen Mary")
                                .shipImage("img.jpg")
                                .numberOfStaff(100)
                                .passengersCapacity(200)
                                .build(),
                        Ship.newBuilder()
                                .id(1)
                                .shipName("Queen Mary")
                                .shipImageSource("img.jpg")
                                .numberOfStaff(100)
                                .passengersCapacity(200)
                                .build(),
                        "Check mapDtoToEntity method"),
                Arguments.of(ShipDto.newBuilder()
                                .build(),
                        Ship.newBuilder()
                                .build(),
                        "Check mapDtoToEntity method with empty values")
                ,
                Arguments.of(null,
                        null,
                        "Check mapDtoToEntity method with null values"));
    }

    private static Stream<Arguments> provideDataForCheckMapEntityToDtoMethod() {
        return Stream.of(Arguments.of(
                        Ship.newBuilder()
                                .id(1)
                                .shipName("Queen Mary")
                                .shipImageSource("img.jpg")
                                .numberOfStaff(100)
                                .passengersCapacity(200)
                                .build(),
                        ShipDto.newBuilder()
                                .id(1)
                                .shipName("Queen Mary")
                                .shipImage("img.jpg")
                                .numberOfStaff(100)
                                .passengersCapacity(200)
                                .build(),
                        "Check entityToShipDto mapper"),
                Arguments.of(Ship.newBuilder()
                                .build(),
                        ShipDto.newBuilder()
                                .build(),
                        "Check entityToShipDto mapper with empty values"),
                Arguments.of(null,
                        null,
                        "Check entityToShipDto mapper with null values"));
    }
}