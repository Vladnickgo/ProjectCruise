package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.dao.entity.Cabin;

public interface CabinDao {
    Cabin findFreeCabinByParameters(Integer roomTypeId, Integer cruiseId);
}
