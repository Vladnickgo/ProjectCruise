package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.controller.dto.CruiseResponseDto;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;

import java.util.List;

public interface CruiseService {
    List<CruiseResponseDto> findAll(CruiseRequestDtoUtil cruiseRequestDtoUtil);

    Integer initNumberOfPage(String numberOfPage);

    Integer initRecordsOnPage(String recordsOnPage);

    Integer getNumberOfPages(Integer recordsOnPage);

    CruiseResponseDto findCruiseById(Integer cruiseId);

    void createCruise(CruiseDto cruiseDto);

    void blockCruiseById(Integer cruiseId);

    void unblockCruiseById(Integer cruiseId);

    Integer getMaxCruiseDuration();

    Integer getMinCruiseDuration();

    Integer getBottomDuration(String bottomDurationStr);

    Integer getTopDuration(String topDurationStr);
}
