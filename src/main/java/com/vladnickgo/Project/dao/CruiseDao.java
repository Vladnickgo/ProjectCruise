package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.dao.entity.Cruise;

import java.util.List;

public interface CruiseDao extends CrudDao<Cruise,Integer> {
    List<Cruise> findAllByParameters(Integer firstRecordOnPage, Integer itemsOnPage);

    Integer countAll();


}
