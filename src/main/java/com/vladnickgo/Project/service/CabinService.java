package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.CabinDto;

public interface CabinService {
    CabinDto findFreeCabinByParameters(Integer cabinTypeId, Integer cruiseId);
}
