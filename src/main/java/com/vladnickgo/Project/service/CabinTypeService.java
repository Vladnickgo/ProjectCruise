package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.CabinTypeDto;

import java.util.List;

public interface CabinTypeService {
    List<CabinTypeDto> findAll();
}
