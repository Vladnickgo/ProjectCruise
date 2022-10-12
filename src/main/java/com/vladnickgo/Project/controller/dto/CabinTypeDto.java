package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class CabinTypeDto {
    private final Integer id;
    private final String cabinTypeName;
    private final Integer numberOfBeds;
    private final Integer price;

    private CabinTypeDto(Builder builder) {
        id = builder.id;
        cabinTypeName = builder.cabinTypeName;
        numberOfBeds = builder.numberOfBeds;
        price = builder.price;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String cabinTypeName;
        private Integer numberOfBeds;
        private Integer price;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder cabinTypeName(String val) {
            cabinTypeName = val;
            return this;
        }

        public Builder numberOfBeds(Integer val) {
            numberOfBeds = val;
            return this;
        }

        public Builder price(Integer val) {
            price = val;
            return this;
        }

        public CabinTypeDto build() {
            return new CabinTypeDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getCabinTypeName() {
        return cabinTypeName;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinTypeDto that = (CabinTypeDto) o;
        return Objects.equals(id, that.id) && Objects.equals(cabinTypeName, that.cabinTypeName) && Objects.equals(numberOfBeds, that.numberOfBeds) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cabinTypeName, numberOfBeds, price);
    }

    @Override
    public String toString() {
        return "CabinTypeDto{" +
                "id=" + id +
                ", cabinTypeName='" + cabinTypeName + '\'' +
                ", numberOfBeds=" + numberOfBeds +
                ", price=" + price +
                '}';
    }
}
