package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.PaymentDto;
import com.vladnickgo.Project.controller.dto.PaymentRequestDto;
import com.vladnickgo.Project.controller.dto.PaymentResponseDto;
import com.vladnickgo.Project.service.util.PaymentRequestDtoUtil;

import java.util.List;

public interface PaymentService {

    PaymentResponseDto addPayment(PaymentDto paymentDto);

    List<PaymentResponseDto> findPaymentsByPaymentRequestDto(Integer userId);

    Integer getNumberOfPages(PaymentRequestDto paymentRequestDto);

    List<PaymentResponseDto> findPaymentsByPaymentRequestDto(PaymentRequestDto paymentRequestDto);

}
