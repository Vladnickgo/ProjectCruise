package com.vladnickgo.Project.dao.entity;

import java.util.Objects;

public class CabinType {
    private final Integer id;
    private final String cabinName;
    private final Integer numberOfBeds;
    private final Integer price;

    private CabinType(Builder builder) {
        id = builder.id;
        cabinName = builder.cabinName;
        numberOfBeds = builder.numberOfBeds;
        price = builder.price;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String cabinName;
        private Integer numberOfBeds;
        private Integer price;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder cabinName(String val) {
            cabinName = val;
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

        public CabinType build() {
            return new CabinType(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getCabinName() {
        return cabinName;
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
        CabinType that = (CabinType) o;
        return Objects.equals(id, that.id) && Objects.equals(cabinName, that.cabinName) && Objects.equals(numberOfBeds, that.numberOfBeds) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cabinName, numberOfBeds, price);
    }

    @Override
    public String toString() {
        return "CabinTypes{" +
                "id=" + id +
                ", cabinName='" + cabinName + '\'' +
                ", numberOfBeds=" + numberOfBeds +
                ", price=" + price +
                '}';
    }
}
