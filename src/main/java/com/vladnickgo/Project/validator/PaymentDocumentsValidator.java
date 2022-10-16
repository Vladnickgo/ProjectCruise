package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.PaymentDocumentsDto;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.vladnickgo.Project.validator.Patterns.*;
import static com.vladnickgo.Project.validator.ValidatorErrorMessage.*;

public class PaymentDocumentsValidator implements Validator<PaymentDocumentsDto>{
    @Override
    public void validate(PaymentDocumentsDto entity) {
        validateByParam(PaymentDocumentsDto::getIdCard, FILE_NAME_PATTERN, ID_CARD_FILE_NAME_IS_NOT_VALID,entity);
        validateByParam(PaymentDocumentsDto::getCardNumber, CARD_NUMBER_PATTERN, CARD_NUMBER_IS_NOT_VALID,entity);
        validateByParam(PaymentDocumentsDto::getCvvCode, CVV_PATTERN, CVV_NUMBER_IS_NOT_VALID,entity);
    }

    private void validateByParam(Function<PaymentDocumentsDto, String> param, Pattern pattern, String errorMessage,  PaymentDocumentsDto paymentData) {
        Optional.ofNullable(param.apply(paymentData))
                .filter(x -> pattern.matcher(x).matches())
                .orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }
}
