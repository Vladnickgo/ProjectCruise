package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.controller.dto.CabinTypeRequestDto;
import com.vladnickgo.Project.dao.entity.CabinType;

import java.util.List;

public interface CabinTypeDao {
    List<CabinType> findAll();
    void insertCabinTypesByCabinTypeNumbers(CabinTypeRequestDto cabinTypeRequestDto);
}
