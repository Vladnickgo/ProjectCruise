package com.vladnickgo.Project.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private final Integer id;
    private final User user;
    private final String userDocuments;
    private final CabinStatus cabinStatus;
    private final LocalDate orderDate;
    private final OrderStatus orderStatus;
    private final Cruise cruise;

    private Order(Builder builder) {
        id = builder.id;
        user = builder.user;
        userDocuments = builder.userDocuments;
        cabinStatus = builder.cabinStatus;
        orderDate = builder.orderDate;
        orderStatus = builder.orderStatus;
        cruise = builder.cruise;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private User user;
        private String userDocuments;
        private CabinStatus cabinStatus;
        private LocalDate orderDate;
        private OrderStatus orderStatus;
        private Cruise cruise;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder userDocuments(String val) {
            userDocuments = val;
            return this;
        }

        public Builder cabinStatus(CabinStatus val) {
            cabinStatus = val;
            return this;
        }

        public Builder orderDate(LocalDate val) {
            orderDate = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder cruise(Cruise val) {
            cruise = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getUserDocuments() {
        return userDocuments;
    }

    public CabinStatus getCabinStatus() {
        return cabinStatus;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Cruise getCruise() {
        return cruise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(user, order.user) && Objects.equals(userDocuments, order.userDocuments) && Objects.equals(cabinStatus, order.cabinStatus) && Objects.equals(orderDate, order.orderDate) && Objects.equals(orderStatus, order.orderStatus) && Objects.equals(cruise, order.cruise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, userDocuments, cabinStatus, orderDate, orderStatus, cruise);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", userDocuments='" + userDocuments + '\'' +
                ", cabinStatus=" + cabinStatus +
                ", orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                ", cruise=" + cruise +
                '}';
    }
}
