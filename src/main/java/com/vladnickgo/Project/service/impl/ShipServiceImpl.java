package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CabinRequestDto;
import com.vladnickgo.Project.controller.dto.LocalDateDto;
import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.dao.ShipDao;
import com.vladnickgo.Project.dao.entity.Ship;
import com.vladnickgo.Project.service.ShipService;
import com.vladnickgo.Project.service.impl.exception.EntityAlreadyExistException;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.ShipRequestDtoUtil;
import com.vladnickgo.Project.validator.Validator;
import com.vladnickgo.Project.validator.ValidatorErrorMessage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.*;

public class ShipServiceImpl implements ShipService {
    private final ShipDao shipRepository;
    private final Mapper<ShipDto, Ship> mapper;
    private final Validator<ShipDto> shipDtoValidator;

    public ShipServiceImpl(ShipDao shipRepository, Mapper<ShipDto, Ship> mapper, Validator<ShipDto> shipDtoValidator) {
        this.shipRepository = shipRepository;
        this.mapper = mapper;
        this.shipDtoValidator = shipDtoValidator;
    }

    @Override
    public List<ShipDto> findAllShipsByNumberOfPageAndRecordsOnPage(ShipRequestDtoUtil shipRequestDtoUtil) {
        return shipRepository.findAllByPageNumberAndItemsOnPage(shipRequestDtoUtil)
                .stream()
                .map(mapper::mapEntityToDto)
                .sorted(shipRequestDtoUtil.getComparator())
                .collect(Collectors.toList());
    }

    public Integer countAll() {
        return shipRepository.countAll();
    }

    @Override
    public void addShip(ShipDto shipDto, List<CabinRequestDto> cabinRequestDtoList) throws SQLException {
        Ship ship = validateAndMapShipDto(shipDto);
        if (shipRepository.findByName(shipDto.getShipName()).isPresent()) {
            String message = String.format(ValidatorErrorMessage.SHIP_ALREADY_EXIST_ERROR_MESSAGE, ship.getShipName());
            throw new EntityAlreadyExistException(message);
        }
        shipRepository.insertShipAndCabinsAndCabinStatuses(ship, cabinRequestDtoList);
    }

    @Override
    public void updateShip(ShipDto shipDto) {
        Ship ship = validateAndMapShipDto(shipDto);
        shipRepository.update(ship);
    }

    private Ship validateAndMapShipDto(ShipDto shipDto) {
        shipDtoValidator.validate(shipDto);
        return mapper.mapDtoToEntity(shipDto);
    }

    @Override
    public Integer getTotalPages(Integer itemsOnPage) {
        Integer countAll = countAll();
        return countAll / itemsOnPage + (countAll % itemsOnPage > 0 ? 1 : 0);
    }

    @Override
    public List<ShipDto> findAllFreeShipsByDateStartAndDateEnd(LocalDate dateStart, LocalDate dateEnd) {
        validateLocalDate(dateStart, DATE_START_IS_NULL);
        validateLocalDate(dateEnd, DATE_END_IS_NULL);
        if (dateStart.isAfter(dateEnd)) throw new IllegalArgumentException(DATE_START_MORE_OR_EQUAL_THAN_DATE_END);
        return shipRepository.findAllFreeShipsByDateStartAndDateEnd(dateStart, dateEnd)
                .stream()
                .map(mapper::mapEntityToDto)
                .sorted(Comparator.comparing(ShipDto::getShipName))
                .collect(Collectors.toList());
    }

    private void validateLocalDate(LocalDate date, String message) {
        if (date == null) throw new IllegalArgumentException(message);
    }

    @Override
    public Map<String, Integer> getNumberOfCruisesForShips(LocalDateDto localDateDto) {
        return shipRepository.getNumberOfCruisesForShips(localDateDto);
    }

    @Override
    public void deleteShipById(Integer shipId) {
        shipRepository.deleteShipById(shipId);
    }
}