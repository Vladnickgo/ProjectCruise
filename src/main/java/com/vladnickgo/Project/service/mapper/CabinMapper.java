package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CabinDto;
import com.vladnickgo.Project.dao.entity.Cabin;
import com.vladnickgo.Project.dao.entity.CabinType;
import com.vladnickgo.Project.dao.entity.Ship;

import java.util.Optional;

public class CabinMapper implements Mapper<CabinDto, Cabin> {
    @Override
    public Cabin mapDtoToEntity(CabinDto cabinDto) {
        if (cabinDto == null) {
            return null;
        }
        return Cabin.newBuilder()
                .id(cabinDto.getId())
                .cabinType(CabinType.newBuilder()
                        .cabinTypeName(cabinDto.getCabinTypeName())
                        .build())
                .ship(Ship.newBuilder()
                        .shipName(cabinDto.getShipName())
                        .build())
                .build();
    }

    @Override
    public CabinDto mapEntityToDto(Cabin cabin) {
        if (cabin == null) return null;
        return CabinDto.newBuilder()
                .id(cabin.getId())
                .cabinTypeName(getCabinTypeName(cabin))
                .shipName(getShipName(cabin))
                .build();
    }

    private String getShipName(Cabin cabin) {
        return Optional.ofNullable(cabin.getShip())
                .map(Ship::getShipName)
                .orElse(null);
    }

    private String getCabinTypeName(Cabin cabin) {
        return Optional.ofNullable(cabin.getCabinType())
                .map(CabinType::getCabinTypeName)
                .orElse(null);
    }
}
