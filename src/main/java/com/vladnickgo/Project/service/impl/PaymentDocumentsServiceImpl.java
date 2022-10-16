package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.PaymentDocumentsDto;
import com.vladnickgo.Project.service.PaymentDocumentsService;
import com.vladnickgo.Project.validator.Validator;

public class PaymentDocumentsServiceImpl implements PaymentDocumentsService {
    private final Validator<PaymentDocumentsDto> validator;

    public PaymentDocumentsServiceImpl(Validator<PaymentDocumentsDto> validator) {
        this.validator = validator;
    }

    @Override
    public void checkCardData(PaymentDocumentsDto paymentDocumentsDto) {
        validator.validate(paymentDocumentsDto);
    }
}
