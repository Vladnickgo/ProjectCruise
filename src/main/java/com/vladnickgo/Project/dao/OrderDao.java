package com.vladnickgo.Project.dao;

import java.util.Map;

public interface OrderDao {
    void updateOrderStatusByPaymentId(Integer paymentId, Integer orderStatusId);

    Map<String, Integer> getEachBusyCabinTypeNumberMap(Integer cruiseIdInteger);
}
