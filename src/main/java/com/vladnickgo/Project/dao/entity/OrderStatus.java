package com.vladnickgo.Project.dao.entity;

public enum OrderStatus {
    PROCESSED,
    COMPLETED,
    CANCELLED;

    public static OrderStatus getOrderStatus(int order_status_id) {
        if (order_status_id != 0) {
            switch (order_status_id) {
                case 1:
                    return PROCESSED;
                case 2:
                    return COMPLETED;
                case 3:
                    return CANCELLED;
                default:
                    throw new IllegalArgumentException("This order status doesn't exist");
            }
        } else {
            throw new IllegalArgumentException("Order status is null");
        }
    }

}
