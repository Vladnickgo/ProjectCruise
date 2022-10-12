package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CabinTypeDto;
import com.vladnickgo.Project.dao.entity.CabinType;

public class CabinTypeMapper implements Mapper<CabinTypeDto, CabinType> {
    @Override
    public CabinType mapDtoToEntity(CabinTypeDto cabinTypeDto) {
        return CabinType.newBuilder()
                .id(cabinTypeDto.getId())
                .cabinTypeName(cabinTypeDto.getCabinTypeName())
                .numberOfBeds(cabinTypeDto.getNumberOfBeds())
                .price(cabinTypeDto.getPrice())
                .build();
    }

    @Override
    public CabinTypeDto mapEntityToDto(CabinType cabinType) {
        return CabinTypeDto.newBuilder()
                .id(cabinType.getId())
                .cabinTypeName(cabinType.getCabinTypeName())
                .numberOfBeds(cabinType.getNumberOfBeds())
                .price(cabinType.getPrice())
                .build();
    }
}
