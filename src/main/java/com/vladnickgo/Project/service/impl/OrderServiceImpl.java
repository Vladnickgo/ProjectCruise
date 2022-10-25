package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.dao.OrderDao;
import com.vladnickgo.Project.service.OrderService;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderRepository;
    private static final Integer ORDER_STATUS_CONFIRMED = 2;

    public OrderServiceImpl(OrderDao orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void confirmOrderByPaymentId(Integer paymentId) {
        orderRepository.updateOrderStatusByPaymentId(paymentId, ORDER_STATUS_CONFIRMED);
    }
}
