package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.entity.CruiseStatus;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.Ship;

import java.util.Optional;

public class CruiseMapper implements Mapper<CruiseDto, Cruise> {
    @Override
    public Cruise mapDtoToEntity(CruiseDto cruiseDto) {
        if (cruiseDto == null) return null;
        return Cruise.newBuilder()
                .id(cruiseDto.getId())
                .cruiseName(cruiseDto.getCruiseName())
                .route(Route.newBuilder()
                        .id(cruiseDto.getRouteID())
                        .build())
                .dateStart(cruiseDto.getDateStart())
                .dateEnd(cruiseDto.getDateEnd())
                .cruiseStatus(CruiseStatus.newBuilder()
                        .id(cruiseDto.getCruiseStatusId())
                        .build())
                .ship(Ship.newBuilder()
                        .id(cruiseDto.getShipId())
                        .build())
                .nights(cruiseDto.getNights())
                .build();
    }

    @Override
    public CruiseDto mapEntityToDto(Cruise cruise) {
        if (cruise == null) return null;
        return CruiseDto.newBuilder()
                .id(cruise.getId())
                .cruiseName(cruise.getCruiseName())
                .routeID(getRouteId(cruise))
                .dateStart(cruise.getDateStart())
                .dateEnd(cruise.getDateEnd())
                .cruiseStatusId(getCruiseStatusId(cruise))
                .shipId(getShipId(cruise))
                .nights(cruise.getNights())
                .build();
    }

    private Integer getShipId(Cruise cruise) {
        return Optional.ofNullable(cruise.getShip())
                .map(Ship::getId)
                .orElse(null);
    }

    private Integer getCruiseStatusId(Cruise cruise) {
        return Optional.ofNullable(cruise.getCruiseStatus())
                .map(CruiseStatus::getId)
                .orElse(null);
    }

    private Integer getRouteId(Cruise cruise) {
        return Optional.ofNullable(cruise.getRoute())
                .map(Route::getId)
                .orElse(null);
    }

}
