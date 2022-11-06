package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.controller.dto.CabinRequestDto;
import com.vladnickgo.Project.controller.dto.LocalDateDto;
import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.dao.entity.Ship;
import com.vladnickgo.Project.service.util.ShipRequestDtoUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ShipDao extends CrudDao<Ship,Integer>{
    List<Ship> findAll();

    void insertShipAndCabinsAndCabinStatuses(Ship ship,List<CabinRequestDto> cabinRequestDtoList) throws SQLException;

    Integer countAll();

    List<Ship> findAllByPageNumberAndItemsOnPage(ShipRequestDtoUtil shipRequestDtoUtil);

    List<Ship> findAllFreeShipsByDateStartAndDateEnd(LocalDate dateStart, LocalDate dateEnd);

    void deleteShipBtId(Integer shipId);

    Map<String, Integer> getNumberOfCruisesForShips(LocalDateDto localDateDto);
}
