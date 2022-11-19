package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.CruiseResponseDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.*;

public class CruiseResponseValidator implements Validator<CruiseResponseDto> {

    @Override
    public void validate(CruiseResponseDto entity) {
        notNullValidate(entity, CRUISE_IS_NULL_MESSAGE);
        notNullValidate(entity.getCruiseName(), EMPTY_CRUISE_NAME);
        notNullValidate(entity.getRouteName(), EMPTY_ROUTE_NAME);
        notNullValidate(entity.getCruiseStatusName(), EMPTY_CRUISE_STATUS_NAME);
        validateByParam(CruiseResponseDto::getDateStart, EMPTY_DATE_START, entity);
        validateByParam(CruiseResponseDto::getDateEnd, EMPTY_DATE_END, entity);
        datesValidate(CruiseResponseDto::getDateStart, CruiseResponseDto::getDateEnd, (u, v) -> (u.isAfter(v) || u.equals(v)), DATE_START_MORE_OR_EQUAL_THAN_DATE_END, entity);
    }

    private void notNullValidate(Object object, String errorMessage) {
        Optional.ofNullable(object).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private void validateByParam(Function<CruiseResponseDto, LocalDate> param, String errorMessage, CruiseResponseDto cruiseResponseDto) {
        Optional.ofNullable(param.apply(cruiseResponseDto)).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private void datesValidate(Function<CruiseResponseDto, LocalDate> firstParam, Function<CruiseResponseDto, LocalDate> secondParam,
                               BiPredicate<LocalDate, LocalDate> predicate, String errorMessage, CruiseResponseDto cruiseResponseDto) {
        LocalDate firstDate = firstParam.apply(cruiseResponseDto);
        LocalDate secondDate = secondParam.apply(cruiseResponseDto);
        if (predicate.test(firstDate, secondDate)) throw new IllegalArgumentException(errorMessage);
    }
}

