package com.vladnickgo.Project.dao.entity;

import java.util.Objects;

public class Cabin {
    private final Integer id;
    private final CabinType cabinType;
    private final Ship ship;

    private Cabin(Builder builder) {
        id = builder.id;
        cabinType = builder.cabinType;
        ship = builder.ship;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private CabinType cabinType;
        private Ship ship;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder cabinType(CabinType val) {
            cabinType = val;
            return this;
        }

        public Builder ship(Ship val) {
            ship = val;
            return this;
        }

        public Cabin build() {
            return new Cabin(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public CabinType getCabinType() {
        return cabinType;
    }

    public Ship getShip() {
        return ship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cabin cabins = (Cabin) o;
        return Objects.equals(id, cabins.id) && Objects.equals(cabinType, cabins.cabinType) && Objects.equals(ship, cabins.ship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cabinType, ship);
    }

    @Override
    public String toString() {
        return "Cabins{" +
                "id=" + id +
                ", cabinType=" + cabinType +
                ", ship=" + ship +
                '}';
    }
}
