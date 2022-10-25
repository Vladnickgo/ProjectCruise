package com.vladnickgo.Project.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class OrderDto {
    private final Integer id;
    private final Integer userId;
    private final String userDocuments;
    private final Integer cabinStatusId;
    private final LocalDate orderDate;
    private final Integer orderStatusId;
    private final Integer cruiseId;

    private OrderDto(Builder builder) {
        id = builder.id;
        userId = builder.userId;
        userDocuments = builder.userDocuments;
        cabinStatusId = builder.cabinStatusId;
        orderDate = builder.orderDate;
        orderStatusId = builder.orderStatusId;
        cruiseId = builder.cruiseId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private Integer userId;
        private String userDocuments;
        private Integer cabinStatusId;
        private LocalDate orderDate;
        private Integer orderStatusId;
        private Integer cruiseId;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder userId(Integer val) {
            userId = val;
            return this;
        }

        public Builder userDocuments(String val) {
            userDocuments = val;
            return this;
        }

        public Builder cabinStatusId(Integer val) {
            cabinStatusId = val;
            return this;
        }

        public Builder orderDate(LocalDate val) {
            orderDate = val;
            return this;
        }

        public Builder orderStatusId(Integer val) {
            orderStatusId = val;
            return this;
        }

        public Builder cruiseId(Integer val) {
            cruiseId = val;
            return this;
        }

        public OrderDto build() {
            return new OrderDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserDocuments() {
        return userDocuments;
    }

    public Integer getCabinStatusId() {
        return cabinStatusId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    public Integer getCruiseId() {
        return cruiseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) && Objects.equals(userId, orderDto.userId) && Objects.equals(userDocuments, orderDto.userDocuments) && Objects.equals(cabinStatusId, orderDto.cabinStatusId) && Objects.equals(orderDate, orderDto.orderDate) && Objects.equals(orderStatusId, orderDto.orderStatusId) && Objects.equals(cruiseId, orderDto.cruiseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, userDocuments, cabinStatusId, orderDate, orderStatusId, cruiseId);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", userDocuments='" + userDocuments + '\'' +
                ", cabinStatusId=" + cabinStatusId +
                ", orderDate=" + orderDate +
                ", orderStatusId=" + orderStatusId +
                ", cruiseId=" + cruiseId +
                '}';
    }
}
