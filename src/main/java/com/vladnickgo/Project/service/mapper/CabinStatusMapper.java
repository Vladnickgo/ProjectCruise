package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CabinStatusDto;
import com.vladnickgo.Project.dao.entity.Cabin;
import com.vladnickgo.Project.dao.entity.CabinStatus;
import com.vladnickgo.Project.dao.entity.CabinStatusStatement;

public class CabinStatusMapper implements Mapper<CabinStatusDto, CabinStatus> {
    @Override
    public CabinStatus mapDtoToEntity(CabinStatusDto cabinStatusDto) {
        return CabinStatus.newBuilder()
                .id(cabinStatusDto.getCabinId())
                .cabin(Cabin.newBuilder()
                        .id(cabinStatusDto.getCabinId())
                        .build())
                .statusStart(cabinStatusDto.getStatus_start())
                .statusEnd(cabinStatusDto.getStatus_end())
                .statusStatement(CabinStatusStatement.newBuilder()
                        .statusStatementName(cabinStatusDto.getStatusStatementName())
                        .build())
                .build();
    }

    @Override
    public CabinStatusDto mapEntityToDto(CabinStatus cabinStatus) {
        return CabinStatusDto.newBuilder()
                .id(cabinStatus.getId())
                .cabinId(cabinStatus.getCabin().getId())
                .status_start(cabinStatus.getStatusStart())
                .status_end(cabinStatus.getStatusStart())
                .statusStatementName(cabinStatus.getStatusStatement().getStatusStatementName())
                .build();
    }
}
