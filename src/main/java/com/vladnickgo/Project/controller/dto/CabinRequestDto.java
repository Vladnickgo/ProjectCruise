package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class CabinRequestDto {
    private final Integer cabinTypeId;
    private final Integer numberOfCabins;

    private CabinRequestDto(Builder builder) {
        cabinTypeId = builder.cabinTypeId;
        numberOfCabins = builder.numberOfCabins;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer cabinTypeId;
        private Integer numberOfCabins;

        private Builder() {
        }

        public Builder cabinTypeId(Integer val) {
            cabinTypeId = val;
            return this;
        }

        public Builder numberOfCabins(Integer val) {
            numberOfCabins = val;
            return this;
        }

        public CabinRequestDto build() {
            return new CabinRequestDto(this);
        }
    }

    public Integer getCabinTypeId() {
        return cabinTypeId;
    }

    public Integer getNumberOfCabins() {
        return numberOfCabins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinRequestDto that = (CabinRequestDto) o;
        return Objects.equals(cabinTypeId, that.cabinTypeId) && Objects.equals(numberOfCabins, that.numberOfCabins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cabinTypeId, numberOfCabins);
    }

    @Override
    public String toString() {
        return "CabinRequestDto{" +
                "cabinTypeId=" + cabinTypeId +
                ", numberOfCabins=" + numberOfCabins +
                '}';
    }
}
