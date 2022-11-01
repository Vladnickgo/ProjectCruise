package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CabinRequestDto;
import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.dao.ShipDao;
import com.vladnickgo.Project.dao.entity.Ship;
import com.vladnickgo.Project.service.ShipService;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.ShipRequestDtoUtil;
import com.vladnickgo.Project.validator.Validator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShipServiceImpl implements ShipService {
    private final ShipDao shipRepository;
    private final Mapper<ShipDto, Ship> mapper;
    private final Validator<ShipDto> shipDtoValidator;
    private static final Integer DEFAULT_NUMBER_OF_PAGE = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 4;

    public ShipServiceImpl(ShipDao shipRepository, Mapper<ShipDto, Ship> mapper, Validator<ShipDto> shipDtoValidator) {
        this.shipRepository = shipRepository;
        this.mapper = mapper;
        this.shipDtoValidator = shipDtoValidator;
    }

    public List<ShipDto> findAllShips() {
        return shipRepository.findAll().stream().map(mapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ShipDto> findAllShipsByNumberOfPageAndRecordsOnPage(ShipRequestDtoUtil shipRequestDtoUtil) {
        return shipRepository.findAllByPageNumberAndItemsOnPage(shipRequestDtoUtil)
                .stream()
                .map(mapper::mapEntityToDto)
                .sorted(shipRequestDtoUtil.getComparator())
                .collect(Collectors.toList());
    }

    private Integer countAll() {
        return shipRepository.countAll();
    }

    @Override
    public void addShip(ShipDto shipDto, List<CabinRequestDto> cabinRequestDtoList) throws SQLException {
        Ship ship = validateAndMapShipDto(shipDto);
        System.out.println(cabinRequestDtoList);
        shipRepository.insertShipAndCabinsAndCabinStatuses(ship, cabinRequestDtoList);
    }

    @Override
    public void updateShip(ShipDto shipDto) {
        Ship ship = validateAndMapShipDto(shipDto);
        shipRepository.update(ship);
    }

    @Override
    public Integer initNumberOfPage(String numberOfPage) {
        return initParameterValue(numberOfPage, DEFAULT_NUMBER_OF_PAGE);
    }

    @Override
    public Integer initRecordsOnPage(String recordsOnPage) {
        return initParameterValue(recordsOnPage, DEFAULT_ITEMS_ON_PAGE);
    }

    private Ship validateAndMapShipDto(ShipDto shipDto) {
        shipDtoValidator.validate(shipDto);
        return mapper.mapDtoToEntity(shipDto);
    }

    private Integer initParameterValue(String stringValue, Integer defaultIntegerValue) {
        return Objects.equals(stringValue, null) || Objects.equals(stringValue, "") ? defaultIntegerValue : Integer.valueOf(stringValue);
    }

    @Override
    public Integer getTotalPages(Integer itemsOnPage) {
        Integer countAll = countAll();
        return countAll / itemsOnPage + (countAll % itemsOnPage > 0 ? 1 : 0);
    }

    @Override
    public List<ShipDto> findAllFreeShipsByDateStartAndDateEnd(LocalDate dateStart, LocalDate dateEnd) {
        return shipRepository.findAllFreeShipsByDateStartAndDateEnd(dateStart, dateEnd)
                .stream()
                .map(mapper::mapEntityToDto)
                .sorted(Comparator.comparing(ShipDto::getShipName))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteShipById(Integer shipId) {
        shipRepository.deleteShipBtId(shipId);
    }

}