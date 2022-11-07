package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.dao.entity.Ship;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipMapperTest {
    private final Mapper<ShipDto, Ship> shipMapper = new ShipMapper();

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
                        "Check ShipDtoToEntity mapper"),
                Arguments.of(ShipDto.newBuilder()
                                .id(1)
                                .shipName("Silver Wave")
                                .shipImage("img.jpg")
                                .numberOfStaff(1000)
                                .passengersCapacity(2200)
                                .build(),
                        Ship.newBuilder()
                                .id(1)
                                .shipName("Silver Wave")
                                .shipImageSource("img.jpg")
                                .numberOfStaff(1000)
                                .passengersCapacity(2200)
                                .build(),
                        "Check ShipDtoToEntity mapper")
        );
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
                        "Check EntityToShipDto mapper"),
                Arguments.of(Ship.newBuilder()
                                .id(1)
                                .shipName("Silver Wave")
                                .shipImageSource("img.jpg")
                                .numberOfStaff(1000)
                                .passengersCapacity(2200)
                                .build(),
                        ShipDto.newBuilder()
                                .id(1)
                                .shipName("Silver Wave")
                                .shipImage("img.jpg")
                                .numberOfStaff(1000)
                                .passengersCapacity(2200)
                                .build(),
                        "Check EntityToShipDto mapper"));
    }
}