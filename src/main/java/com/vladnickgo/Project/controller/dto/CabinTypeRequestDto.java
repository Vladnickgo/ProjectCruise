package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class CabinTypeRequestDto {
    private final String numberOfSingleCabin;
    private final String numberOfInsideCabin;
    private final String numberOfOceanViewCabin;
    private final String numberOfBalconyCabin;
    private final String numberOfSuiteCabin;

    private CabinTypeRequestDto(Builder builder) {
        numberOfSingleCabin = builder.numberOfSingleCabin;
        numberOfInsideCabin = builder.numberOfInsideCabin;
        numberOfOceanViewCabin = builder.numberOfOceanViewCabin;
        numberOfBalconyCabin = builder.numberOfBalconyCabin;
        numberOfSuiteCabin = builder.numberOfSuiteCabin;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String numberOfSingleCabin;
        private String numberOfInsideCabin;
        private String numberOfOceanViewCabin;
        private String numberOfBalconyCabin;
        private String numberOfSuiteCabin;

        private Builder() {
        }

        public Builder numberOfSingleCabin(String val) {
            numberOfSingleCabin = val;
            return this;
        }

        public Builder numberOfInsideCabin(String val) {
            numberOfInsideCabin = val;
            return this;
        }

        public Builder numberOfOceanViewCabin(String val) {
            numberOfOceanViewCabin = val;
            return this;
        }

        public Builder numberOfBalconyCabin(String val) {
            numberOfBalconyCabin = val;
            return this;
        }

        public Builder numberOfSuiteCabin(String val) {
            numberOfSuiteCabin = val;
            return this;
        }

        public CabinTypeRequestDto build() {
            return new CabinTypeRequestDto(this);
        }
    }

    public String getNumberOfSingleCabin() {
        return numberOfSingleCabin;
    }

    public String getNumberOfInsideCabin() {
        return numberOfInsideCabin;
    }

    public String getNumberOfOceanViewCabin() {
        return numberOfOceanViewCabin;
    }

    public String getNumberOfBalconyCabin() {
        return numberOfBalconyCabin;
    }

    public String getNumberOfSuiteCabin() {
        return numberOfSuiteCabin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinTypeRequestDto that = (CabinTypeRequestDto) o;
        return Objects.equals(numberOfSingleCabin, that.numberOfSingleCabin) && Objects.equals(numberOfInsideCabin, that.numberOfInsideCabin) && Objects.equals(numberOfOceanViewCabin, that.numberOfOceanViewCabin) && Objects.equals(numberOfBalconyCabin, that.numberOfBalconyCabin) && Objects.equals(numberOfSuiteCabin, that.numberOfSuiteCabin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfSingleCabin, numberOfInsideCabin, numberOfOceanViewCabin, numberOfBalconyCabin, numberOfSuiteCabin);
    }

    @Override
    public String toString() {
        return "ShipRequestDto{" +
                "numberOfSingleCabin='" + numberOfSingleCabin + '\'' +
                ", numberOfInsideCabin='" + numberOfInsideCabin + '\'' +
                ", numberOfOceanViewCabin='" + numberOfOceanViewCabin + '\'' +
                ", numberOfBalconyCabin='" + numberOfBalconyCabin + '\'' +
                ", numberOfSuiteCabin='" + numberOfSuiteCabin + '\'' +
                '}';
    }
}
