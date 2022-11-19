package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.OrderDto;
import com.vladnickgo.Project.dao.OrderDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    private static final Integer ORDER_STATUS_CONFIRMED = 2;
    private static final Integer TEST_PAYMENT_ID = 1;
    @Mock
    OrderDao orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    void confirmOrderByPaymentId() {
        orderService.confirmOrderByPaymentId(TEST_PAYMENT_ID);
        verify(orderRepository).updateOrderStatusByPaymentId(TEST_PAYMENT_ID, ORDER_STATUS_CONFIRMED);
    }
}