package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;

import java.util.List;

public interface CruiseService {
    List<CruiseDto> findAll(CruiseRequestDtoUtil cruiseRequestDtoUtil);

    Integer initNumberOfPage(String numberOfPage);

    Integer initRecordsOnPage(String recordsOnPage);

    Integer getNumberOfPages(Integer recordsOnPage);

    CruiseDto findCruiseById(Integer cruiseId);
}
