package com.vladnickgo.Project.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Payment {
    private final Integer id;
    private final Order order;
    private final LocalDate paymentDate;
    private final Integer amount;

    private Payment(Builder builder) {
        id = builder.id;
        order = builder.order;
        paymentDate = builder.paymentDate;
        amount = builder.amount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private Order order;
        private LocalDate paymentDate;
        private Integer amount;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder order(Order val) {
            order = val;
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

        public Payment build() {
            return new Payment(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public Order getOrder() {
        return order;
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
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(order, payment.order) && Objects.equals(paymentDate, payment.paymentDate) && Objects.equals(amount, payment.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, paymentDate, amount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", order=" + order +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                '}';
    }
}
