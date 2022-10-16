package com.vladnickgo.Project.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class PaymentResponseDto {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer paymentNumber;
    private final LocalDate paymentDate;
    private final String cruiseName;
    private final String routeName;
    private final Integer cabinNumber;
    private final String cabinType;
    private final LocalDate dateStart;
    private final LocalDate dateEnd;
    private final Integer amount;

    private PaymentResponseDto(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        paymentNumber = builder.paymentNumber;
        paymentDate = builder.paymentDate;
        cruiseName = builder.cruiseName;
        routeName = builder.routeName;
        cabinNumber = builder.cabinNumber;
        cabinType = builder.cabinType;
        dateStart = builder.dateStart;
        dateEnd = builder.dateEnd;
        amount = builder.amount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private Integer paymentNumber;
        private LocalDate paymentDate;
        private String cruiseName;
        private String routeName;
        private Integer cabinNumber;
        private String cabinType;
        private LocalDate dateStart;
        private LocalDate dateEnd;
        private Integer amount;

        private Builder() {
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder paymentNumber(Integer val) {
            paymentNumber = val;
            return this;
        }

        public Builder paymentDate(LocalDate val) {
            paymentDate = val;
            return this;
        }

        public Builder cruiseName(String val) {
            cruiseName = val;
            return this;
        }

        public Builder routeName(String val) {
            routeName = val;
            return this;
        }

        public Builder cabinNumber(Integer val) {
            cabinNumber = val;
            return this;
        }

        public Builder cabinType(String val) {
            cabinType = val;
            return this;
        }

        public Builder dateStart(LocalDate val) {
            dateStart = val;
            return this;
        }

        public Builder dateEnd(LocalDate val) {
            dateEnd = val;
            return this;
        }

        public Builder amount(Integer val) {
            amount = val;
            return this;
        }

        public PaymentResponseDto build() {
            return new PaymentResponseDto(this);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getPaymentNumber() {
        return paymentNumber;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getCruiseName() {
        return cruiseName;
    }

    public String getRouteName() {
        return routeName;
    }

    public Integer getCabinNumber() {
        return cabinNumber;
    }

    public String getCabinType() {
        return cabinType;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentResponseDto that = (PaymentResponseDto) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(paymentNumber, that.paymentNumber) && Objects.equals(paymentDate, that.paymentDate) && Objects.equals(cruiseName, that.cruiseName) && Objects.equals(routeName, that.routeName) && Objects.equals(cabinNumber, that.cabinNumber) && Objects.equals(cabinType, that.cabinType) && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, paymentNumber, paymentDate, cruiseName, routeName, cabinNumber, cabinType, dateStart, dateEnd, amount);
    }

    @Override
    public String toString() {
        return "PaymentResponseDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", paymentNumber=" + paymentNumber +
                ", paymentDate=" + paymentDate +
                ", cruiseName='" + cruiseName + '\'' +
                ", routeName='" + routeName + '\'' +
                ", cabinNumber=" + cabinNumber +
                ", cabinType='" + cabinType + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", amount=" + amount +
                '}';
    }
}
