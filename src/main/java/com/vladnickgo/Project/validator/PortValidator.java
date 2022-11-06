package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.PortDto;

import java.util.Optional;
import java.util.function.Function;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.*;

public class PortValidator implements Validator<PortDto> {
    @Override
    public void validate(PortDto entity) {
        if (entity == null) throw new IllegalArgumentException(PORT_IS_NULL);
        validateByParam(PortDto::getPortNameUa, PORT_NAME_UA_IS_NULL_MESSAGE, entity);
        validateByParam(PortDto::getPortNameEn, PORT_NAME_EN_IS_NULL_MESSAGE, entity);
        validateByParam(PortDto::getCountryUa, COUNTRY_NAME_UA_IS_NULL_MESSAGE, entity);
        validateByParam(PortDto::getCountryEn, PORT_NAME_EN_IS_NULL_MESSAGE, entity);
    }

    private void validateByParam(Function<PortDto, String> param, String errorMessage, PortDto portDto) {
        Optional.ofNullable(param.apply(portDto))
                .orElseThrow(() -> new IllegalArgumentException(errorMessage));
        if (param.apply(portDto).isBlank())throw new IllegalArgumentException(errorMessage);
    }
}
