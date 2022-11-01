package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.entity.CruiseStatus;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.Ship;

public class CruiseMapper implements Mapper<CruiseDto, Cruise> {
    @Override
    public Cruise mapDtoToEntity(CruiseDto cruiseDto) {
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
        return CruiseDto.newBuilder()
                .id(cruise.getId())
                .cruiseName(cruise.getCruiseName())
                .routeID(cruise.getRoute().getId())
                .dateStart(cruise.getDateStart())
                .dateEnd(cruise.getDateEnd())
                .cruiseStatusId(cruise.getCruiseStatus().getId())
                .shipId(cruise.getShip().getId())
                .nights(cruise.getNights())
                .build();
    }
}
