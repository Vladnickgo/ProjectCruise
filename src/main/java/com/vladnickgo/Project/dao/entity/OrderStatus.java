package com.vladnickgo.Project.dao.entity;

import java.util.Objects;

public class OrderStatus {
    private final Integer id;
    private final String orderStatusName;

    private OrderStatus(Builder builder) {
        id = builder.id;
        orderStatusName = builder.orderStatusName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String orderStatusName;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder orderStatusName(String val) {
            orderStatusName = val;
            return this;
        }

        public OrderStatus build() {
            return new OrderStatus(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(orderStatusName, that.orderStatusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderStatusName);
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", orderStatusName='" + orderStatusName + '\'' +
                '}';
    }
}
