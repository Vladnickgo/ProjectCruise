package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.dao.entity.CabinStatus;

import java.util.Map;

public interface CabinStatusDao {
    CabinStatus findCabinStatusByParameters(Integer cruiseId, Integer cabinTypeId);

    Map<CabinStatus,Integer> getCabinStatusListByParameter(Integer cruiseStatusId);

    Integer findCabinStatusIdByParameters(Integer cruiseStatusId, Integer cabinStatusId);
}
