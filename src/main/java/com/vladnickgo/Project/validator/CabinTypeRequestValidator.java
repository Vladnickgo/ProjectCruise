package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.CabinTypeRequestDto;

import java.util.Optional;
import java.util.function.Function;

public class CabinTypeRequestValidator implements Validator<CabinTypeRequestDto> {
    private static final String CABIN_TYPE_REQUEST_DTO_IS_NULL = "CabinTypeRequestDto is null";
    private static final String NUMBER_OF_SINGLE_CABIN_IS_NULL = "Number of single cabin is null";
    private static final String NUMBER_OF_INSIDE_CABIN_IS_NULL = "Number of inside cabin is null";
    private static final String NUMBER_OF_OCEAN_VIEW_CABIN_IS_NULL = "Number of ocean view cabin is null";
    private static final String NUMBER_OF_BALCONY_CABIN_IS_NULL = "Number of balcony cabin is null";
    private static final String NUMBER_OF_SUITE_CABIN_IS_NULL = "Number of suite cabin is null";

    @Override
    public void validate(CabinTypeRequestDto entity) {
        if (entity == null) throw new IllegalArgumentException(CABIN_TYPE_REQUEST_DTO_IS_NULL);
        validateByParam(CabinTypeRequestDto::getNumberOfSingleCabin,NUMBER_OF_SINGLE_CABIN_IS_NULL,entity);
        validateByParam(CabinTypeRequestDto::getNumberOfInsideCabin,NUMBER_OF_INSIDE_CABIN_IS_NULL,entity);
        validateByParam(CabinTypeRequestDto::getNumberOfOceanViewCabin,NUMBER_OF_OCEAN_VIEW_CABIN_IS_NULL,entity);
        validateByParam(CabinTypeRequestDto::getNumberOfBalconyCabin,NUMBER_OF_BALCONY_CABIN_IS_NULL,entity);
        validateByParam(CabinTypeRequestDto::getNumberOfSuiteCabin,NUMBER_OF_SUITE_CABIN_IS_NULL,entity);
    }

    private void validateByParam(Function<CabinTypeRequestDto, String> param, String errorMessage, CabinTypeRequestDto cabinTypeRequestDto) {
        Optional.ofNullable(param.apply(cabinTypeRequestDto)).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }
}
