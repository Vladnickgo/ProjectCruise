package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.controller.dto.CruiseResponseDto;
import com.vladnickgo.Project.dao.CruiseDao;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.service.CruiseService;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;
import com.vladnickgo.Project.validator.Validator;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.CRUISE_NOT_FOUND;

public class CruiseServiceImpl implements CruiseService {
    private static final Integer DEFAULT_NUMBER_OF_PAGE = 1;
    private static final Integer DEFAULT_RECORDS_ON_PAGE = 4;
    private static final Integer CRUISE_STATUS_AVAILABLE = 1;
    private static final Integer CRUISE_STATUS_NOT_AVAILABLE = 4;

    private final CruiseDao cruiseRepository;
    private final Mapper<CruiseResponseDto, Cruise> cruiseResponseMapper;
    private final Mapper<CruiseDto, Cruise> cruiseMapper;
    private final Validator<CruiseResponseDto> validator;

    public CruiseServiceImpl(CruiseDao cruiseRepository, Mapper<CruiseResponseDto, Cruise> cruiseMapper, Mapper<CruiseDto, Cruise> cruiseMapper1, Validator<CruiseResponseDto> validator) {
        this.cruiseRepository = cruiseRepository;
        this.cruiseResponseMapper = cruiseMapper;
        this.cruiseMapper = cruiseMapper1;
        this.validator = validator;
    }

    @Override
    public List<CruiseResponseDto> findAll(CruiseRequestDtoUtil cruiseRequestDtoUtil) {
        return cruiseRepository.findAllByParameters(cruiseRequestDtoUtil)
                .stream()
                .map(cruiseResponseMapper::mapEntityToDto)
                .sorted(cruiseRequestDtoUtil.extractedComparator())
                .collect(Collectors.toList());
    }

    @Override
    public Integer initNumberOfPage(String numberOfPage) {
        return numberOfPage == null ? DEFAULT_NUMBER_OF_PAGE : Integer.valueOf(numberOfPage);
    }

    @Override
    public Integer initRecordsOnPage(String recordsOnPage) {
        return recordsOnPage == null ? DEFAULT_RECORDS_ON_PAGE : Integer.valueOf(recordsOnPage);
    }

    public Integer getNumberOfPages(Integer itemsOnPage) {
        Integer size = cruiseRepository.countAll();
        return size / itemsOnPage + (size % itemsOnPage > 0 ? 1 : 0);
    }

    @Override
    public CruiseResponseDto findCruiseById(Integer cruiseId) {
        return cruiseResponseMapper.mapEntityToDto(cruiseRepository.findById(cruiseId)
                .orElseThrow(() -> new IllegalArgumentException(CRUISE_NOT_FOUND)));
    }

    @Override
    public void createCruise(CruiseDto cruiseDto) {
        Cruise cruise = cruiseMapper.mapDtoToEntity(cruiseDto);
        try {
            cruiseRepository.createCruise(cruise);
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public void blockCruiseById(Integer cruiseId) {
        cruiseRepository.updateCruiseStatusByCruiseId(cruiseId, CRUISE_STATUS_NOT_AVAILABLE);
    }

    @Override
    public Integer getMaxCruiseDuration() {
        return cruiseRepository.getMaxCruiseDuration();
    }

    @Override
    public Integer getMinCruiseDuration() {
        return cruiseRepository.getMinCruiseDuration();
    }

    @Override
    public Integer getBottomDuration(String bottomDurationStr) {
        return Objects.equals(bottomDurationStr, null) ? getMinCruiseDuration() : Integer.valueOf(bottomDurationStr);
    }

    @Override
    public Integer getTopDuration(String topDurationStr) {
        return Objects.equals(topDurationStr,null)?getMaxCruiseDuration():Integer.valueOf(topDurationStr);
    }

    @Override
    public void unblockCruiseById(Integer cruiseId) {
        cruiseRepository.updateCruiseStatusByCruiseId(cruiseId, CRUISE_STATUS_AVAILABLE);
    }
}
