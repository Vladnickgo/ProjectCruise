package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;

import java.sql.SQLException;
import java.util.List;

public interface CruiseDao extends CrudDao<Cruise, Integer> {
    List<Cruise> findAllByParameters(CruiseRequestDtoUtil cruiseRequestDtoUtil);

    Integer countAll();

    void createCruise(Cruise cruise) throws SQLException;

    void updateCruiseStatusByCruiseId(Integer cruiseId, Integer cruiseStatusId);

    Integer getMaxCruiseDuration();

    Integer getMinCruiseDuration();
}
