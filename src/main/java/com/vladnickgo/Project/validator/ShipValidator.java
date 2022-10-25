package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.ShipDto;

import java.util.Optional;
import java.util.function.Function;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.*;

public class ShipValidator implements Validator<ShipDto> {

    @Override
    public void validate(ShipDto entity) {
        if (entity == null) throw new IllegalArgumentException(SHIP_IS_NULL_MESSAGE);
        if(entity.getNumberOfStaff()==null)throw new IllegalArgumentException(NOT_VALID_NUMBER_OF_PERSONS);
        validateByParam(ShipDto::getShipName,SHIP_NAME_IS_EMPTY,entity);
        validateByParam(ShipDto::getShipImage,SHIP_IMAGE_IS_EMPTY,entity);

    }

    private void validateByParam(Function<ShipDto, String> param, String errorMessage, ShipDto ship) {
        Optional.ofNullable(param.apply(ship))
                .orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }
}
