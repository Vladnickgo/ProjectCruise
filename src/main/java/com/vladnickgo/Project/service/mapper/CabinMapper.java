package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CabinDto;
import com.vladnickgo.Project.dao.entity.Cabin;
import com.vladnickgo.Project.dao.entity.CabinType;
import com.vladnickgo.Project.dao.entity.Ship;

public class CabinMapper implements Mapper<CabinDto, Cabin> {
    @Override
    public Cabin mapDtoToEntity(CabinDto cabinDto) {
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
        return CabinDto.newBuilder()
                .id(cabin.getId())
                .cabinTypeName(cabin.getCabinType().getCabinTypeName())
                .shipName(cabin.getShip().getShipName())
                .build();
    }
}
