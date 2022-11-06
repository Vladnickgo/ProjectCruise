package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.CabinRequestDto;
import com.vladnickgo.Project.controller.dto.LocalDateDto;
import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.service.util.ShipRequestDtoUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ShipService {
    List<ShipDto> findAllShipsByNumberOfPageAndRecordsOnPage(ShipRequestDtoUtil shipRequestDtoUtil);

    void addShip(ShipDto shipDto, List<CabinRequestDto> cabinRequestDtoList) throws SQLException;

    void updateShip(ShipDto shipDto);

    Integer getTotalPages(Integer itemsOnPage);

    List<ShipDto> findAllFreeShipsByDateStartAndDateEnd(LocalDate dateStart, LocalDate dateEnd);

    void deleteShipById(Integer shipId);

    Map<String,Integer> getNumberOfCruisesForShips(LocalDateDto localDateDto);
}
