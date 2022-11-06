package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.controller.dto.CruiseResponseDto;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CruiseDao extends CrudDao<Cruise, Integer> {
    List<Cruise> findAllByParameters(CruiseRequestDtoUtil cruiseRequestDtoUtil);

    Integer countAll();

    void createCruise(Cruise cruise) throws SQLException;

    void updateCruiseStatusByCruiseId(Integer cruiseId, Integer cruiseStatusId);

    Integer getMaxCruiseDuration();

    Integer getMinCruiseDuration();

    LocalDate getMinDateStart();

    LocalDate getMaxDateEnd();

    List<Cruise> findAllByDatesAndDuration(CruiseRequestDtoUtil cruiseRequestDtoUtil);

    Integer countAllByDatesAndDuration(CruiseRequestDtoUtil cruiseRequestDtoUtil);

    List<Cruise> findAllByDates(LocalDate dateStart, LocalDate dateEnd);

    Map<String, Integer> getEachCabinTypeNumberMap(Integer cruiseId);
}


