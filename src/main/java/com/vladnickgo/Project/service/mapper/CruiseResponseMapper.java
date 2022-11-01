package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CruiseResponseDto;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.entity.CruiseStatus;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.Ship;

public class CruiseResponseMapper implements Mapper<CruiseResponseDto, Cruise>{
    @Override
    public Cruise mapDtoToEntity(CruiseResponseDto cruiseResponseDto) {
        return Cruise.newBuilder()
                .id(cruiseResponseDto.getId())
                .cruiseName(cruiseResponseDto.getCruiseName())
                .route(Route.newBuilder()
                        .routeName(cruiseResponseDto.getRouteName())
                        .build())
                .dateStart(cruiseResponseDto.getDateStart())
                .dateEnd(cruiseResponseDto.getDateEnd())
                .nights(cruiseResponseDto.getNights())
                .cruiseStatus(CruiseStatus.newBuilder()
                        .cruiseStatusName(cruiseResponseDto.getCruiseStatusName())
                        .build())
                .ship(Ship.newBuilder()
                        .shipName(cruiseResponseDto.getShipName())
                        .shipImageSource(cruiseResponseDto.getShipImageSource())
                        .build())
                .build();
    }

    @Override
    public CruiseResponseDto mapEntityToDto(Cruise cruise) {
        return CruiseResponseDto.newBuilder()
                .id(cruise.getId())
                .cruiseName(cruise.getCruiseName())
                .routeName(cruise.getRoute().getRouteName())
                .dateStart(cruise.getDateStart())
                .dateEnd(cruise.getDateEnd())
                .nights(cruise.getNights())
                .cruiseStatusName(cruise.getCruiseStatus().getCruiseStatusName())
                .shipName(cruise.getShip().getShipName())
                .shipImageSource(cruise.getShip().getShipImage())
                .build();
    }
}
