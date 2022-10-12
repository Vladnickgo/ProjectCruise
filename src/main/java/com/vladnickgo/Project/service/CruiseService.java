package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.CruiseDto;

import java.util.List;

public interface CruiseService {
    List<CruiseDto> findAll(Integer numberOfPage, Integer recordsOnPage);

    Integer initNumberOfPage(String numberOfPage);

    Integer initRecordsOnPage(String recordsOnPage);

    Integer getNumberOfPages(Integer recordsOnPage);

    CruiseDto findCruiseById(Integer cruiseId);
}
