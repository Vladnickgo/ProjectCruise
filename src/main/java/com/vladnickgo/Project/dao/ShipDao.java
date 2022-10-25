package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.controller.dto.CabinRequestDto;
import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.dao.entity.Ship;
import com.vladnickgo.Project.service.util.ShipRequestDtoUtil;

import java.sql.SQLException;
import java.util.List;

public interface ShipDao extends CrudDao<Ship,Integer>{
    List<Ship> findAll();

    void insertShipAndCabinsAndCabinStatuses(Ship ship,List<CabinRequestDto> cabinRequestDtoList) throws SQLException;

    Integer countAll();

    List<Ship> findAllByPageNumberAndItemsOnPage(ShipRequestDtoUtil shipRequestDtoUtil);
}
