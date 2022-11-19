package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.CabinTypeResponseDto;
import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.controller.dto.CruiseResponseDto;
import com.vladnickgo.Project.controller.dto.LocalDateDto;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface CruiseService {
    List<CruiseResponseDto> findAll(CruiseRequestDtoUtil cruiseRequestDtoUtil);

    Integer getNumberOfPages(Integer recordsOnPage);

    CruiseResponseDto findCruiseById(Integer cruiseId);

    void createCruise(CruiseDto cruiseDto) throws SQLException;

    void blockCruiseById(Integer cruiseId);

    void unblockCruiseById(Integer cruiseId);

    Integer getMaxCruiseDuration();

    Integer getMinCruiseDuration();

    LocalDate getMinDateStartForStatusAvailable();

    LocalDate getMaxDateEndForStatusAvailable();

    List<CruiseResponseDto> findAllByDatesAndDuration(CruiseRequestDtoUtil cruiseRequestDtoUtil);

    Integer getNumberOfPagesByDatesAndDuration(CruiseRequestDtoUtil cruiseRequestDtoUtil);

    List<CruiseDto> findAllByDates(LocalDateDto localDateDto);

    List<CabinTypeResponseDto> getNumberOfAllAndBusyCabins(String cruiseId);
}
