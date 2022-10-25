package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.OrderDto;
import com.vladnickgo.Project.controller.dto.PaymentDto;
import com.vladnickgo.Project.dao.entity.*;

public class PaymentMapper implements Mapper<PaymentDto, Payment> {
    @Override
    public Payment mapDtoToEntity(PaymentDto paymentDto) {
        return Payment.newBuilder()
                .id(paymentDto.getId())
                .order(Order.newBuilder()
                        .id(paymentDto.getId())
                        .user(User.newBuilder()
                                .id(paymentDto.getOrderDto().getUserId())
                                .build())
                        .userDocuments(paymentDto.getOrderDto().getUserDocuments())
                        .cabinStatus(CabinStatus.newBuilder()
                                .id(paymentDto.getOrderDto().getCabinStatusId())
                                .build())
                        .orderDate(paymentDto.getOrderDto().getOrderDate())
                        .orderStatus(OrderStatus.newBuilder()
                                .id(paymentDto.getOrderDto().getOrderStatusId())
                                .build())
                        .cruise(Cruise.newBuilder()
                                .id(paymentDto.getOrderDto().getCruiseId())
                                .build())
                        .build())
                .paymentDate(paymentDto.getPaymentDate())
                .amount(paymentDto.getAmount())
                .build();
    }

    @Override
    public PaymentDto mapEntityToDto(Payment payment) {
        return PaymentDto.newBuilder()
                .id(payment.getId())
                .orderDto(OrderDto.newBuilder()
                        .id(payment.getOrder().getId())
                        .userId(payment.getOrder().getUser().getId())
                        .cabinStatusId(payment.getOrder().getCabinStatus().getId())
                        .orderDate(payment.getOrder().getOrderDate())
                        .orderStatusId(payment.getOrder().getOrderStatus().getId())
                        .cruiseId(payment.getOrder().getCruise().getId())
                        .build())
                .paymentDate(payment.getPaymentDate())
                .amount(payment.getAmount())
                .build();
    }
}
