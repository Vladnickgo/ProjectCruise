package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.CabinRequestDto;
import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.service.util.ShipRequestDtoUtil;

import java.sql.SQLException;
import java.util.List;

public interface ShipService {
    List<ShipDto> findAllShipsByNumberOfPageAndRecordsOnPage(ShipRequestDtoUtil shipRequestDtoUtil);

    void addShip(ShipDto shipDto, List<CabinRequestDto> cabinRequestDtoList) throws SQLException;

    void updateShip(ShipDto shipDto);

    Integer initNumberOfPage(String numberOfPage);

    Integer initRecordsOnPage(String recordsOnPage);

    Integer getTotalPages(Integer itemsOnPage);
}
