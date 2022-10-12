package com.vladnickgo.Project.dao.entity;

import java.util.Objects;

public class CabinOrder {
    private final Integer cabinId;
    private final Integer orderId;

    private CabinOrder(Builder builder) {
        cabinId = builder.cabinId;
        orderId = builder.orderId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer cabinId;
        private Integer orderId;

        private Builder() {
        }

        public Builder cabinId(Integer val) {
            cabinId = val;
            return this;
        }

        public Builder orderId(Integer val) {
            orderId = val;
            return this;
        }

        public CabinOrder build() {
            return new CabinOrder(this);
        }
    }

    public Integer getCabinId() {
        return cabinId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinOrder that = (CabinOrder) o;
        return Objects.equals(cabinId, that.cabinId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cabinId, orderId);
    }

    @Override
    public String toString() {
        return "CabinOrder{" +
                "cabinId=" + cabinId +
                ", orderId=" + orderId +
                '}';
    }
}
