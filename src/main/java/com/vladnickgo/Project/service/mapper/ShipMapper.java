package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.dao.entity.Ship;

public class ShipMapper implements Mapper<ShipDto, Ship> {
    @Override
    public Ship mapDtoToEntity(ShipDto shipDto) {
        if (shipDto == null) return null;
        return Ship.newBuilder()
                .id(shipDto.getId())
                .shipName(shipDto.getShipName())
                .passengersCapacity(shipDto.getPassengersCapacity())
                .numberOfStaff(shipDto.getNumberOfStaff())
                .shipImageSource(shipDto.getShipImage())
                .build();
    }

    @Override
    public ShipDto mapEntityToDto(Ship ship) {
        if (ship == null) return null;
        return ShipDto.newBuilder()
                .id(ship.getId())
                .shipName(ship.getShipName())
                .passengersCapacity(ship.getPassengersCapacity())
                .numberOfStaff(ship.getNumberOfStaff())
                .shipImage(ship.getShipImage())
                .build();
    }
}
