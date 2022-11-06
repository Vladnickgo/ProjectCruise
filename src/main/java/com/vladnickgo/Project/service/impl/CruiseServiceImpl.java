package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CabinTypeResponseDto;
import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.controller.dto.CruiseResponseDto;
import com.vladnickgo.Project.controller.dto.LocalDateDto;
import com.vladnickgo.Project.dao.CruiseDao;
import com.vladnickgo.Project.dao.OrderDao;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.service.CruiseService;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;
import com.vladnickgo.Project.validator.Validator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.CRUISE_NOT_FOUND;

public class CruiseServiceImpl implements CruiseService {
    private static final Integer CRUISE_STATUS_AVAILABLE = 1;
    private static final Integer CRUISE_STATUS_NOT_AVAILABLE = 4;

    private final CruiseDao cruiseRepository;
    private final OrderDao orderRepository;
    private final Mapper<CruiseResponseDto, Cruise> cruiseResponseMapper;
    private final Mapper<CruiseDto, Cruise> cruiseMapper;
    private final Validator<CruiseResponseDto> validator;

    public CruiseServiceImpl(CruiseDao cruiseRepository, OrderDao orderRepository, Mapper<CruiseResponseDto, Cruise> cruiseMapper, Mapper<CruiseDto, Cruise> cruiseMapper1, Validator<CruiseResponseDto> validator) {
        this.cruiseRepository = cruiseRepository;
        this.orderRepository = orderRepository;
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
    public LocalDate getMinDateStartForStatusAvailable() {
        return cruiseRepository.getMinDateStart();
    }

    @Override
    public List<CabinTypeResponseDto> getNumberOfAllAndBusyCabins(String cruiseId) {
        String s = Optional.ofNullable(cruiseId).orElse("0");
        Integer cruiseIdInteger = Integer.valueOf(s);
        Map<String, Integer> cabinTypeMap = cruiseRepository.getEachCabinTypeNumberMap(cruiseIdInteger);
        Map<String, Integer> busyCabinTypeMap = orderRepository.getEachBusyCabinTypeNumberMap(cruiseIdInteger);

        return cabinTypeMap.entrySet().stream().map(t -> CabinTypeResponseDto.newBuilder()
                        .cabinTypeName(t.getKey())
                        .numberOfCabins(t.getValue())
                        .numberOfBusyCabins(busyCabinTypeMap.getOrDefault(t.getKey(), 0))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CruiseDto> findAllByDates(LocalDateDto localDateDto) {
        LocalDate dateStart = localDateDto.getDateStart();
        LocalDate dateEnd = localDateDto.getDateEnd();
        return cruiseRepository.findAllByDates(dateStart, dateEnd).stream()
                .map(cruiseMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getNumberOfPagesByDatesAndDuration(CruiseRequestDtoUtil cruiseRequestDtoUtil) {
        Integer size = cruiseRepository.countAllByDatesAndDuration(cruiseRequestDtoUtil);
        Integer itemsOnPage = cruiseRequestDtoUtil.getItemsOnPage();
        return size / itemsOnPage + (size % itemsOnPage > 0 ? 1 : 0);
    }

    @Override
    public List<CruiseResponseDto> findAllByDatesAndDuration(CruiseRequestDtoUtil cruiseRequestDtoUtil) {
        return cruiseRepository.findAllByDatesAndDuration(cruiseRequestDtoUtil).stream()
                .map(cruiseResponseMapper::mapEntityToDto)
                .sorted(Comparator.comparing(CruiseResponseDto::getCruiseName))
                .collect(Collectors.toList());
    }

    @Override
    public LocalDate getMaxDateEndForStatusAvailable() {
        return cruiseRepository.getMaxDateEnd();
    }

    @Override
    public void unblockCruiseById(Integer cruiseId) {
        cruiseRepository.updateCruiseStatusByCruiseId(cruiseId, CRUISE_STATUS_AVAILABLE);
    }
}
