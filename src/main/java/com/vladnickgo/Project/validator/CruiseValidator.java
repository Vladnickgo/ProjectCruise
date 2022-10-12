package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.CruiseDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.*;

public class CruiseValidator implements Validator<CruiseDto> {

    @Override
    public void validate(CruiseDto entity) {
        notNullValidate(entity, CRUISE_IS_NULL_MESSAGE);
        notNullValidate(entity.getCruiseName(), EMPTY_CRUISE_NAME);
        notNullValidate(entity.getRouteName(), EMPTY_ROUTE_NAME);
        notNullValidate(entity.getCruiseStatusName(), EMPTY_CRUISE_STATUS_NAME);
        validateByParam(CruiseDto::getDateStart, EMPTY_DATE_START, entity);
        validateByParam(CruiseDto::getDateEnd, EMPTY_DATE_END, entity);
        datesValidate(CruiseDto::getDateStart, CruiseDto::getDateEnd, (u, v) -> (u.isAfter(v) || u.equals(v)), DATE_START_MORE_OR_EQUAL_THAN_DATE_END, entity);
    }

    private void notNullValidate(Object object, String errorMessage) {
        Optional.ofNullable(object).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private void validateByParam(Function<CruiseDto, LocalDate> param, String errorMessage, CruiseDto cruiseDto) {
        Optional.ofNullable(param.apply(cruiseDto)).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private void datesValidate(Function<CruiseDto, LocalDate> firstParam, Function<CruiseDto, LocalDate> secondParam,
                               BiPredicate<LocalDate, LocalDate> predicate, String errorMessage, CruiseDto cruiseDto) {
        LocalDate firstDate = firstParam.apply(cruiseDto);
        LocalDate secondDate = secondParam.apply(cruiseDto);
        if (predicate.test(firstDate, secondDate)) throw new IllegalArgumentException(errorMessage);

    }

}
