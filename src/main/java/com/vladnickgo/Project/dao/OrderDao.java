package com.vladnickgo.Project.dao;

public interface OrderDao {
    void updateOrderStatusByPaymentId(Integer paymentId, Integer orderStatusId);
}
