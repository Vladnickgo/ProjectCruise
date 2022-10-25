package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class ShipRequestDto {
    private final Integer shipId;
    private final String sorting;
    private final String ordering;
    private final String recordsOnPage;
    private final String numberOfPage;

    private ShipRequestDto(Builder builder) {
        shipId = builder.shipId;
        sorting = builder.sorting;
        ordering = builder.ordering;
        recordsOnPage = builder.recordsOnPage;
        numberOfPage = builder.numberOfPage;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer shipId;
        private String sorting;
        private String ordering;
        private String recordsOnPage;
        private String numberOfPage;

        private Builder() {
        }

        public Builder shipId(Integer val) {
            shipId = val;
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

        public Builder recordsOnPage(String val) {
            recordsOnPage = val;
            return this;
        }

        public Builder numberOfPage(String val) {
            numberOfPage = val;
            return this;
        }

        public ShipRequestDto build() {
            return new ShipRequestDto(this);
        }
    }

    public Integer getShipId() {
        return shipId;
    }

    public String getSorting() {
        return sorting;
    }

    public String getOrdering() {
        return ordering;
    }

    public String getRecordsOnPage() {
        return recordsOnPage;
    }

    public String getNumberOfPage() {
        return numberOfPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipRequestDto that = (ShipRequestDto) o;
        return Objects.equals(shipId, that.shipId) && Objects.equals(sorting, that.sorting) && Objects.equals(ordering, that.ordering) && Objects.equals(recordsOnPage, that.recordsOnPage) && Objects.equals(numberOfPage, that.numberOfPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipId, sorting, ordering, recordsOnPage, numberOfPage);
    }

    @Override
    public String toString() {
        return "ShipRequestDto{" +
                "shipId=" + shipId +
                ", sorting='" + sorting + '\'' +
                ", ordering='" + ordering + '\'' +
                ", recordsOnPage='" + recordsOnPage + '\'' +
                ", numberOfPage='" + numberOfPage + '\'' +
                '}';
    }
}
