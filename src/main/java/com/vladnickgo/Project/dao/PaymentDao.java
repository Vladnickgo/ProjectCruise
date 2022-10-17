package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.controller.dto.PaymentRequestDto;
import com.vladnickgo.Project.dao.entity.Payment;

import java.util.List;

public interface PaymentDao {
    Payment addPayment(Payment payment);

    List<Payment> findPaymentByUserId(Integer userId);

    Integer countAll(PaymentRequestDto paymentRequestDto);

    List<Payment> findPaymentsByUserIdAndSortingParameters(PaymentRequestDto paymentRequestDto, Integer firstRecordOnPage);
}
