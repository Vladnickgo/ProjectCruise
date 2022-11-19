package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.CruiseDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.*;

public class CruiseValidator implements Validator<CruiseDto> {

    public static final String EMPTY_CRUISE_STATUS_ID = "Cruise statusId is empty";
    public static final String EMPTY_SHIP_ID = "Ship id is empty";
    public static final String EMPTY_ROUTE_ID="Route id is null";

    @Override
    public void validate(CruiseDto entity) {
        notNullValidate(entity, CRUISE_IS_NULL_MESSAGE);
        if (entity.getCruiseName()==null||entity.getCruiseName().isBlank()) throw new IllegalArgumentException(EMPTY_CRUISE_NAME);
        notNullValidate(entity.getRouteID(), EMPTY_ROUTE_ID);
        validateByParam(CruiseDto::getDateStart, EMPTY_DATE_START, entity);
        notNullValidate(entity.getShipId(), EMPTY_SHIP_ID);
        validateByParam(CruiseDto::getDateEnd, EMPTY_DATE_END, entity);
        LocalDate dateStart = entity.getDateStart();
        LocalDate dateEnd = entity.getDateEnd();
        if (dateStart.isAfter(dateEnd) || dateStart.equals(dateEnd)) throw new IllegalArgumentException(DATE_START_MORE_OR_EQUAL_THAN_DATE_END);}

    private void notNullValidate(Object object, String errorMessage) {
        Optional.ofNullable(object).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private void validateByParam(Function<CruiseDto, LocalDate> param, String errorMessage, CruiseDto cruiseDto) {
        Optional.ofNullable(param.apply(cruiseDto)).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }
}
