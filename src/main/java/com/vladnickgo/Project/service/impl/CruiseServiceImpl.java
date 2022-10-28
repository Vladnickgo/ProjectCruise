package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CabinTypeRequestDto;
import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.dao.CruiseDao;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.service.CruiseService;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;
import com.vladnickgo.Project.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.CRUISE_NOT_FOUND;

public class CruiseServiceImpl implements CruiseService {
    private static final Integer DEFAULT_NUMBER_OF_PAGE = 1;
    private static final Integer DEFAULT_RECORDS_ON_PAGE = 4;

    private final CruiseDao cruiseRepository;
    private final Mapper<CruiseDto, Cruise> cruiseMapper;
    private final Validator<CruiseDto> validator;

    public CruiseServiceImpl(CruiseDao cruiseRepository, Mapper<CruiseDto, Cruise> cruiseMapper, Validator<CruiseDto> validator) {
        this.cruiseRepository = cruiseRepository;
        this.cruiseMapper = cruiseMapper;
        this.validator = validator;
    }

    @Override
    public List<CruiseDto> findAll(CruiseRequestDtoUtil cruiseRequestDtoUtil) {
        return cruiseRepository.findAllByParameters(cruiseRequestDtoUtil)
                .stream()
                .map(cruiseMapper::mapEntityToDto)
                .sorted(cruiseRequestDtoUtil.extractedComparator())
                .collect(Collectors.toList());
    }

    public Integer getDefaultNumberOfPage() {
        return DEFAULT_NUMBER_OF_PAGE;
    }

    public Integer getDefaultRecordsOnPage() {
        return DEFAULT_RECORDS_ON_PAGE;
    }

    @Override
    public Integer initNumberOfPage(String numberOfPage) {
        return numberOfPage == null ? DEFAULT_NUMBER_OF_PAGE : Integer.valueOf(numberOfPage);
    }

    @Override
    public Integer initRecordsOnPage(String recordsOnPage) {
        return recordsOnPage == null ? DEFAULT_RECORDS_ON_PAGE : Integer.valueOf(recordsOnPage);
    }

    private Integer getFirstRecordOnPage(Integer itemsOnPage, Integer numberOfPage) {
        Integer pages = getNumberOfPages(itemsOnPage);
        return itemsOnPage * ((Math.min(numberOfPage, pages)) - 1);
    }

    public Integer getNumberOfPages(Integer itemsOnPage) {
        Integer size = cruiseRepository.countAll();
        return size / itemsOnPage + (size % itemsOnPage > 0 ? 1 : 0);
    }

    @Override
    public CruiseDto findCruiseById(Integer cruiseId) {
        return cruiseMapper.mapEntityToDto(cruiseRepository.findById(cruiseId)
                .orElseThrow(() -> new IllegalArgumentException(CRUISE_NOT_FOUND)));
    }


}
