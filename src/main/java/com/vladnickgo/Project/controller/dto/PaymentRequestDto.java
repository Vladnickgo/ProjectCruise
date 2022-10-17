package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class PaymentRequestDto {
    private final Integer userId;
    private final String sorting;
    private final String ordering;
    private final String itemsOnPage;
    private final String numberOfPage;
    private final String statusInProgress;
    private final String statusConfirmed;
    private final String statusCanceled;
    private final String statusCompleted;

    private PaymentRequestDto(Builder builder) {
        userId = builder.userId;
        sorting = builder.sorting;
        ordering = builder.ordering;
        itemsOnPage = builder.itemsOnPage;
        numberOfPage = builder.numberOfPage;
        statusInProgress = builder.statusInProgress;
        statusConfirmed = builder.statusConfirmed;
        statusCanceled = builder.statusCanceled;
        statusCompleted = builder.statusCompleted;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer userId;
        private String sorting;
        private String ordering;
        private String itemsOnPage;
        private String numberOfPage;
        private String statusInProgress;
        private String statusConfirmed;
        private String statusCanceled;
        private String statusCompleted;

        private Builder() {
        }

        public Builder userId(Integer val) {
            userId = val;
            return this;
        }

        public Builder sorting(String val) {
            sorting = val;
            return this;
        }

        public Builder ordering(String val) {
            ordering = val;
            return this;
        }

        public Builder itemsOnPage(String val) {
            itemsOnPage = val;
            return this;
        }

        public Builder numberOfPage(String val) {
            numberOfPage = val;
            return this;
        }

        public Builder statusInProgress(String val) {
            statusInProgress = val;
            return this;
        }

        public Builder statusConfirmed(String val) {
            statusConfirmed = val;
            return this;
        }

        public Builder statusCanceled(String val) {
            statusCanceled = val;
            return this;
        }

        public Builder statusCompleted(String val) {
            statusCompleted = val;
            return this;
        }

        public PaymentRequestDto build() {
            return new PaymentRequestDto(this);
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public String getSorting() {
        return sorting;
    }

    public String getOrdering() {
        return ordering;
    }

    public String getItemsOnPage() {
        return itemsOnPage;
    }

    public String getNumberOfPage() {
        return numberOfPage;
    }

    public String getStatusInProgress() {
        return statusInProgress;
    }

    public String getStatusConfirmed() {
        return statusConfirmed;
    }

    public String getStatusCanceled() {
        return statusCanceled;
    }

    public String getStatusCompleted() {
        return statusCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRequestDto that = (PaymentRequestDto) o;
        return Objects.equals(userId, that.userId) && Objects.equals(sorting, that.sorting) && Objects.equals(ordering, that.ordering) && Objects.equals(itemsOnPage, that.itemsOnPage) && Objects.equals(numberOfPage, that.numberOfPage) && Objects.equals(statusInProgress, that.statusInProgress) && Objects.equals(statusConfirmed, that.statusConfirmed) && Objects.equals(statusCanceled, that.statusCanceled) && Objects.equals(statusCompleted, that.statusCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, sorting, ordering, itemsOnPage, numberOfPage, statusInProgress, statusConfirmed, statusCanceled, statusCompleted);
    }

    @Override
    public String toString() {
        return "PaymentRequestDto{" +
                "userId=" + userId +
                ", sorting='" + sorting + '\'' +
                ", ordering='" + ordering + '\'' +
                ", itemsOnPage='" + itemsOnPage + '\'' +
                ", numberOfPage='" + numberOfPage + '\'' +
                ", statusInProgress='" + statusInProgress + '\'' +
                ", statusConfirmed='" + statusConfirmed + '\'' +
                ", statusCanceled='" + statusCanceled + '\'' +
                ", statusCompleted='" + statusCompleted + '\'' +
                '}';
    }
}
