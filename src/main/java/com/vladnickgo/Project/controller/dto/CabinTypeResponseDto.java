package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class CabinTypeResponseDto {
    private final String cabinTypeName;
    private final Integer numberOfCabins;
    private final Integer numberOfBusyCabins;

    private CabinTypeResponseDto(Builder builder) {
        cabinTypeName = builder.cabinTypeName;
        numberOfCabins = builder.numberOfCabins;
        numberOfBusyCabins = builder.numberOfBusyCabins;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String cabinTypeName;
        private Integer numberOfCabins;
        private Integer numberOfBusyCabins;

        private Builder() {
        }

        public Builder cabinTypeName(String val) {
            cabinTypeName = val;
            return this;
        }

        public Builder numberOfCabins(Integer val) {
            numberOfCabins = val;
            return this;
        }

        public Builder numberOfBusyCabins(Integer val) {
            numberOfBusyCabins = val;
            return this;
        }

        public CabinTypeResponseDto build() {
            return new CabinTypeResponseDto(this);
        }
    }

    public String getCabinTypeName() {
        return cabinTypeName;
    }

    public Integer getNumberOfCabins() {
        return numberOfCabins;
    }

    public Integer getNumberOfBusyCabins() {
        return numberOfBusyCabins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinTypeResponseDto that = (CabinTypeResponseDto) o;
        return Objects.equals(cabinTypeName, that.cabinTypeName) && Objects.equals(numberOfCabins, that.numberOfCabins) && Objects.equals(numberOfBusyCabins, that.numberOfBusyCabins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cabinTypeName, numberOfCabins, numberOfBusyCabins);
    }

    @Override
    public String toString() {
        return "CabinTypeResponseDto{" +
                "cabinTypeName='" + cabinTypeName + '\'' +
                ", numberOfCabins=" + numberOfCabins +
                ", numberOfBusyCabins=" + numberOfBusyCabins +
                '}';
    }
}
