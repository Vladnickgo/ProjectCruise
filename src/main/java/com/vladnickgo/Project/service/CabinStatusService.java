package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.CabinStatusDto;
import com.vladnickgo.Project.controller.dto.CabinStatusRequestDto;

import java.util.List;

public interface CabinStatusService {
    CabinStatusDto findCabinStatusByParameters(Integer cruiseId, Integer cabinTypeId);

    List<CabinStatusRequestDto> getCabinStatusListByParameter(Integer cruiseStatusId);

    Integer findCabinStatusIdByParameters(Integer cruiseStatusId, Integer cabinStatusId);

}
