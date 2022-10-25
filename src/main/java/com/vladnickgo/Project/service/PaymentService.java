package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.PaymentDto;
import com.vladnickgo.Project.controller.dto.PaymentRequestDto;
import com.vladnickgo.Project.controller.dto.PaymentResponseDto;
import com.vladnickgo.Project.service.util.PaymentRequestDtoUtil;

import java.sql.SQLException;
import java.util.List;

public interface PaymentService {

    PaymentResponseDto addPayment(PaymentDto paymentDto);

    Integer getNumberOfPagesByUserIdAndSortingParameters(PaymentRequestDtoUtil paymentRequestDtoUtil);

    Integer getNumberOfPagesByFilterParameters(PaymentRequestDtoUtil paymentRequestDtoUtil);

    List<PaymentResponseDto> findPaymentsByUserIdAndSortingParameters(PaymentRequestDtoUtil paymentRequestDtoUtil);

    List<PaymentResponseDto> findPaymentsByFilterParameters(PaymentRequestDtoUtil paymentRequestDtoUtil);

    void confirmPaymentByPaymentId(Integer paymentId);

    void cancelPayment(Integer paymentId) throws SQLException;
}
