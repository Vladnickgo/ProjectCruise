package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.PaymentDocumentsDto;

public interface PaymentDocumentsService {
    void checkCardData(PaymentDocumentsDto paymentDocumentsDto);
}
