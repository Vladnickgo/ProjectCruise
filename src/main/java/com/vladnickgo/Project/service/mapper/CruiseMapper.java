package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.entity.CruiseStatus;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.Ship;

public class CruiseMapper implements Mapper<CruiseDto, Cruise>{
    @Override
    public Cruise mapDtoToEntity(CruiseDto cruiseDto) {
        return Cruise.newBuilder()
                .id(cruiseDto.getId())
                .cruiseName(cruiseDto.getCruiseName())
                .route(Route.newBuilder()
                        .routeName(cruiseDto.getRouteName())
                        .build())
                .dateStart(cruiseDto.getDateStart())
                .dateEnd(cruiseDto.getDateEnd())
                .cruiseStatus(CruiseStatus.newBuilder()
                        .cruiseStatusName(cruiseDto.getCruiseStatusName())
                        .build())
                .ship(Ship.newBuilder()
                        .shipName(cruiseDto.getShipName())
                        .shipImageSource(cruiseDto.getShipImageSource())
                        .build())
                .build();
    }

    @Override
    public CruiseDto mapEntityToDto(Cruise cruise) {
        return CruiseDto.newBuilder()
                .id(cruise.getId())
                .cruiseName(cruise.getCruiseName())
                .routeName(cruise.getRoute().getRouteName())
                .dateStart(cruise.getDateStart())
                .dateEnd(cruise.getDateEnd())
                .cruiseStatusName(cruise.getCruiseStatus().getCruiseStatusName())
                .shipName(cruise.getShip().getShipName())
                .shipImageSource(cruise.getShip().getShipImage())
                .build();
    }
}
