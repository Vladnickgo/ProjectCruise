package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class PaymentDocumentsDto {
    private final String cardNumber;
    private final String cvvCode;
    private final String idCard;

    private PaymentDocumentsDto(Builder builder) {
        cardNumber = builder.cardNumber;
        cvvCode = builder.cvvCode;
        idCard = builder.idCard;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String cardNumber;
        private String cvvCode;
        private String idCard;

        private Builder() {
        }

        public Builder cardNumber(String val) {
            cardNumber = val;
            return this;
        }

        public Builder cvvCode(String val) {
            cvvCode = val;
            return this;
        }

        public Builder idCard(String val) {
            idCard = val;
            return this;
        }

        public PaymentDocumentsDto build() {
            return new PaymentDocumentsDto(this);
        }
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public String getIdCard() {
        return idCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDocumentsDto that = (PaymentDocumentsDto) o;
        return Objects.equals(cardNumber, that.cardNumber) && Objects.equals(cvvCode, that.cvvCode) && Objects.equals(idCard, that.idCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cvvCode, idCard);
    }

    @Override
    public String toString() {
        return "PaymentDocumentsDto{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cvvCode='" + cvvCode + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
