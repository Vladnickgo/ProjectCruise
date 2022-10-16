package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.PaymentDto;
import com.vladnickgo.Project.controller.dto.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto addPayment(PaymentDto paymentDto);
}
