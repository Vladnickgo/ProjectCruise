package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.CruiseResponseDto;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.entity.CruiseStatus;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.Ship;

import java.util.Optional;

public class CruiseResponseMapper implements Mapper<CruiseResponseDto, Cruise> {
    @Override
    public Cruise mapDtoToEntity(CruiseResponseDto cruiseResponseDto) {
        if (cruiseResponseDto == null) return null;
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
        if (cruise == null) return null;
        return CruiseResponseDto.newBuilder()
                .id(cruise.getId())
                .cruiseName(cruise.getCruiseName())
                .routeName(getRouteName(cruise))
                .dateStart(cruise.getDateStart())
                .dateEnd(cruise.getDateEnd())
                .nights(cruise.getNights())
                .cruiseStatusName(getCruiseStatusName(cruise))
                .shipName(getShipName(cruise))
                .shipImageSource(getShipImage(cruise))
                .build();
    }

    private String getShipImage(Cruise cruise) {
        return Optional.ofNullable(cruise.getShip()).map(Ship::getShipImage).orElse(null);
    }

    private String getShipName(Cruise cruise) {
        return Optional.ofNullable(cruise.getShip())
                .map(Ship::getShipName)
                .orElse(null);
    }

    private String getCruiseStatusName(Cruise cruise) {
        return Optional.ofNullable(cruise.getCruiseStatus())
                .map(CruiseStatus::getCruiseStatusName)
                .orElse(null);
    }

    private String getRouteName(Cruise cruise) {
        return Optional.ofNullable(cruise.getRoute())
                .map(Route::getRouteName)
                .orElse(null);
    }
}
