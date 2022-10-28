package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;

import java.util.List;

public interface CruiseDao extends CrudDao<Cruise,Integer> {
    List<Cruise> findAllByParameters(CruiseRequestDtoUtil cruiseRequestDtoUtil);

    Integer countAll();


}
