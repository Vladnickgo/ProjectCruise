package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CabinStatusDto;
import com.vladnickgo.Project.dao.entity.Cabin;
import com.vladnickgo.Project.dao.entity.CabinStatus;
import com.vladnickgo.Project.dao.entity.CabinStatusStatement;

import java.util.Optional;

public class CabinStatusMapper implements Mapper<CabinStatusDto, CabinStatus> {
    @Override
    public CabinStatus mapDtoToEntity(CabinStatusDto cabinStatusDto) {
        if (cabinStatusDto == null) return null;
        return CabinStatus.newBuilder()
                .id(cabinStatusDto.getCabinId())
                .cabin(Cabin.newBuilder()
                        .id(cabinStatusDto.getCabinId())
                        .build())
                .statusStart(cabinStatusDto.getStatusStart())
                .statusEnd(cabinStatusDto.getStatusEnd())
                .statusStatement(CabinStatusStatement.newBuilder()
                        .statusStatementName(cabinStatusDto.getStatusStatementName())
                        .build())
                .build();
    }

    @Override
    public CabinStatusDto mapEntityToDto(CabinStatus cabinStatus) {
        if (cabinStatus == null) return null;
        return CabinStatusDto.newBuilder()
                .id(cabinStatus.getId())
                .cabinId(cabinStatusId(cabinStatus))
                .statusStart(cabinStatus.getStatusStart())
                .statusEnd(cabinStatus.getStatusEnd())
                .statusStatementName(getStatusStatementName(cabinStatus))
                .build();
    }

    private String getStatusStatementName(CabinStatus cabinStatus) {
        return Optional.ofNullable(cabinStatus.getStatusStatement())
                .map(CabinStatusStatement::getStatusStatementName)
                .orElse(null);
    }

    private Integer cabinStatusId(CabinStatus cabinStatus) {
        return Optional.ofNullable(cabinStatus.getCabin()).map(Cabin::getId).orElse(null);
    }

}
