package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.ShipDto;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.*;

public class ShipValidator implements Validator<ShipDto> {
    private static final ShipDto EMPTY_SHIP_VALUE = ShipDto.newBuilder().build();

    @Override
    public void validate(ShipDto entity) {
        if (entity == null) throw new IllegalArgumentException(SHIP_IS_NULL_MESSAGE);
        if (Objects.equals(entity, EMPTY_SHIP_VALUE)) throw new IllegalArgumentException(SHIP_IS_EMPTY);
        validateByParam(ShipDto::getShipName, SHIP_NAME_IS_EMPTY, entity);
        if (entity.getNumberOfStaff() == null) throw new IllegalArgumentException(NOT_VALID_NUMBER_OF_PERSONS);
        if (entity.getPassengersCapacity() == null) throw new IllegalArgumentException(NOT_VALID_NUMBER_OF_CABINS);
        validateByParam(ShipDto::getShipImage, SHIP_IMAGE_IS_EMPTY, entity);
    }

    private void validateByParam(Function<ShipDto, String> param, String errorMessage, ShipDto ship) {
        Optional.ofNullable(param.apply(ship))
                .orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }
}
