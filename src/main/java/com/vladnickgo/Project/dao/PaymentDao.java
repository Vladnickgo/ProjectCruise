package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.controller.dto.PaymentRequestDto;
import com.vladnickgo.Project.dao.entity.Payment;
import com.vladnickgo.Project.service.util.PaymentRequestDtoUtil;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDao {
    Payment addPayment(Payment payment);

    Integer countAllPaymentsByUserIdAndFilterParameters(PaymentRequestDtoUtil paymentRequestDtoUtil);

    Integer countAllPaymentsByFilterParameters(PaymentRequestDtoUtil paymentRequestDtoUtil);

    List<Payment> findPaymentsByUserIdAndFilterParameters(PaymentRequestDtoUtil paymentRequestDtoUtil, Integer firstRecordOnPage);

    List<Payment> findPaymentsByFilterParameters(PaymentRequestDtoUtil paymentRequestDtoUtil, Integer firstRecordOnPage);

    void updatePaymentStatusByPaymentId(Integer paymentId, Integer paymentStatusId, Integer orderStatusId) throws SQLException;
}
