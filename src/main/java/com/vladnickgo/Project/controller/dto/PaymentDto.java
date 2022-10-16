package com.vladnickgo.Project.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class PaymentDto {
    private final Integer id;
    private final OrderDto orderDto;
    private final LocalDate paymentDate;
    private final Integer amount;

    private PaymentDto(Builder builder) {
        id = builder.id;
        orderDto = builder.orderDto;
        paymentDate = builder.paymentDate;
        amount = builder.amount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private OrderDto orderDto;
        private LocalDate paymentDate;
        private Integer amount;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder orderDto(OrderDto val) {
            orderDto = val;
            return this;
        }

        public Builder paymentDate(LocalDate val) {
            paymentDate = val;
            return this;
        }

        public Builder amount(Integer val) {
            amount = val;
            return this;
        }

        public PaymentDto build() {
            return new PaymentDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDto that = (PaymentDto) o;
        return Objects.equals(id, that.id) && Objects.equals(orderDto, that.orderDto) && Objects.equals(paymentDate, that.paymentDate) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDto, paymentDate, amount);
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
                "id=" + id +
                ", orderDto=" + orderDto +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                '}';
    }
}
