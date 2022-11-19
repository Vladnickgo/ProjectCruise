package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.OrderDto;
import com.vladnickgo.Project.controller.dto.PaymentDto;
import com.vladnickgo.Project.dao.entity.*;

import java.time.LocalDate;
import java.util.Optional;

public class PaymentMapper implements Mapper<PaymentDto, Payment> {
    @Override
    public Payment mapDtoToEntity(PaymentDto paymentDto) {
        if (paymentDto == null) return null;
        return Payment.newBuilder()
                .id(paymentDto.getId())
                .order(Order.newBuilder()
                        .id(paymentDto.getId())
                        .user(User.newBuilder()
                                .id(getUserId(paymentDto))
                                .build())
                        .userDocuments(getUserDocuments(paymentDto))
                        .cabinStatus(CabinStatus.newBuilder()
                                .id(getCabinStatusId(paymentDto))
                                .build())
                        .orderDate(getOrderDate(paymentDto))
                        .orderStatus(OrderStatus.newBuilder()
                                .id(getOrderStatusId(paymentDto))
                                .build())
                        .cruise(Cruise.newBuilder()
                                .id(getCruiseId(paymentDto))
                                .build())
                        .build())
                .paymentDate(paymentDto.getPaymentDate())
                .amount(paymentDto.getAmount())
                .build();
    }

    private Integer getCruiseId(PaymentDto paymentDto) {
        return Optional.ofNullable(paymentDto.getOrderDto())
                .map(OrderDto::getCruiseId)
                .orElse(null);
    }

    private Integer getOrderStatusId(PaymentDto paymentDto) {
        return Optional.ofNullable(paymentDto.getOrderDto())
                .map(OrderDto::getOrderStatusId)
                .orElse(null);
    }

    private LocalDate getOrderDate(PaymentDto paymentDto) {
        return Optional.ofNullable(paymentDto.getOrderDto())
                .map(OrderDto::getOrderDate)
                .orElse(null);
    }

    private Integer getCabinStatusId(PaymentDto paymentDto) {
        return Optional.ofNullable(paymentDto.getOrderDto())
                .map(OrderDto::getCabinStatusId)
                .orElse(null);
    }

    private String getUserDocuments(PaymentDto paymentDto) {
        return Optional.ofNullable(paymentDto.getOrderDto())
                .map(OrderDto::getUserDocuments)
                .orElse(null);
    }

    private Integer getUserId(PaymentDto paymentDto) {
        return Optional.ofNullable(paymentDto.getOrderDto()).map(OrderDto::getUserId).orElse(null);
    }

    @Override
    public PaymentDto mapEntityToDto(Payment payment) {
        if (payment == null) return null;
        return PaymentDto.newBuilder()
                .id(payment.getId())
                .orderDto(OrderDto.newBuilder()
                        .id(getOrderId(payment))
                        .userId(getId(payment))
                        .userDocuments(getUserDocuments(payment))
                        .cabinStatusId(getCabinStatusId(payment))
                        .orderDate(getOrderDate(payment))
                        .orderStatusId(getOrderStatusId(payment))
                        .cruiseId(getCruiseId(payment))
                        .build())
                .paymentDate(payment.getPaymentDate())
                .amount(payment.getAmount())
                .build();
    }

    private Integer getCruiseId(Payment payment) {
        return Optional.ofNullable(payment.getOrder())
                .map(Order::getCruise)
                .map(Cruise::getId)
                .orElse(null);
    }

    private Integer getOrderStatusId(Payment payment) {
        return Optional.ofNullable(payment.getOrder())
                .map(Order::getOrderStatus)
                .map(OrderStatus::getId)
                .orElse(null);
    }

    private LocalDate getOrderDate(Payment payment) {
        return Optional.ofNullable(payment.getOrder())
                .map(Order::getOrderDate)
                .orElse(null);
    }

    private Integer getCabinStatusId(Payment payment) {
        return Optional.ofNullable(payment.getOrder())
                .map(Order::getCabinStatus)
                .map(CabinStatus::getId)
                .orElse(null);
    }

    private String getUserDocuments(Payment payment) {
        return Optional.ofNullable(payment.getOrder())
                .map(Order::getUserDocuments)
                .orElse(null);
    }

    private Integer getId(Payment payment) {
        return Optional.ofNullable(payment.getOrder())
                .map(Order::getUser)
                .map(User::getId)
                .orElse(null);
    }

    private Integer getOrderId(Payment payment) {
        return Optional.ofNullable(payment.getOrder()).map(Order::getId).orElse(null);
    }
}
